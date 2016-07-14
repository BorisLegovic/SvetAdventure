package com.example.boris.svetadventure;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.splunk.mint.Mint;


/**
 * Created by boris on 03/01/2016.
 */
public class HowToPlay extends Activity {

   ViewPager viewPager;
   RelativeLayout deleteShared;
    TextView createPostCard;
    TextView numberiOfGifts;
   SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    LinearLayout firstInstructions;
    LinearLayout secondInstructions;
    LinearLayout thirdInstructions;
    LinearLayout forthInstructions;
    LinearLayout fifthInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stackactivity);
        Mint.initAndStartSession(HowToPlay.this, "f241ce49");
        numberiOfGifts = (TextView) findViewById(R.id.giftNumberId);
        deleteShared = (RelativeLayout) findViewById(R.id.delete_shared);
        Context context = getApplicationContext();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPref.edit();
        int gift = sharedPref.getInt("finalGift",0);
        numberiOfGifts.setText(String.valueOf(gift));
        deleteShared.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(),"Gifts removed",Toast.LENGTH_LONG).show();
                return false;
            }
        });


    }
}