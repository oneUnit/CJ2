package com.oneunit.test.cj2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.oneunit.test.cj2.UI.Constants;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elena on 24/11/2015.
 */
public class TrafficInfoManager {

    // returns a one dimensional array with two items: element with index 0 is network traffic, element with index 1 is wifi traffic
   public static double[] getUsageForDay (Context context, Date dayInfo) {
        double[] consumptionPerDay = new double[2];
        int i = 0;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dayInfo.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        cal.set(Calendar.MINUTE, 0); // set minutes to zero
        cal.set(Calendar.SECOND, 0); //set seconds to zero
        float dateStart = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 23); //set hours to 23
        cal.set(Calendar.MINUTE, 59); // set minutes to 59
        cal.set(Calendar.SECOND, 59); //set seconds to 59
        float dateStop = cal.getTimeInMillis();

        consumptionPerDay[0] = 0;
        consumptionPerDay[1] = 0;

        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();
        // read the previous value from the database
       String selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
               " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
               " <= " +  dateStop + " ;";
       Cursor cursor = dbSource.rawQuery(selectQuery, null);

       cursor.moveToFirst();
       consumptionPerDay[0] = cursor.getDouble(0) / 1024 / 1024; // display in MB

       selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
               " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
               " <= " +  dateStop + " ;";
       cursor = dbSource.rawQuery(selectQuery, null);
       cursor.moveToFirst();
       consumptionPerDay[1] = cursor.getDouble(0) / 1024 / 1024; // display in MB
        return consumptionPerDay;
    }

    // returns a one dimensional array with two items: element with index 0 is network traffic, element with index 1 is wifi traffic
    public static double[] getUsageForMonth (Context context, Date dayInfo) {
        double[] consumptionPerMonth = new double[2];

        Calendar calStart = Calendar.getInstance();
        calStart.setTimeInMillis(dayInfo.getTime());
        calStart.set(Calendar.DAY_OF_MONTH, 1);
        calStart.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        calStart.set(Calendar.MINUTE, 0); // set minutes to zero
        calStart.set(Calendar.SECOND, 0); //set seconds to zero
        float dateStart = calStart.getTimeInMillis();
        Calendar calStop = Calendar.getInstance();
        calStop.setTimeInMillis(dayInfo.getTime());
        calStop.set(Calendar.DATE, calStop.getActualMaximum(Calendar.DATE));
        calStop.set(Calendar.HOUR_OF_DAY, 23); //set hours to 23
        calStop.set(Calendar.MINUTE, 59); // set minutes to 59
        calStop.set(Calendar.SECOND, 59); //set seconds to 59
        float dateStop = calStop.getTimeInMillis();

        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();


            String selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            Cursor cursor = dbSource.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            consumptionPerMonth[0] = cursor.getDouble(0) / 1024 / 1024; // display in MB

            selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            cursor = dbSource.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            consumptionPerMonth[1] = cursor.getDouble(0) / 1024 / 1024; // display in MB
        return consumptionPerMonth;
    }

     public static double[][] getDataPerDay (Context context, Date dayInfo){
         double[][] consumptionPerDay = new double[2][Constants.DATA_PER_DAY];
         int i = 0;

         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(dayInfo.getTime());
         cal.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
         cal.set(Calendar.MINUTE, 0); // set minutes to zero
         cal.set(Calendar.SECOND, 0); //set seconds to zero
         float dateStart = cal.getTimeInMillis();
         cal.set(Calendar.HOUR_OF_DAY, 23); //set hours to 23
         cal.set(Calendar.MINUTE, 59); // set minutes to 59
         cal.set(Calendar.SECOND, 59); //set seconds to 59
         float dateStop = cal.getTimeInMillis();

         int j=0;
         for (j = 0; j < Constants.DATA_PER_DAY; j++) {
             consumptionPerDay[0][j] = 0;
             consumptionPerDay[1][j] = 0;
         }

             DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
             SQLiteDatabase dbSource = dbAccess.getReadableDatabase();
             // read the previous value from the database
             String selectQuery = "SELECT  * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                     " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " <= " +  dateStop + " ;";
             Cursor cursor = dbSource.rawQuery(selectQuery, null);

         int dateColumnIndex = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP);
         int valueIndexNetwork = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK);
         int valueIndexWifi = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI);

         if(cursor.moveToFirst()) {
             do {
                 cal.setTimeInMillis(cursor.getLong(dateColumnIndex));
                 j = cal.get(Calendar.HOUR_OF_DAY);
                 consumptionPerDay[0][j] = cursor.getDouble(valueIndexNetwork) / 1024 / 1024; // display in MB
                 consumptionPerDay[1][j] = cursor.getDouble(valueIndexWifi) / 1024 / 1024; // display in MB
                 } while (cursor.moveToNext());
             }
         return consumptionPerDay;
    }

    public static double[][] getDataPerWeek (Context context, Date dayInfo){
        double[][] consumptionPerWeek = new double[2][Constants.DATA_PER_WEEK];
        int i = 0;

        Calendar calStart = Calendar.getInstance();
        calStart.setTimeInMillis(dayInfo.getTime());
        calStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calStart.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        calStart.set(Calendar.MINUTE, 0); // set minutes to zero
        calStart.set(Calendar.SECOND, 0); //set seconds to zero
        float dateStart = calStart.getTimeInMillis();
        Calendar calStop = Calendar.getInstance();
        calStop.setTimeInMillis(dayInfo.getTime());
        calStop.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calStop.set(Calendar.HOUR_OF_DAY, 23); //set hours to 23
        calStop.set(Calendar.MINUTE, 59); // set minutes to 59
        calStop.set(Calendar.SECOND, 59); //set seconds to 59
        float dateStop = calStop.getTimeInMillis();

        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();
        // read the previous value from the database



        for (i = 0; i < Constants.DATA_PER_WEEK; i++){
            String selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            Cursor cursor = dbSource.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            consumptionPerWeek[0][i] = cursor.getDouble(0) / 1024 / 1024; // display in MB

            selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            cursor = dbSource.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            consumptionPerWeek[1][i] = cursor.getDouble(0) / 1024 / 1024; // display in MB

            calStart.add(Calendar.DATE, 1);
            dateStart = calStart.getTimeInMillis();
            calStop.add(Calendar.DATE, 1);
            dateStop = calStop.getTimeInMillis();
        }
        return consumptionPerWeek;
    }

    public static double[][] getDataPerMonth (Context context, Date dayInfo){
        double[][] consumptionPerMonth = new double[2][Constants.DATA_PER_MONTH];
        int i = 0;

        Calendar calStart = Calendar.getInstance();
        calStart.setTimeInMillis(dayInfo.getTime());
        calStart.set(Calendar.DAY_OF_MONTH, 1);
        calStart.set(Calendar.HOUR_OF_DAY, 0); //set hours to zero
        calStart.set(Calendar.MINUTE, 0); // set minutes to zero
        calStart.set(Calendar.SECOND, 0); //set seconds to zero
        float dateStart = calStart.getTimeInMillis();
        Calendar calStop = Calendar.getInstance();
        calStop.setTimeInMillis(dayInfo.getTime());
        calStop.set(Calendar.DATE, calStop.getActualMaximum(Calendar.DATE));
        calStop.set(Calendar.HOUR_OF_DAY, 23); //set hours to 23
        calStop.set(Calendar.MINUTE, 59); // set minutes to 59
        calStop.set(Calendar.SECOND, 59); //set seconds to 59
        float dateStop = calStop.getTimeInMillis();

        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();
        // read the previous value from the database



        for (i = 0; i < Constants.DATA_PER_MONTH; i++){
            String selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            Cursor cursor = dbSource.rawQuery(selectQuery, null);

            cursor.moveToFirst();
            consumptionPerMonth[0][i] = cursor.getDouble(0) / 1024 / 1024; // display in MB

            selectQuery = "SELECT SUM(" + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI + ") FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +
                    " WHERE " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP + " >= " + dateStart + " AND " + DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP +
                    " <= " +  dateStop + " ;";
            cursor = dbSource.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            consumptionPerMonth[1][i] = cursor.getDouble(0) / 1024 / 1024; // display in MB

            calStart.add(Calendar.MONTH, 1);
            dateStart = calStart.getTimeInMillis();
            calStop = calStart;
            calStop.set(Calendar.DATE, calStop.getActualMaximum(Calendar.DATE));
            dateStop = calStop.getTimeInMillis();
        }
        return consumptionPerMonth;
    }

    // returns a two dimensional array of monthly data consumption for each month
    // from the launch of the program until the latest available month
    // index [][0] - network data
    // index [][1] - wifi data
    public static double[][] getDataAllMonthly (Context context) {
        Calendar calNow = Calendar.getInstance();
        int monthNow = Calendar.MONTH;
        Calendar calThen = Calendar.getInstance();
        int monthThen = 0;
        DailyFeedReaderDbHelper dbAccess = new DailyFeedReaderDbHelper(context);
        SQLiteDatabase dbSource = dbAccess.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME +" ;";
        Cursor cursor = dbSource.rawQuery(selectQuery, null);
        int dateColumnIndex = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_TIMESTAMP);
        if (cursor.moveToFirst()) {
            calThen.setTimeInMillis(Double.doubleToLongBits(cursor.getDouble(dateColumnIndex)));
            monthThen = Calendar.MONTH;
        } else {
            monthThen = monthNow;
        }

        double[][] result = new double[2][monthNow - monthThen];
        int i = 0;
        double[] monthly;
        while ((monthNow - monthThen) > 0){
            monthly = getUsageForMonth(context, new Date(calNow.getTimeInMillis()));
            result[0][i] = monthly[0];
            result[1][i] = monthly[1];
            monthThen++;
            calThen.add(Calendar.MONTH, 1);
            i++;
        }

        return result;
    }
}