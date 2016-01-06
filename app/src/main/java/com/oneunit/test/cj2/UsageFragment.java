package com.oneunit.test.cj2;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
public class UsageFragment extends Fragment{

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
    ImageButton changeViewButton,dateChangeLeft, dateChangeRight;
    Button  dailyButton, weeklyButton, monthlyButton ;
    boolean dailyButtonflag = true, weeklyButtonflag, montlyButtonflag;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usage_fragment, container, false);
        Typeface roboto_regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface mytypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FuturaCondense-Normal.ttf");

        final RelativeLayout change_view_layout = (RelativeLayout)view.findViewById(R.id.change_view_menu);
        final RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.layout2);

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

        graph  = (GraphView)view.findViewById(R.id.graph);
        //----spinner = (Spinner)view.findViewById(R.id.usage_dropdown);
        //adapter = ArrayAdapter.createFromResource(getActivity(),R.array.usage,android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        dateIndicator = (TextView) view.findViewById(R.id.dataDate);
        totalIndicator = (TextView) view.findViewById(R.id.totalVolumeNum);
        dateIndicator.setText(dateFormat.format(new Date()));

        changeViewButton = (ImageButton) view.findViewById(R.id.change_view);

        dailyButton = (Button) view.findViewById(R.id.daily_button);
        dailyButton.setTypeface(mytypeface);

        weeklyButton = (Button) view.findViewById(R.id.weekly_button);
        weeklyButton.setTypeface(mytypeface);

        monthlyButton = (Button) view.findViewById(R.id.monthly_button);
        monthlyButton.setTypeface(mytypeface);

        dateChangeLeft = (ImageButton) view.findViewById(R.id.dateBeforeBtn);
        dateChangeRight = (ImageButton) view.findViewById(R.id.dateAfterBtn);
        //----------------------------------------------------------------------
        dailyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dailyButtonflag = true;
                weeklyButtonflag = false;
                montlyButtonflag = false;
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                change_view_layout.startAnimation(slideDown);
                change_view_layout.setVisibility(View.GONE);
                dailyButton.setTextColor(Color.parseColor("#3c434e"));
                weeklyButton.setTextColor(Color.parseColor("#593c434e"));
                monthlyButton.setTextColor(Color.parseColor("#593c434e"));
                updateChart();
            }


        });
        weeklyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                weeklyButtonflag = true;
                dailyButtonflag = false;
                montlyButtonflag = false;
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                change_view_layout.startAnimation(slideDown);
                change_view_layout.setVisibility(View.GONE);
                dailyButton.setTextColor(Color.parseColor("#593c434e"));
                weeklyButton.setTextColor(Color.parseColor("#3c434e"));
                monthlyButton.setTextColor(Color.parseColor("#593c434e"));
                updateChart();
            }


        });
        monthlyButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                montlyButtonflag = true;
                weeklyButtonflag = false;
                dailyButtonflag = false;
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                change_view_layout.startAnimation(slideDown);
                change_view_layout.setVisibility(View.GONE);
                dailyButton.setTextColor(Color.parseColor("#593c434e"));
                weeklyButton.setTextColor(Color.parseColor("#593c434e"));
                monthlyButton.setTextColor(Color.parseColor("#3c434e"));
                updateChart();
            }


        });
        dateChangeLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDisplayDate);
                if(dailyButtonflag){
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                }else if(weeklyButtonflag){
                    cal.add(Calendar.DAY_OF_MONTH, -7);
                }else if(montlyButtonflag){
                    cal.add(Calendar.MONTH, -1);
                }
                selectedDisplayDate = cal.getTime();
                dateIndicator.setText(dateFormat.format(selectedDisplayDate));
                updateChart();
            }
        });

        dateChangeRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDisplayDate);
                if (dailyButtonflag){
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                } else if(weeklyButtonflag){
                    cal.add(Calendar.DAY_OF_MONTH, 7);
                } else if(montlyButtonflag){
                    cal.add(Calendar.MONTH, 1);
                }
                selectedDisplayDate = cal.getTime();
                dateIndicator.setText(dateFormat.format(selectedDisplayDate));
                updateChart();
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(change_view_layout.getVisibility()== View.VISIBLE) {
                    Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                    change_view_layout.startAnimation(slideDown);
                    change_view_layout.setVisibility(View.GONE);
                }
            }

        });

        changeViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideUp = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_up);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);

                if (change_view_layout.getVisibility() == View.GONE) {
                    change_view_layout.startAnimation(slideUp);
                    change_view_layout.setVisibility(View.VISIBLE);
                } else {
                    change_view_layout.startAnimation(slideDown);
                    change_view_layout.setVisibility(View.GONE);
                }
            }
        });

        updateChart();
        /***********************Data Handling************************/
