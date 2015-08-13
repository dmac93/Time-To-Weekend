package com.example.dominik.timetoweekend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends Activity {
    SharedPreferences userPreferences;
    SharedPreferences.Editor userPreferencesEditor;
    Spinner spinner, spinHour, spinMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pelny ekran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_settings);

        userPreferences = getSharedPreferences("Saved", 0); //wczytywanie ustawien
        spinnerItems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void clickToClock(View view){

        Intent intent = new Intent(this, CountingClock.class);

        savingValues();
        startActivity(intent);
        finish();

    }

    public void spinnerItems(){
        spinner = (Spinner) findViewById(R.id.spinner);
        spinHour = (Spinner) findViewById(R.id.spinnerHour);
        spinMinute = (Spinner) findViewById(R.id.spinnerMinute);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.daysArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterHour = ArrayAdapter.createFromResource(this,
                R.array.hoursArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterMinute = ArrayAdapter.createFromResource(this,
                R.array.minutesArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterHour.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterMinute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinHour.setAdapter(adapterHour);
        spinMinute.setAdapter(adapterMinute);
    }

    public void savingValues(){

        userPreferencesEditor = userPreferences.edit();

        userPreferencesEditor.putInt("day", (int)spinner.getSelectedItemId());
        userPreferencesEditor.putInt("hour", (int)spinHour.getSelectedItemId());
        userPreferencesEditor.putInt("minute", (int) spinMinute.getSelectedItemId());
        userPreferencesEditor.putLong("forProgressbar",0);

        userPreferencesEditor.commit();
    }

}
