package com.oneunit.test.cj2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Abbas on 10/11/2015.
 */
public class FeedReaderDbWrapper {
    private SQLiteDatabase db;

    public FeedReaderDbWrapper(SQLiteDatabase db){
        this.db = db;
    }

    public String[] getColumn(String dbName, String index){
        String result[] = new String[0];
        Cursor c = this.db.query(dbName, null, null, null, null, null, null);
        if(c.moveToFirst()){
            int columnIndex = c.getColumnIndex(index);

            do{
                String[] temp = result;
                result = new String[result.length + 1];
                for(int i = 0; i < temp.length; i++){
                    result[i] = temp[i];
                }
                result[result.length - 1] = c.getString(columnIndex);
            }
            while (c.moveToNext());
        }
        return result;
    }

    public Cursor rawQuery(String query){
        Cursor c = this.db.rawQuery(query, null);
        return c;
    }

    public Cursor selectQeury(String column, String tableName, String ifClause){
        String query = "";
        if(ifClause == null){
             query = "SELECT " + column + " FROM " + tableName;
        }
        else{
             query = "SELECT " + column + " FROM " + tableName + " WHERE " + ifClause;
        }

        Cursor c = db.rawQuery(query, null);
        return c;
    }
    public long insertQuery(String[] index, String[][] values, String tableName) throws Exception {
        long rowID = -1;
        if(values.length == 0){
            throw new Exception("No values defined");
        }
        else if(index.length == 0){
            throw  new Exception("No index defined");
        }
        else if(tableName == ""){
            throw new Exception("No table defined");
        }
        ContentValues contentValues= new ContentValues();
        for(int i = 0; i < values.length; i++){
            if(index.length != values[i].length){
                throw new Exception("Stopped at row " + i + ". values[" +i + "] has inappropriate length");
            }
            for(int j = 0; j  < values.length; j++){
                contentValues.put(index[j], values[i][j]);
            }
        }
        rowID = this.db.insert(tableName, null, contentValues);
        return rowID;
    }
}
