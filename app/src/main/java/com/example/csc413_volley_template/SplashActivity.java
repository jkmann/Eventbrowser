
package com.example.csc413_volley_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity{

    private final int SPLASH_DISPLAY_LENGTH = 1000000000;


    /** Called when the activity is first created. */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);

        new Handler().postDelayed(new Runnable(){
            @Override
                    public void run()   {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);

/*
    Intent intent = new Intent(this,MainActivity.class);
    startActivity(intent);
    finish();
*/
    }
}
