package com.oneunit.test.cj2;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /***********************Data Handling************************/
        float[] dataArrNetwork = new float[Constants.DATA_PER_DAY];
        float[] dataArrWifi = new float[Constants.DATA_PER_DAY];
        DailyFeedReaderDbHelper feedReaderDbHelper = new DailyFeedReaderDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME, null);

        int valueIndexNetwork = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK);
        int valueIndexWifi = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI);
        int i = 0;
        cursor.moveToFirst();
        do{
            dataArrNetwork[i] = cursor.getFloat(valueIndexNetwork)/ 1024/1024; // display in MB
            dataArrWifi[i] = cursor.getFloat(valueIndexWifi)/ 1024/1024; // display in MB
            i++;
        }
        while (cursor.moveToNext());

            for (int j = i; j < Constants.DATA_PER_DAY; j++) {
                    dataArrNetwork[j] = 0;
                    dataArrWifi[j] = 0;
            }
        GraphView graph = (GraphView)view.findViewById(R.id.graph);
<<<<<<< HEAD
        //graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        //graph.getGridLabelRenderer().setHighlightZeroLines(true);
        //graph.getGridLabelRenderer().setVerticalLabelsVisible(true);
        //graph.getGridLabelRenderer().setHorizontalLabelsVisible(true);
        //graph.getGridLabelRenderer().setNumHorizontalLabels(3);
        //graph.getGridLabelRenderer().setNumVerticalLabels(1);
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

=======
        LineGraphSeries<DataPoint> seriesNetwork = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> seriesWifi = new LineGraphSeries<DataPoint>();
        for (int j = 0; j < Constants.DATA_PER_DAY; j++) {
            seriesNetwork.appendData(new DataPoint(j, dataArrNetwork[j]), true, Constants.DATA_PER_DAY);
            seriesWifi.appendData(new DataPoint(j, dataArrWifi[j]), true, Constants.DATA_PER_DAY);
        }

        seriesWifi.setColor(Color.RED);
        graph.addSeries(seriesNetwork);
        graph.addSeries(seriesWifi);
>>>>>>> 430dd3fc4561d21d4642bf46c94e796685b76e9b
        /*********************************************************/

        return view;
    }
}
