package com.iit.myactivtytracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("slim", "MainActivity. onCreate called");
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_background));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainFragment())
                .commit();

    }

    protected void onStart() {
        super.onStart();
        Log.v("slim", "MainActivity. onStart called");
    }

    protected void onPause() {
        super.onPause();
        Log.v("slim", "MainActivity. onPause called");
    }

    protected void onStop() {
        super.onStop();
        Log.v("slim", "MainActivity. onStop called");
    }

    protected void onResume() {
        super.onResume();
        Log.v("slim", "MainActivity. onResume called");
    }


    protected void onDestroy() {
        super.onDestroy();
        Log.v("slim", "MainActivity. onDestroy called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
//            startActivity(intent);
            Log.v("slim","click settings activity");
            return false;
        } else if (id == R.id.action_share) {
            Intent intent = new Intent("Intent.slim");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
