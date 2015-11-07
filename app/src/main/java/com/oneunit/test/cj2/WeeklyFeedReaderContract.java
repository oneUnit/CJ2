package com.oneunit.test.cj2;

import android.provider.BaseColumns;

/**
 * Created by Abbas on 11/7/2015.
 */
public class WeeklyFeedReaderContract {

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "TariffUseWeekly";
        public static final String COLUMN_NAME_VALUE = " val_reg";
        public static final String COLUMN_NAME_TIME = " time_reg";
        public static final String TEXT_TYPE = " TEXT";
        public static final String TIMESTAMP_TYPE = " TİMESTAMP(6)"; //YYMMDD
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_NAME_TIME + " " + FeedEntry.TIMESTAMP_TYPE +"  PRIMARY KEY," +
                        FeedEntry.COLUMN_NAME_VALUE + TEXT_TYPE +
                        " );";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
