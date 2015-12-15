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
<<<<<<< HEAD
}
=======

    private void updateChart(){
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

        //staticLabelsFormatter.setVerticalLabels(new String[]{"0 MB", "250 MB", "500 MB"});

        //graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space


        //graph.getViewport().setMinY(yAxisMin);

        LineGraphSeries<DataPoint> seriesNetwork = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> seriesWifi = new LineGraphSeries<DataPoint>();
        graph.removeAllSeries();
        int selectedDropdownOption = spinner.getSelectedItemPosition();
        double[][] measuredData = new double[2][];
        if (selectedDropdownOption == 0) {
            displayPointsNum = Constants.DATA_PER_DAY;
            measuredData = TrafficInfoManager.getDataPerDay(getActivity(), selectedDisplayDate);
            gridLabel.setHorizontalAxisTitle("Hours");
            // set manual y bounds to have nice steps
            //graph.getViewport().setMaxY(yAxisMaxDay);
            //graph.getViewport().setXAxisBoundsManual(true);
            //staticLabelsFormatter.setHorizontalLabels(new String[]{"00", "02", "04", "06", "08", "10", "12", "14", "16", "18", "20", "22"});
        } else if (selectedDropdownOption == 1) {
            displayPointsNum = Constants.DATA_PER_WEEK;
            measuredData = TrafficInfoManager.getDataPerWeek(getActivity(), selectedDisplayDate);
            gridLabel.setHorizontalAxisTitle("Week days");
            //graph.getViewport().setMaxY(yAxisMaxWeekly);
            //staticLabelsFormatter.setHorizontalLabels(new String[]{"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"});
        } else if (selectedDropdownOption == 2) {
            displayPointsNum = Constants.DATA_PER_MONTH;
            measuredData = TrafficInfoManager.getDataPerMonth(getActivity(), selectedDisplayDate);
            gridLabel.setHorizontalAxisTitle("Days");
            //graph.getViewport().setMaxY(yAxisMaxMonthly);
            //staticLabelsFormatter.setHorizontalLabels(new String[]{"01", "04", "07", "10", "13", "16", "19", "21", "24", "27", "30"});

        }
        for (int j = 0; j < displayPointsNum; j++) {
            seriesNetwork.appendData(new DataPoint(j, measuredData[0][j]), true, displayPointsNum);
            seriesWifi.appendData(new DataPoint(j, measuredData[1][j]), true, displayPointsNum);
        }
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        seriesWifi.setColor(Color.RED);
        graph.addSeries(seriesNetwork);
        graph.addSeries(seriesWifi);

        updateTotal(measuredData);
    }

    private void updateTotal(double[][] measuredData){
        double sum = 0;
        for (int i = 0; i < measuredData[0].length; i++){
            sum += measuredData[0][i];
        }
        totalIndicator.setText(Integer.toString((int)sum));
    }
}
>>>>>>> 56dc7a826432483e5e04396f33349f515c1111ab
