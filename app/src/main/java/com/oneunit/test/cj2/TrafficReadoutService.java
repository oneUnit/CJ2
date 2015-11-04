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

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrafficReadoutService extends Service{
    private boolean running;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        double totalBytes = trSt.getMobileRxBytes() + trSt.getMobileTxBytes();
        FeedReaderDbHelper dbAccess = new FeedReaderDbHelper(TrafficReadoutService.this);

        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();

        // read the previous value from the database
        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = dbSource.rawQuery(selectQuery, null);

        double previousData;
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            previousData = cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE));
        }else
        {
            previousData = 0;
        }
        cursor.close();

        dbSource = dbAccess.getWritableDatabase();

        // creating values to be written into the database
        // timestamp + total consumption within an hour
        ContentValues values = new ContentValues();
        Date dateNow = new Date();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TIME, dateFormat.format(dateNow));
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, totalBytes - previousData);

        long newRowId;
        newRowId = dbSource.insert(FeedReaderContract.FeedEntry.TABLE_NAME, "null", values);

        dbAccess.close();

        //}
        return START_STICKY;
    }
}
