package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("EXTRA_MESSAGE");
        ArrayList<Object> abilities = (ArrayList<Object>) args.getSerializable("ABILITY");
        Log.i("RESULT", String.valueOf(abilities.get(0)));
    }
}