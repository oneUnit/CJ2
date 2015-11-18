package com.oneunit.test.cj2;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


/**
 * Created by Abbas on 11/14/2015.
 */
public class QueueRequestHandler {
    Context context;
    final String url = "http://one-unit.com/reg_data.php";

    public QueueRequestHandler(Context context){
        this.context = context;
    }

    public void requestQueue(String val, String date){
        String deviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String requestStr = this.url + "?id=" + deviceID + "&val=" + val + "&date=" + date;
        new Connection().execute(requestStr);
    }

    private class Connection extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            try{
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet((String)params[0]);
                HttpResponse response = httpClient.execute(httpGet);
            }
            catch (IOException e){

            }
            return null;
        }
    }
}
