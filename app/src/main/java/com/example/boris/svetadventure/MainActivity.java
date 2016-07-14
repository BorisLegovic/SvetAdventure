package com.example.boris.svetadventure;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.splunk.mint.Mint;

/**
 * Created by boris on 29/12/2015.
 */



public class MainActivity extends Activity {

    RelativeLayout svetvincenatButton;
    RelativeLayout workoutParkButton;
    RelativeLayout yourStack;
    RelativeLayout townshipButton;
    TextView route;
    TextView savicenta;
    TextView workoutpark;
    TextView yourstacktxt;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        Mint.initAndStartSession(MainActivity.this, "f241ce49");
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.medievalm);
        mp.start();
        sharedPreferences = getSharedPreferences("tset", Context.MODE_PRIVATE);
        Typeface impactFont = Typeface.createFromAsset(getAssets(), "fonts/helvetica.otf");

        route = (TextView) findViewById(R.id.townhsiptext);
        savicenta = (TextView) findViewById(R.id.svetvincenattext);
        workoutpark = (TextView) findViewById(R.id.workoutparktext);
        yourstacktxt = (TextView) findViewById(R.id.test_button_text2);

        route.setTypeface(impactFont);
        savicenta.setTypeface(impactFont);
        workoutpark.setTypeface(impactFont);
        yourstacktxt.setTypeface(impactFont);

        svetvincenatButton = (RelativeLayout) findViewById(R.id.townshipid);
         yourStack = (RelativeLayout) findViewById(R.id.yourstackid);
        workoutParkButton = (RelativeLayout) findViewById(R.id.workoutparkid);
        townshipButton = (RelativeLayout) findViewById(R.id.POSTCARD);

        svetvincenatButton.setAlpha(0.7f);
        yourStack.setAlpha(0.7f);
        workoutParkButton.setAlpha(0.7f);
        townshipButton.setAlpha(0.7f);

       workoutParkButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentPark = new Intent(MainActivity.this, WorkoutPark.class);
               startActivity(intentPark);
               }
       });

        yourStack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HowToPlay.class);
                startActivity(intent);
                }
        });


        svetvincenatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
                }
        });
        townshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreatePostcard.class);
                startActivity(intent);
            }
        });


    }
}
