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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.PriorityQueue;

/**
 * Created by Bishal on 12/29/2015.
 */
public class TariffsFragment extends Fragment {
    private TextView tariffs,top,tariffPriceEuro1,seperator,tariffPriceCent1,yourTarifDetail,userTarifPriceEuro,userTarifPriceCent,
                    telefoniaFlat,dtHandyFlat,userInternetUsage,userInternetSpeed,ourSuggestion,suggeestionPriceEuro,suggestionPriceCent,telefoniaFlat2,dtHandyFlat2,
                     suggestionInternetUsage,suggestionInternetSpeed,commaSeperator,commaSeperator2 ;
    private TextView tariffPriceEuro2,tariffPriceEuro3,tariffPriceEuro4,tariffPriceEuro5,tariffPriceEuro6,tariffPriceEuro7,tariffPriceEuro8,tariffPriceEuro9,tariffPriceEuro10;
    private TextView seperator2,seperator3,seperator4,seperator5,seperator6,seperator7,seperator8,seperator9,seperator10;
    private TextView tariffPriceCent2,tariffPriceCent3,tariffPriceCent4,tariffPriceCent5,tariffPriceCent6,tariffPriceCent7,tariffPriceCent8,tariffPriceCent9,tariffPriceCent10;


    private ImageView indicator1,indicator2,indicator3,indicator4,indicator5,indicator6,indicator7,indicator8,indicator9,indicator10;
    int counter;
    Button  topTariffsButton, comparisonButton;

