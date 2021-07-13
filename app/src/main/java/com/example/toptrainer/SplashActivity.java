package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        // set timer
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < 1000) {
            //perform db poll/check
            elapsedTime = (new Date()).getTime() - startTime;
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}