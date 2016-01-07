package com.oneunit.test.cj2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyAdapter extends BaseAdapter {
    private Context context;
    String[] menu;
    Activity a;
    int[] images = {R.drawable.usage, R.drawable.comparision, R.drawable.contact, R.drawable.settings};
    public MyAdapter(Context context){
        this.context=context;
        menu=context.getResources().getStringArray(R.array.menu);
    }
    @Override
    public int getCount() {
        return menu.length;
    }

    @Override
    public Object getItem(int position) {
        return menu[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        if(convertView==null){  //check if the view is null, if so then create it...
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row,parent,false);
        }else {
            row=convertView;
        }
        //initialize the views..
        TextView titleTextView = (TextView)row.findViewById(R.id.textView);
        ImageView titleImageView = (ImageView)row.findViewById(R.id.imageView);
        //assign the data...
        titleTextView.setText(menu[position]);
        titleImageView.setImageResource(images[position]);

        Typeface typeface = Typeface.createFromAsset( context.getAssets(), "fonts/Roboto-Bold.ttf");
        TextView textview=(TextView)row.findViewById(R.id.textView);
        textview.setTypeface(typeface);

        return row;
    }
}