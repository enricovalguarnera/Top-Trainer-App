package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG = "RESULT";
    private Integer totalPercentage;
    private Integer whitePercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("EXTRA_MESSAGE");
        ArrayList<Object> abilities = (ArrayList<Object>) args.getSerializable("ABILITY");
        Log.i("RESULT", String.valueOf(abilities.get(0)));

        totalPercentage = getTotalPercentage();
        whitePercentage = getWhitePercentage();

        getBestTraining();
    }

    private void getBestTraining() {
        Log.i(LOG, "Get Best Training method");
    }

    private Integer getTotalPercentage() {
        Log.i(LOG, "Get Total Percentage method");
        Integer result = null;
        return result;
    }

    private Integer getWhitePercentage() {
        Log.i(LOG, "Get White Percentage method");
        Integer result = null;
        return result;
    }



}