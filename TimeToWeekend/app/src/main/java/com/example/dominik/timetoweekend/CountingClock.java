package com.example.dominik.timetoweekend;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class CountingClock extends Activity {
    SharedPreferences userPreferences;
    SharedPreferences.Editor userPreferencesEditor;
    long time;
    TextView topClock,bottomClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pe³ny ekran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_counting_clock);

        userPreferences = getSharedPreferences("Saved", 0); //wczytywanie ustwieñ
        topClock= (TextView) findViewById(R.id.topClock);
        bottomClock = (TextView) findViewById(R.id.bottomClock);

        countTimeLeft();

        final CounterClass timer = new CounterClass(time, 1000);
        timer.start();

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

    public void countTimeLeft(){

        int min = userPreferences.getInt("minute", 0);
        int hour = userPreferences.getInt("hour",0);
        int day = userPreferences.getInt("day",0);

        Calendar CurrentDateTime = Calendar.getInstance();

        int csec, cmin, chour, cday, pom;

        csec = CurrentDateTime.get(Calendar.SECOND);
        cmin = CurrentDateTime.get(Calendar.MINUTE);
        chour = CurrentDateTime.get(Calendar.HOUR_OF_DAY);
        cday = CurrentDateTime.get(Calendar.DAY_OF_WEEK)-1;

        time= TimeUnit.DAYS.toMillis((long)(day-cday))+TimeUnit.HOURS.toMillis((long)(hour-chour))+
                TimeUnit.MINUTES.toMillis((long)(min-cmin))-TimeUnit.SECONDS.toMillis((long)csec);

        if (time<0) time=time+TimeUnit.DAYS.toMillis(7);

        userPreferencesEditor = userPreferences.edit();
        userPreferencesEditor.putLong("forProgressbar", time);
        userPreferencesEditor.commit();





    }

    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;


            String toTopClock = String.format("%02d dni %02d godzin", TimeUnit.MILLISECONDS.toDays(millis),
                    TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)));
            String toBottomClock = String.format("%02d minut %02d sekund",
                    TimeUnit.MILLISECONDS.toMinutes(millis)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            topClock.setText(toTopClock);
            bottomClock.setText(toBottomClock);

            }

        @Override
        public void onFinish() {

        }
    }






}
