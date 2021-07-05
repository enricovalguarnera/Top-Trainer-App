package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG = "RESULT";
    private Integer totalPercentage;
    private Integer whitePercentage;

    private Map<String, String> abilityMap;             // mappa: key nome abilita, tipo di abilità (difesa, attacco, portiere, fisico e mentale)
    private Map<String, String> insertedMapAbility;     // mappa: key nome abilita, value valore inserito in fase di compilazione

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("EXTRA_MESSAGE");
        ArrayList<Object> abilities = (ArrayList<Object>) args.getSerializable("ABILITY");
        Boolean gkValue  = (Boolean) args.getBoolean("GK_VALUE");

        Log.i(LOG, String.valueOf(abilities.get(0)));
        Log.i(LOG, String.valueOf(gkValue));

        // calcolo le percentuali
        totalPercentage = getTotalPercentage();
        whitePercentage = getWhitePercentage();

        // questo metodo associa le abilità inserite (arraylist abilities) con i nomi corretti. Per costruzione l'assaylist differenzia le posizioni delle abilita in base al ruolo
        // Nel caso sia un portiere nelle prime 10 posizioni ci saranno le abilità da portiere e nelle ultime 5 le abilità di tipo Fisico e Mentale.
        // Nel caso sia un giocatore di ruolo nelle prime 5 posizioni ci saranno le abilità di tipo difesa, nelle altre 5 le abilità di tipo attacco, nelle ultime 5 le abilita di tipo Fisico e Mentale
//        associateInsertedAbilities(gkValue);

        // inizializzo la mappa delle abilità per tipo
        abilityMap = initAbilities();

        insertedMapAbility = associateInsertedAbilities(abilities, gkValue);

        // calcolo il miglior allenamento
        getBestTraining(insertedMapAbility);
    }


    private void getBestTraining(Map<String, String> insertedMapAbility, Boolean isGoalkeeper) {
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
    
    private Map<String, String> associateInsertedAbilities (ArrayList<Object> abilities, Boolean isGoalkeeper) {
        Map<String, String> result = new HashMap<>();
        List<String> playerAbilityModel;
        if (isGoalkeeper) {
            playerAbilityModel = getGKPlayerAbility();
        } else {
            playerAbilityModel = getNormalPlayerAbility();
        }

        for (int i=0; i < abilities.size(); i++) {
            result.put(playerAbilityModel.get(i), (String) abilities.get(i));
        }
        
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


    private List<String> getNormalPlayerAbility() {
        List<String> normalAbilities = new ArrayList<>();

        normalAbilities.add("contrasto");
        normalAbilities.add("marcatura");
        normalAbilities.add("posizionamento");
        normalAbilities.add("colpo_di_testa");
        normalAbilities.add("coraggio");

        normalAbilities.add("passaggio");
        normalAbilities.add("dribbling");
        normalAbilities.add("cross");
        normalAbilities.add("tiro");
        normalAbilities.add("finalizzazione");

        normalAbilities.add("forma");
        normalAbilities.add("forza");
        normalAbilities.add("aggressivita");
        normalAbilities.add("velocita");
        normalAbilities.add("creativita");
        return normalAbilities;
    }

    private List<String> getGKPlayerAbility() {
        List<String> normalAbilities = new ArrayList<>();

        normalAbilities.add("riflessi");
        normalAbilities.add("agilita");
        normalAbilities.add("anticipo");
        normalAbilities.add("scatto");
        normalAbilities.add("comunicazione");
        normalAbilities.add("lancio");
        normalAbilities.add("calcio");
        normalAbilities.add("pugni");
        normalAbilities.add("elevazione");
        normalAbilities.add("concentrazione");
        normalAbilities.add("forma");
        normalAbilities.add("forza");
        normalAbilities.add("aggressivita");
        normalAbilities.add("velocita");
        normalAbilities.add("creativita");
        return normalAbilities;
    }




}