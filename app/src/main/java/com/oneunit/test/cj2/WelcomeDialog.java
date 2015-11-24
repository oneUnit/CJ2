package com.oneunit.test.cj2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;

/**
 * Created by Abbas on 11/24/2015.
 */
public class WelcomeDialog extends Dialog {

    private Button button;
    private Spinner spinner;
    private String tariffNames[];
    public WelcomeDialog(Context context) {
        super(context);
        setOwnerActivity((Activity) context);
        try {
            this.tariffNames = new TariffHandler(context).getTariffNames();
        }
        catch (IOException e){
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.welcome_dialog);
        this.setCanceledOnTouchOutside(false);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getOwnerActivity(),
                R.layout.support_simple_spinner_dropdown_item, this.tariffNames);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        this.spinner = (Spinner) findViewById(R.id.welcome_spinner);
        this.spinner.setAdapter(arrayAdapter);
        this.spinner.setPrompt("Choose Tariff");
        this.spinner.setSelection(0);
    }
}