//        float[] dataArrNetwork = new float[Constants.DATA_PER_DAY];
//        float[] dataArrWifi = new float[Constants.DATA_PER_DAY];
//        DailyFeedReaderDbHelper feedReaderDbHelper = new DailyFeedReaderDbHelper(getActivity());
//        SQLiteDatabase sqLiteDatabase = feedReaderDbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DailyFeedReaderContract.FeedEntry.TABLE_NAME, null);
//
//        int valueIndexNetwork = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_NETWORK);
//        int valueIndexWifi = cursor.getColumnIndex(DailyFeedReaderContract.FeedEntry.COLUMN_NAME_VOLUME_WIFI);
//        int i = 0;
//        if(cursor.moveToFirst()) {
//            do {
//                dataArrNetwork[i] = cursor.getFloat(valueIndexNetwork) / 1024 / 1024; // display in MB
//                dataArrWifi[i] = cursor.getFloat(valueIndexWifi) / 1024 / 1024; // display in MB
//                i++;
//            }
//            while (cursor.moveToNext() && (i < Constants.DATA_PER_DAY));
//        }
//
//            for (int j = i; j < Constants.DATA_PER_DAY; j++) {
//                    dataArrNetwork[j] = 0;
//                    dataArrWifi[j] = 0;
//            }
//
//        for (int j = 0; j < Constants.DATA_PER_DAY; j++) {
//            seriesNetwork.appendData(new DataPoint(j, dataArrNetwork[j]), true, Constants.DATA_PER_DAY);
//            seriesWifi.appendData(new DataPoint(j, dataArrWifi[j]), true, Constants.DATA_PER_DAY);
//        }
//
//        seriesWifi.setColor(Color.RED);
//        graph.addSeries(seriesNetwork);
//        graph.addSeries(seriesWifi);
        /*********************************************************/



        return view;
    }
    private void updateChart(){
       // GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);

        //staticLabelsFormatter.setVerticalLabels(new String[]{"0 MB", "250 MB", "500 MB"});

        //graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space


        //graph.getViewport().setMinY(yAxisMin);

        LineGraphSeries<DataPoint> seriesNetwork = new LineGraphSeries<DataPoint>();
        LineGraphSeries<DataPoint> seriesWifi = new LineGraphSeries<DataPoint>();
        graph.removeAllSeries();
       //---- int selectedDropdownOption = spinner.getSelectedItemPosition();
        double[][] measuredData = new double[2][];

        if(dailyButtonflag){
            displayPointsNum = Constants.DATA_PER_DAY;
            measuredData = TrafficInfoManager.getDataPerDay(getActivity(), selectedDisplayDate);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Hours");
           // gridLabel.setHorizontalAxisTitle("Hours");

            // set manual y bounds to have nice steps
            //graph.getViewport().setMaxY(yAxisMaxDay);
            //graph.getViewport().setXAxisBoundsManual(true);
            staticLabelsFormatter.setHorizontalLabels(new String[]{"00", "02", "04", "06", "08", "10", "12", "14", "16", "18", "20", "22"});
        } else if(weeklyButtonflag){
            displayPointsNum = Constants.DATA_PER_WEEK;
            measuredData = TrafficInfoManager.getDataPerWeek(getActivity(), selectedDisplayDate);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Week days");
           // gridLabel.setHorizontalAxisTitle("Week days");

            //graph.getViewport().setMaxY(yAxisMaxWeekly);
            staticLabelsFormatter.setHorizontalLabels(new String[]{"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"});
        }else if(montlyButtonflag){
            displayPointsNum = Constants.DATA_PER_MONTH;
            measuredData = TrafficInfoManager.getDataPerMonth(getActivity(), selectedDisplayDate);
            graph.getGridLabelRenderer().setHorizontalAxisTitle("Days");
            //gridLabel.setHorizontalAxisTitle("Days");
            //graph.getViewport().setMaxY(yAxisMaxMonthly);
            staticLabelsFormatter.setHorizontalLabels(new String[]{"01", "04", "07", "10", "13", "16", "19", "21", "24", "27", "30"});
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