    ImageButton leftArrow, rightArrow, changeViewButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.tarif_fragment,container,false);
        final RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.layout);
        final RelativeLayout change_view_layout = (RelativeLayout)view.findViewById(R.id.change_view_menu);
        final RelativeLayout tarif_layout_1 = (RelativeLayout)view.findViewById(R.id.layout1);
        final RelativeLayout tarif_layout_2 = (RelativeLayout)view.findViewById(R.id.layout2);
        final RelativeLayout tarif_layout_3 = (RelativeLayout)view.findViewById(R.id.layout3);
        final RelativeLayout tarif_layout_4 = (RelativeLayout)view.findViewById(R.id.layout4);
        final RelativeLayout tarif_layout_5 = (RelativeLayout)view.findViewById(R.id.layout5);
        final RelativeLayout tarif_layout_6 = (RelativeLayout)view.findViewById(R.id.layout6);
        final RelativeLayout tarif_layout_7 = (RelativeLayout)view.findViewById(R.id.layout7);
        final RelativeLayout tarif_layout_8 = (RelativeLayout)view.findViewById(R.id.layout8);
        final RelativeLayout tarif_layout_9 = (RelativeLayout)view.findViewById(R.id.layout9);
        final RelativeLayout tarif_layout_10 = (RelativeLayout)view.findViewById(R.id.layout10);
        indicator1 = (ImageView)view.findViewById(R.id.indicator_second1);
        indicator2 = (ImageView)view.findViewById(R.id.indicator_second2);
        indicator3 = (ImageView)view.findViewById(R.id.indicator_second3);
        indicator4 = (ImageView)view.findViewById(R.id.indicator_second4);
        indicator5 = (ImageView)view.findViewById(R.id.indicator_second5);
        indicator6 = (ImageView)view.findViewById(R.id.indicator_second6);
        indicator7 = (ImageView)view.findViewById(R.id.indicator_second7);
        indicator8 = (ImageView)view.findViewById(R.id.indicator_second8);
        indicator9 = (ImageView)view.findViewById(R.id.indicator_second9);
        indicator10 = (ImageView)view.findViewById(R.id.indicator_second10);
        final RelativeLayout top_ten_tarif_head = (RelativeLayout)view.findViewById(R.id.top_tariffs_layout_head);
        final RelativeLayout top_ten_tarif_body = (RelativeLayout)view.findViewById(R.id.top_tariffs_layout_body);
        final RelativeLayout comparison_layout_head = (RelativeLayout)view.findViewById(R.id.comparision_layout_head);
        final RelativeLayout comparison_layout_body = (RelativeLayout)view.findViewById(R.id.comparision_layout_body);

        changeViewButton = (ImageButton) view.findViewById(R.id.change_view);
        topTariffsButton = (Button)view.findViewById(R.id.top_tariffs_button);
        topTariffsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(top_ten_tarif_head.getVisibility()==View.GONE) {
                    top_ten_tarif_head.setVisibility(View.VISIBLE);
                    top_ten_tarif_body.setVisibility(View.VISIBLE);
                    tarif_layout_1.setVisibility(View.VISIBLE);
                    comparison_layout_head.setVisibility(View.GONE);
                    comparison_layout_body.setVisibility(View.GONE);
                }
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                change_view_layout.startAnimation(slideDown);
                change_view_layout.setVisibility(View.GONE);
                comparisonButton.setTextColor(Color.parseColor("#593c434e"));
                topTariffsButton.setTextColor(Color.parseColor("#3c434e"));
            }
        });

        comparisonButton = (Button)view.findViewById(R.id.comparision_button);
        comparisonButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if (comparison_layout_head.getVisibility() == View.GONE) {
                    comparison_layout_head.setVisibility(View.VISIBLE);
                    comparison_layout_body.setVisibility(View.VISIBLE);
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    top_ten_tarif_head.setVisibility(View.GONE);
                    top_ten_tarif_body.setVisibility(View.GONE);
                }
                Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                change_view_layout.startAnimation(slideDown);
                change_view_layout.setVisibility(View.GONE);
                comparisonButton.setTextColor(Color.parseColor("#3c434e"));
                topTariffsButton.setTextColor(Color.parseColor("#593c434e"));
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
        relativeLayout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(change_view_layout.getVisibility()== View.VISIBLE) {
                    Animation slideDown = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.slide_down);
                    change_view_layout.startAnimation(slideDown);
                    change_view_layout.setVisibility(View.GONE);
                }
            }

        });
        Typeface roboto_regular = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface mytypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/FuturaCondense-Normal.ttf");

        tariffs = (TextView)view.findViewById(R.id.tariffs);
        top = (TextView)view.findViewById(R.id.top);

        tariffPriceEuro1 = (TextView)view.findViewById(R.id.tarif_price_euro1);
        tariffPriceEuro2 = (TextView)view.findViewById(R.id.tarif_price_euro2);
        tariffPriceEuro3 = (TextView)view.findViewById(R.id.tarif_price_euro3);
        tariffPriceEuro4 = (TextView)view.findViewById(R.id.tarif_price_euro4);
        tariffPriceEuro5 = (TextView)view.findViewById(R.id.tarif_price_euro5);
        tariffPriceEuro6 = (TextView)view.findViewById(R.id.tarif_price_euro6);
        tariffPriceEuro7 = (TextView)view.findViewById(R.id.tarif_price_euro7);
        tariffPriceEuro8 = (TextView)view.findViewById(R.id.tarif_price_euro8);
        tariffPriceEuro9 = (TextView)view.findViewById(R.id.tarif_price_euro9);
        tariffPriceEuro10 = (TextView)view.findViewById(R.id.tarif_price_euro10);
        seperator = (TextView)view.findViewById(R.id.comma);
        seperator2 = (TextView)view.findViewById(R.id.comma2);
        seperator3 = (TextView)view.findViewById(R.id.comma3);
        seperator4 = (TextView)view.findViewById(R.id.comma4);
        seperator5 = (TextView)view.findViewById(R.id.comma5);
        seperator6 = (TextView)view.findViewById(R.id.comma6);
        seperator7 = (TextView)view.findViewById(R.id.comma7);
        seperator8 = (TextView)view.findViewById(R.id.comma8);
        seperator9 = (TextView)view.findViewById(R.id.comma9);
        seperator10 = (TextView)view.findViewById(R.id.comma10);
        tariffPriceCent1 =(TextView)view.findViewById(R.id.tarif_price_cent1);
        tariffPriceCent2 =(TextView)view.findViewById(R.id.tarif_price_cent2);
        tariffPriceCent3 =(TextView)view.findViewById(R.id.tarif_price_cent3);
        tariffPriceCent4 =(TextView)view.findViewById(R.id.tarif_price_cent4);
        tariffPriceCent5 =(TextView)view.findViewById(R.id.tarif_price_cent5);
        tariffPriceCent6 =(TextView)view.findViewById(R.id.tarif_price_cent6);
        tariffPriceCent7 =(TextView)view.findViewById(R.id.tarif_price_cent7);
        tariffPriceCent8 =(TextView)view.findViewById(R.id.tarif_price_cent8);
        tariffPriceCent9 =(TextView)view.findViewById(R.id.tarif_price_cent9);
        tariffPriceCent10 =(TextView)view.findViewById(R.id.tarif_price_cent10);


        yourTarifDetail = (TextView)view.findViewById(R.id.your_tarif_detail);
        userTarifPriceEuro = (TextView)view.findViewById(R.id.user_tarif_price_euro);
        commaSeperator = (TextView)view.findViewById(R.id.comma_seperator);
        userTarifPriceCent = (TextView)view.findViewById(R.id.user_tarif_price_cent);
        telefoniaFlat = (TextView)view.findViewById(R.id.telefonia_flat);
        dtHandyFlat = (TextView)view.findViewById(R.id.dt_handy_flat);
        userInternetUsage = (TextView)view.findViewById(R.id.user_internet_usage);
        userInternetSpeed = (TextView)view.findViewById(R.id.user_internet_speed);
        ourSuggestion = (TextView)view.findViewById(R.id.our_suggestion);
        suggeestionPriceEuro = (TextView)view.findViewById(R.id.suggestion_tarif_price_euro);
        commaSeperator2 = (TextView)view.findViewById(R.id.comma_seperator2);
        suggestionPriceCent = (TextView)view.findViewById(R.id.suggestion_tarif_price_cent);
        telefoniaFlat2 = (TextView)view.findViewById(R.id.telefonia_flat2);
        dtHandyFlat2 = (TextView)view.findViewById(R.id.dt_handy_flat2);
        suggestionInternetSpeed = (TextView)view.findViewById(R.id.suggestion_internet_speed);
        suggestionInternetUsage = (TextView)view.findViewById(R.id.suggestion_internet_usage);

        yourTarifDetail.setTypeface(mytypeface);
        ourSuggestion.setTypeface(mytypeface);
        userTarifPriceEuro.setTypeface(mytypeface);
        commaSeperator.setTypeface(mytypeface);
        userTarifPriceCent.setTypeface(mytypeface);
        telefoniaFlat.setTypeface(mytypeface);
        dtHandyFlat.setTypeface(mytypeface);
        userInternetUsage.setTypeface(mytypeface);
        userInternetSpeed.setTypeface(mytypeface);
        suggeestionPriceEuro.setTypeface(mytypeface);
        commaSeperator2.setTypeface(mytypeface);
        suggestionPriceCent.setTypeface(mytypeface);
        telefoniaFlat2.setTypeface(mytypeface);
        dtHandyFlat2.setTypeface(mytypeface);
        suggestionInternetUsage.setTypeface(mytypeface);
        suggestionInternetSpeed.setTypeface(mytypeface);
        topTariffsButton.setTypeface(mytypeface);
        comparisonButton.setTypeface(mytypeface);



        tariffs.setTypeface(roboto_regular);
        top.setTypeface(roboto_regular);

        tariffPriceEuro1.setTypeface(mytypeface);
        tariffPriceEuro2.setTypeface(mytypeface);
        tariffPriceEuro3.setTypeface(mytypeface);
        tariffPriceEuro4.setTypeface(mytypeface);
        tariffPriceEuro5.setTypeface(mytypeface);
        tariffPriceEuro6.setTypeface(mytypeface);
        tariffPriceEuro7.setTypeface(mytypeface);
        tariffPriceEuro8.setTypeface(mytypeface);
        tariffPriceEuro9.setTypeface(mytypeface);
        tariffPriceEuro10.setTypeface(mytypeface);
        seperator.setTypeface(mytypeface);
        seperator2.setTypeface(mytypeface);
        seperator3.setTypeface(mytypeface);
        seperator4.setTypeface(mytypeface);
        seperator5.setTypeface(mytypeface);
        seperator6.setTypeface(mytypeface);
        seperator7.setTypeface(mytypeface);
        seperator8.setTypeface(mytypeface);
        seperator9.setTypeface(mytypeface);
        seperator10.setTypeface(mytypeface);
        tariffPriceCent1.setTypeface(mytypeface);
        tariffPriceCent2.setTypeface(mytypeface);
        tariffPriceCent3.setTypeface(mytypeface);
        tariffPriceCent4.setTypeface(mytypeface);
        tariffPriceCent5.setTypeface(mytypeface);
        tariffPriceCent6.setTypeface(mytypeface);
        tariffPriceCent7.setTypeface(mytypeface);
        tariffPriceCent8.setTypeface(mytypeface);
        tariffPriceCent9.setTypeface(mytypeface);
        tariffPriceCent10.setTypeface(mytypeface);

        leftArrow = (ImageButton)view.findViewById(R.id.leftArrow);
        rightArrow = (ImageButton)view.findViewById(R.id.rightArrow);

        counter = 1;
        leftArrow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter --;
                if(counter < 1){
                    counter = 10;
                }
                if(counter == 1){
                    tarif_layout_1.setVisibility(View.VISIBLE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.VISIBLE);
                    indicator2.setVisibility(View.GONE);
                    indicator3.setVisibility(View.GONE);
                    indicator4.setVisibility(View.GONE);
                    indicator5.setVisibility(View.GONE);
                    indicator6.setVisibility(View.GONE);
                    indicator7.setVisibility(View.GONE);
                    indicator8.setVisibility(View.GONE);
                    indicator9.setVisibility(View.GONE);
                    indicator10.setVisibility(View.GONE);
                }
                if(counter == 2){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.VISIBLE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.VISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 3){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.VISIBLE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.VISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 4){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.VISIBLE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.VISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 5){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.VISIBLE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.VISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 6){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.VISIBLE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.VISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 7){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.VISIBLE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.VISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 8){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.VISIBLE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.VISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 9){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.VISIBLE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.VISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 10){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.VISIBLE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.VISIBLE);
                }
            }



        });

        rightArrow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                counter ++;
                if(counter > 10){
                    counter = 1;
                }
                if(counter == 1){
                    tarif_layout_1.setVisibility(View.VISIBLE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.VISIBLE);
                    indicator2.setVisibility(View.GONE);
                    indicator3.setVisibility(View.GONE);
                    indicator4.setVisibility(View.GONE);
                    indicator5.setVisibility(View.GONE);
                    indicator6.setVisibility(View.GONE);
                    indicator7.setVisibility(View.GONE);
                    indicator8.setVisibility(View.GONE);
                    indicator9.setVisibility(View.GONE);
                    indicator10.setVisibility(View.GONE);
                }
                if(counter == 2){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.VISIBLE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.VISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 3){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.VISIBLE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.VISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 4){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.VISIBLE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.VISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 5){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.VISIBLE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.VISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 6){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.VISIBLE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.VISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 7){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.VISIBLE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.VISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 8){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.VISIBLE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.VISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 9){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.VISIBLE);
                    tarif_layout_10.setVisibility(View.GONE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.VISIBLE);
                    indicator10.setVisibility(View.INVISIBLE);
                }
                if(counter == 10){
                    tarif_layout_1.setVisibility(View.GONE);
                    tarif_layout_2.setVisibility(View.GONE);
                    tarif_layout_3.setVisibility(View.GONE);
                    tarif_layout_4.setVisibility(View.GONE);
                    tarif_layout_5.setVisibility(View.GONE);
                    tarif_layout_6.setVisibility(View.GONE);
                    tarif_layout_7.setVisibility(View.GONE);
                    tarif_layout_8.setVisibility(View.GONE);
                    tarif_layout_9.setVisibility(View.GONE);
                    tarif_layout_10.setVisibility(View.VISIBLE);
                    indicator1.setVisibility(View.INVISIBLE);
                    indicator2.setVisibility(View.INVISIBLE);
                    indicator3.setVisibility(View.INVISIBLE);
                    indicator4.setVisibility(View.INVISIBLE);
                    indicator5.setVisibility(View.INVISIBLE);
                    indicator6.setVisibility(View.INVISIBLE);
                    indicator7.setVisibility(View.INVISIBLE);
                    indicator8.setVisibility(View.INVISIBLE);
                    indicator9.setVisibility(View.INVISIBLE);
                    indicator10.setVisibility(View.VISIBLE);
                }

            }


        });

        return view;
    }

}
