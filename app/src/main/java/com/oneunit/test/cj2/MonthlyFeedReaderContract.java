package com.oneunit.test.cj2;

import android.provider.BaseColumns;

/**
 * Created by Abbas on 11/7/2015.
 */
public class MonthlyFeedReaderContract {

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "TariffUseMonthly";
        public static final String COLUMN_NAME_VOLUME_NETWORK = "cVolumeNetwork";
        public static final String COLUMN_NAME_VOLUME_WIFI = "cVolumeWifi";
        public static final String COLUMN_NAME_TIMESTAMP = "cTimeStamp";
        public static final String TEXT_TYPE = " TEXT";
        public static final String FLOAT_TYPE = " REAL";
        public static final String TIMESTAMP_TYPE = " TİMESTAMP(4)"; //YYMM
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_NAME_TIMESTAMP + " " + FeedEntry.TIMESTAMP_TYPE +"  PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_VOLUME_NETWORK + FLOAT_TYPE + FeedEntry.COLUMN_NAME_VOLUME_WIFI + FLOAT_TYPE +
                        " );";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
