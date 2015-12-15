package com.oneunit.test.cj2;

/**
 * Created by Elena on 02/11/2015.
 */
import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.TrafficStats;
import android.os.*;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrafficReadoutService extends Service{
    private boolean running;
    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        this.running = false;
    }

    @Override
    public void onDestroy(){
        this.running = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //if (!this.running){
        this.running = true;
        // TODO
        // collect consupmtion information
        TrafficStats trSt = new TrafficStats();
        double totalBytesMobile = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes();
        double totalBytesWifi = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes() - totalBytesMobile;
        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(TrafficReadoutService.this);

        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();

        // read the previous value from the database
        String selectQuery = "SELECT  * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = dbSource.rawQuery(selectQuery, null);

        double previousDataNetwork, previousDataWifi;

        // read the previous value of the TrafficStats readout
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        previousDataNetwork = preferences.getFloat("TrafficStatsNetwork", (float)totalBytesMobile);
        previousDataWifi = preferences.getFloat("TrafficStatsWifi", (float)totalBytesWifi);

        // countering the TrafficStats bug that occurs when the phone or user switches from mobile network into wifi
        // which results in the TrafficStats object resetting the MobileRXBytes and MobileTXBytes counters
        // and decrementing the TotalRxBytes and TotalTxBytes by those amounts
        if (previousDataNetwork > totalBytesMobile){
            // the switch has ocurred sometime within the observational hour
            totalBytesWifi += previousDataNetwork;
            previousDataNetwork = 0;
        }

        SQLiteDatabase dbSourceWrite = dbAccess.getWritableDatabase();

        // creating values to be written into the database
        // timestamp + total consumption within an hour
        ContentValues values = new ContentValues();
        //Date dateNow = new Date();
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP, System.currentTimeMillis());
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK, totalBytesMobile - previousDataNetwork);
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI, totalBytesWifi - previousDataWifi);

        long newRowId = dbSourceWrite.insert(DailyFeedReaderContract.FeedEntry.TABLE_NAME, "null", values);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("TrafficStatsNetwork", (float)totalBytesMobile);
        editor.putFloat("TrafficStatsWifi", (float)totalBytesWifi);
        editor.commit();

        return START_NOT_STICKY;
    }
}
