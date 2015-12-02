package com.oneunit.test.cj2;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by Abbas on 11/25/2015.
 */
public class PlanComparer {

    private Context context;
    private String[] defaultPlan;
    private BufferedReader bufferedReader;
    private Config config;
    private TariffHandler tariffHandler;
    private SimpleDateFormat dateFormat;

    public PlanComparer(Context context) throws IOException{
        this.context = context;
        this.config = new Config(context);
        this.dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.defaultPlan = new String[5];
        for(int i = 0; i < this.defaultPlan.length; i++){
            this.defaultPlan[i] = this.config.getValues()[i+2];
        }
        this.tariffHandler = new TariffHandler(context);
    }

    public String[] getBestTariffPlan(String tariffName){
        String result[] = null;
        int counter = 0;
        for(counter = 0; counter < this.tariffHandler.getTariffNames().length; counter++){
            if(tariffName.equals(this.tariffHandler.getTariffNames()[counter])){
                break;
            }
        }
        int bestPlanCounter = counter;

        for(int i = 0; i < this.tariffHandler.getTariffCostPerKBs().length; i++){
            if(Float.valueOf(tariffHandler.getTariffCostPerKBs()[bestPlanCounter]) < Float.valueOf(tariffHandler.getTariffCostPerKBs()[i])){
                    bestPlanCounter = i;
            }
        }
        return this.tariffHandler.getTariff(bestPlanCounter);
    }

   /* public boolean comparePlans(String[] myPlan, String[] plan){
        Calendar calendar = Calendar.getInstance();
        calendar.getTime();
        double dataPerDay[][] = TrafficInfoManager.getDataPerDay(this.context, calendar.getTime());

    }*/


    public String[] getDefaultPlan(){
        return this.defaultPlan;
    }
}
