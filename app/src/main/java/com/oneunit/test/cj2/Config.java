package com.oneunit.test.cj2;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Abbas on 11/24/2015.
 * Config File Structure:
 * first_use = 1|0
 * send_data = 1|0
 *WebSession Basic
 *200
 *0.025
 *4.99
 * 7.2
 */
public class Config {
    private final String FILE = "config.txt";
    private final String FILE_FORMAT = "UTF8";
    static final String CONFIG_FIRST_USE = "first_use";
    static final String CONFIG_SEND_DATA = "send_data";
    static final String CONFIG_PLAN_NAME = "plan_name";
    static final String CONFIG_PLAN_LIMIT = "plan_limit";
    static final String CONFIG_PLAN_COST_PER_MB = "plan_per_mb";
    static final String CONFIG_PLAN_COST = "cost";
    static final String CONFIG_PRICE = "price";
    private BufferedReader reader;
    private BufferedWriter writer;

    private Context context;

    public Config(Context context)throws IOException{
        this.context = context;
    }

    public boolean isEmpty(){
        try {
            this.reader = new BufferedReader(new InputStreamReader(context.openFileInput(FILE)));
            this.reader.close();
            return false;
        }
        catch (IOException e){
            try {
                this.writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILE, Context.MODE_PRIVATE)));
                this.writer.flush();
                this.writer.close();
                this.createConfigFile();
                return true;
            }
            catch (IOException e1){
                e.printStackTrace();
            }
        }
        return false;

    }

    public String getValue(String value) throws IOException{
        if(isEmpty()){
            throw new IOException("File Doesn't Exist");
        }
        String strArr[] = this.getValues();
        switch (value){
            case CONFIG_FIRST_USE:
                return strArr[0].split("=")[0].trim();
            case CONFIG_SEND_DATA:
                return strArr[1].split("=")[1].trim();
            default:
                throw new IOException();
        }
    }

    public String[] getValues() throws IOException{
        if(isEmpty()){
            throw new IOException("File Doesn't Exist");
        }
        this.reader = new BufferedReader(new InputStreamReader(context.openFileInput(FILE)));
        String result[] = new String[0];
        String temp = "";
        while((temp = this.reader.readLine()) != null){
            String tempResult[] = result;
            result = new String[result.length + 1];
            for(int i =0 ; i < tempResult.length; i++){
                result[i] = tempResult[i];
            }
            result[result.length - 1] = temp;
        }
        this.reader.close();
        return result;
    }

    public void createConfigFile() throws IOException{
        this.writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILE, Context.MODE_PRIVATE)));
        this.writer.write(Config.CONFIG_FIRST_USE + "=1");
        this.writer.newLine();
        this.writer.write(Config.CONFIG_SEND_DATA + "=1");
        TariffHandler tariffHandler = new TariffHandler(context);
        String defTariff[] = tariffHandler.getTariff(2);
        this.writer.newLine();
        for(int i = 0; i < defTariff.length; i++){
            this.writer.write(defTariff[i]);
            this.writer.newLine();
        }
        this.writer.flush();
        this.writer.close();
    }

    public void setValue(String confType, int value) throws Exception{
        if(value != 0 | value != 1){
            throw new Exception("Value must be either 0 or 1");
        }
        switch (confType){
            case Config.CONFIG_FIRST_USE:
                String values[] = this.getValues();
                this.writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILE, Context.MODE_PRIVATE)));
                this.writer.write(Config.CONFIG_FIRST_USE + "=" + value);
                this.writer.write(values[1]);
                return;
            case Config.CONFIG_SEND_DATA:
                values = this.getValues();
                this.writer = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(FILE, Context.MODE_PRIVATE)));
                this.writer.write(values[0]);
                this.writer.write(Config.CONFIG_SEND_DATA + "=" + value);
                return;
            default:
                throw new Exception("Wrong Configuration Name");
        }
    }


}
