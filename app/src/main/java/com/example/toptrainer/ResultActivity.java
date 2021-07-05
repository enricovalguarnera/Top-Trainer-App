package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG = "RESULT";
    private Integer totalPercentage;
    private Integer whitePercentage;

    private Map<String, String> abilityMap;

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

        abilityMap = initAbilities();
        Log.i(LOG, (String) abilityMap.get("colpo_di_testa"));
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


    private Map<String, String> initAbilities() {
        abilityMap = new HashMap<>();

        abilityMap.put("contrasto", "D");
        abilityMap.put("marcatura", "D");
        abilityMap.put("posizionamento", "D");
        abilityMap.put("colpo_di_testa", "D");
        abilityMap.put("coraggio", "D");

        abilityMap.put("passaggio", "A");
        abilityMap.put("dribbling", "A");
        abilityMap.put("cross", "A");
        abilityMap.put("tiro", "A");
        abilityMap.put("finalizzazione", "A");

        abilityMap.put("riflessi", "P");
        abilityMap.put("agilita", "P");
        abilityMap.put("anticipo", "P");
        abilityMap.put("scatto", "P");
        abilityMap.put("comunicazione", "P");
        abilityMap.put("lancio", "P");
        abilityMap.put("calcio", "P");
        abilityMap.put("pugni", "P");
        abilityMap.put("elevazione", "P");
        abilityMap.put("concentrazione", "P");

        abilityMap.put("forma", "F");
        abilityMap.put("forza", "F");
        abilityMap.put("aggressivita", "F");
        abilityMap.put("velocita", "F");
        abilityMap.put("creativita", "F");
        return abilityMap;
    }



}