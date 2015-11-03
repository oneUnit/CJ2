package com.oneunit.test.cj2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oneunit.test.cj2.UI.Constants;

/**
 * Created by Bishal on 10/16/2015.
 */
public class usageFragment extends Fragment {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usage_fragment,container,false);

        spinner = (Spinner)view.findViewById(R.id.usage_dropdown);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.usage,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"selected",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /***********************Data Handling************************/
        //Temporary unavailable to get real results
        float[] dataArr = new float[Constants.DATA_PER_WEEK];
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME, null);
        if(!cursor.moveToFirst()){

        }
        else {
            int valueIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE);
            for (int i = 0; i < Constants.DATA_PER_WEEK; i++) {
                //dataArr[i] = Float.valueOf(cursor.getString(valueIndex));
                if(!cursor.moveToNext()){
                    dataArr[i] = (float)(Math.random()*100);
                }
            }
        }
        GraphView graph = (GraphView)view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, dataArr[0]),
                new DataPoint(1, dataArr[1]),
                new DataPoint(2, dataArr[2]),
                new DataPoint(3, dataArr[3]),
                new DataPoint(4, dataArr[4]),
                new DataPoint(5, dataArr[5]),
                new DataPoint(6, dataArr[6])

        });
        graph.addSeries(series);
        /*********************************************************/
        return view;
    }
}
