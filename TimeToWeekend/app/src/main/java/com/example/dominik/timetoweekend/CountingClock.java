package com.example.dominik.timetoweekend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class CountingClock extends Activity {
    SharedPreferences userPreferences;
    SharedPreferences.Editor userPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pe³ny ekran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_counting_clock);

        userPreferences = getSharedPreferences("Saved", 0); //wczytywanie ustwieñ

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_counting_clock, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void returnToSettings(View view){

        userPreferencesEditor = userPreferences.edit();

        userPreferencesEditor.putInt("day", 9);
        userPreferencesEditor.putInt("hour", 0);
        userPreferencesEditor.putInt("minute", 0);

        userPreferencesEditor.commit();

        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        finish();
    }



}
