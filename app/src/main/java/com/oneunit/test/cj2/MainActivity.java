package com.oneunit.test.cj2;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
//import com.jjoe64.graphview.GraphView;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {


    private DrawerLayout drawerLayout; //DrawerLayout variable
    private ListView listView; //Lists of the DrawerLayout
    private ListView listView2;
    private ActionBarDrawerToggle drawerListener;
    private MyAdapter myAdapter;
    private MySecondAdapter mysecondAdapter;
    private Context context;

    private Config config;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // GraphView graph = (GraphView) findViewById(R.id.graph);
        this.config = null;

        final Button popupButton = (Button)findViewById(R.id.change_view);
        popupButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);
                popupMenu.inflate(R.menu.menu_main);
                popupMenu.show();
            }

        });


        listView = (ListView)findViewById(R.id.drawerList); //Initilize the list
        listView2 = (ListView)findViewById(R.id.drawerList2);
        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView2.setAdapter(mysecondAdapter);
        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu));
        listView.setOnItemClickListener(this);
        listView2.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout); //Initilizing the drawerLayout
        drawerListener = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                //Toast.makeText(MainActivity.this, " Drawer Opened ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // Toast.makeText(MainActivity.this, " Drawer Closed ", Toast.LENGTH_SHORT).show();
            }
        };
        drawerLayout.setDrawerListener(drawerListener);

        getSupportActionBar().setHomeButtonEnabled(true); //enables the home button...
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //enables the icon for the home button...

        // Update the action bar title with the TypefaceSpan instance


        this.context = this;
        Intent alarm = new Intent(this.context, PlanScheduler.class);
        boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmRunning){
            PendingIntent pIntent = PendingIntent.getBroadcast(this.context, 0, alarm, 0);
            AlarmManager alarmM = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmM.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 1000 * 60 * 60, pIntent);
        }


    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,myAdapter.menu[position]+"was selected",Toast.LENGTH_SHORT).show();
        selectItem(position);
        Fragment fragment = new UsageFragment();
        switch (position){
            case 0:
                fragment = new UsageFragment();
                break;
            case 1:
                fragment = new SettingsFragment();
                break;
            case 2:
                fragment = new ContactFragment();
                break;
            case 3:
                Intent intent = new Intent(this, ComparisonFragment.class);
                startActivity(intent);
                break;
            case 4:
                fragment = new BestTarifFragment();
                break;
            default:
                fragment = new UsageFragment();
                break;

        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent,fragment).commit();
        drawerLayout.closeDrawers();
    }

    public void selectItem(int position) {
        listView.setItemChecked(position, true);
        setTitle(myAdapter.menu[position]);
    }
    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //This method is used to open the drawerlayout when the hamburger icon is clicked....
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(drawerListener.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) { //This method is used if the somethings changes in the drawerlayout for eg screen size....
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }
}
