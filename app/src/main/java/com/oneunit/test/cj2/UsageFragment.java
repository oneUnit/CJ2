package com.oneunit.test.cj2;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.oneunit.test.cj2.UI.Constants;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bishal on 10/16/2015.
 */
public class UsageFragment extends Fragment {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    private int displayPointsNum = 0;
    private Date selectedDisplayDate = new Date();
    private TextView dateIndicator;
    private TextView totalIndicator;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    GraphView graph;
    private  Config config;

    private long yAxisMin = 0;
    private long yAxisMaxDay = 1024;
    private long yAxisMaxWeekly = 7 * yAxisMaxDay;
    private long yAxisMaxMonthly = 31 * yAxisMaxDay;
    ImageButton dateChangeLeft, dateChangeRight;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usage_fragment,container,false);

        try {
            this.config = new Config(this.getActivity());
            if(config.isEmpty()) {
                WelcomeDialog welcomeDialog = new WelcomeDialog(this.getActivity());
                welcomeDialog.show();
            }
        }
        catch (IOException e){
            e.printStackTrace();
            WelcomeDialog welcomeDialog = new WelcomeDialog(this.getActivity());
            welcomeDialog.show();
        }

        graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(12, 8)

        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setNumHorizontalLabels(5);
        graph.getGridLabelRenderer().setGridColor(Color.GREEN);
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);

        return view;
    }
}