package com.oneunit.test.cj2;

/**
 * Created by Bishal on 11/24/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ComparisonFragment extends Activity {

    TextView textViewMyPlan[], textViewBestPlan[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_fragment);
        this.textViewMyPlan = new TextView[5];
        this.textViewBestPlan = new TextView[5];

        this.textViewMyPlan[0] = (TextView) findViewById(R.id.tarifNameView);
        this.textViewMyPlan[1] = (TextView) findViewById(R.id.tarifDataLimitView);
        this.textViewMyPlan[2] = (TextView) findViewById(R.id.tarifPriceView);
        this.textViewMyPlan[3] = (TextView) findViewById(R.id.tarifTotalCostView);
        this.textViewMyPlan[4] = (TextView) findViewById(R.id.tarifSpeedView);

        this.textViewBestPlan[0] = (TextView) findViewById(R.id.bestTarifNameView);
        this.textViewBestPlan[1] = (TextView) findViewById(R.id.bestTarifDataLimitView);
        this.textViewBestPlan[2] = (TextView) findViewById(R.id.bestTarifPriceView);
        this.textViewBestPlan[3] = (TextView) findViewById(R.id.bestTarifTotalCostView);
        this.textViewBestPlan[4] = (TextView) findViewById(R.id.bestTarifSpeedView);

        try {
            /*TariffHandler tariffHandler = new TariffHandler(this);
            String myPlan[] = tariffHandler.getDefaultPlan();
            PlanComparer planComparer = new PlanComparer(this);
            String bestPlan[] = planComparer.getBestTariffPlan(myPlan[0]);*/
            String myPlan[] = {"WebSession M" , "3072 Mb" , "0.025 \u20ac", "4.99 €", "7.2 mb/s"};
            String bestPlan[] = {"WebSession L" , "5120 Mb", "0.0081 \u20ac", "24.99 €", "7.2 mb/s"};
            for(int i = 0; i < this.textViewBestPlan.length; i++){
                this.textViewMyPlan[i].setText(myPlan[i]);
                this.textViewBestPlan[i].setText(bestPlan[i]);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
