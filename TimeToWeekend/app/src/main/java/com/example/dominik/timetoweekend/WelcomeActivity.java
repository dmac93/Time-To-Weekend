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
import android.widget.Button;


public class WelcomeActivity extends Activity {
    private SharedPreferences userPreferences;
    private Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pelny ekran
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome);

        userPreferences = getSharedPreferences("Saved", 0); //wczytywanie ustawien
        nextbutton = (Button) findViewById(R.id.clickNext);

        if (userPreferences.getInt("day",9)==9){
            nextbutton.setText(R.string.clickNext);
        }else{
            nextbutton.setText(R.string.clickNext2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);

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

    public void clickNext(View view){

        if (userPreferences.getInt("day",9)==9){
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this, CountingClock.class);
            startActivity(intent);
            finish();
        }
    }
}
