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
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

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
        double totalBytesMobile = trSt.getMobileRxBytes() + trSt.getMobileTxBytes();
        double totalBytesWifi = trSt.getTotalRxBytes() + trSt.getTotalTxBytes() - totalBytesMobile;
        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(TrafficReadoutService.this);

        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();

        // read the previous value from the database
        String selectQuery = "SELECT  * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = dbSource.rawQuery(selectQuery, null);

        double previousDataNetwork, previousDataWifi;

        // read the previous value of the TrafficStats readout
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TrafficReadoutService.this);
        previousDataNetwork = preferences.getFloat("TrafficStatsNetwork", (float)totalBytesMobile);
        previousDataWifi = preferences.getFloat("TrafficStatsWifi", (float)totalBytesWifi);


        /*if (cursor.getCount() > 0) {
            cursor.moveToLast();
            totalBytesMobile-= cursor.getLong(cursor.getColumnIndexOrThrow(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK));
            totalBytesWifi -= cursor.getLong(cursor.getColumnIndexOrThrow(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI));
        }else
        {
            //totalBytesMobile = 0;
            //totalBytesWifi = 0;
        }
        //cursor.close();
        */
        SQLiteDatabase dbSourceWrite = dbAccess.getWritableDatabase();

        // creating values to be written into the database
        // timestamp + total consumption within an hour
        Log.d("DB Mobile", totalBytesMobile + "");
        Log.d("DB Wifi", totalBytesWifi + "");
        ContentValues values = new ContentValues();
        Date dateNow = new Date();
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP, dateFormat.format(dateNow));
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK, totalBytesMobile - previousDataNetwork);
        values.put(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI, totalBytesWifi - previousDataWifi);

        long newRowId = dbSourceWrite.insert(DailyFeedReaderContract.FeedEntry.TABLE_NAME, "null", values);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("TrafficStatsNetwork", (float)totalBytesMobile);
        editor.putFloat("TrafficStatsWifi", (float)totalBytesWifi);
        editor.commit();


        //dbAccess.close();

        //}
        return START_NOT_STICKY;
    }
}
