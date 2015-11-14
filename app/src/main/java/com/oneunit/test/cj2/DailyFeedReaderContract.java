package com.oneunit.test.cj2;

import android.provider.BaseColumns;

/**
 * Created by Abbas on 11/7/2015.
 */
public class DailyFeedReaderContract {


    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "TariffUseDaily";
        public static final String COLUMN_NAME_VOLUME_NETWORK = "cVolumeNetwork";
        public static final String COLUMN_NAME_VOLUME_WIFI = "cVolumeWifi";
        public static final String COLUMN_NAME_TIMESTAMP = "cTimeStamp";
        public static final String TEXT_TYPE = " TEXT";
        public static final String FLOAT_TYPE = " REAL";
        public static final String TIMESTAMP_TYPE = " TİMESTAMP"; //YYYY-MM-DD-HH-mm
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_NAME_TIMESTAMP + " " + FeedEntry.FLOAT_TYPE +"  PRIMARY KEY, " +
                        FeedEntry.COLUMN_NAME_VOLUME_NETWORK+ " " + FeedEntry.FLOAT_TYPE + ", " +
                        FeedEntry.COLUMN_NAME_VOLUME_WIFI + " " + FeedEntry.FLOAT_TYPE +
                        " );";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
