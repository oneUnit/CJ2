package com.oneunit.test.cj2;

/**
 * Created by Abbas on 10/8/2015.
 */
import android.provider.BaseColumns;

public class FeedReaderContract {

    public FeedReaderContract(){

    }

    public static abstract class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "TariffUse";
        public static final String COLUMN_NAME_ENTRY_ID = "email";
        public static final String COLUMN_NAME_TITLE = "val_reg";
        public static final String COLUMN_NAME_TIME = "time_reg";
        public static final String COLUMN_NAME_SUBTITILE = "";
        public static final String COLUMN_NAME_FOREVER = "forever";
        public static final String TEXT_TYPE = " TEXT";
        public static final String BOOL_TYPE = " BOOLEAN";
        public static final String TİMESTAMP_TYPE = " TİMESTAMP(10)"; //YYMMDDHHMM
        public static final String COMMA_SEP = ",";
        public static final String COLUMN_NAME_CONTENT = "";
        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" + FeedEntry.COLUMN_NAME_ENTRY_ID + " " + TEXT_TYPE +"  PRIMARY KEY,"
                        + FeedEntry.COLUMN_NAME_TIME + TİMESTAMP_TYPE + COMMA_SEP +
                        FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
                        " );";
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
    }
}
