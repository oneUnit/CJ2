package com.oneunit.test.cj2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Abbas on 11/7/2015.
 */
public class DailyFeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME ="TariffUseDaily.db";

    public DailyFeedReaderDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB: ", "--- onCreate database ---");
        db.execSQL(DailyFeedReaderContract.FeedEntry.SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DailyFeedReaderContract.FeedEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}