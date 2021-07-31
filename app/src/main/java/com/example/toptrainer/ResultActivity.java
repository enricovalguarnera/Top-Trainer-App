package com.example.toptrainer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    private static final String LOG = "RESULT";
    private Float totalPercentage;
    private Integer whitePercentage;

    private TextView resultTraining;
    private TextView totalAverage;
    private TextView whiteAverage;

    private Map<String, String> abilityMap;             // mappa: key nome abilita, tipo di abilità (difesa, attacco, portiere, fisico e mentale)
    private Map<String, String> insertedMapAbility;     // mappa: key nome abilita, value valore inserito in fase di compilazione

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView popupPGPTextview;
    private Button popupPGPButton;
    private TextView bestPGPTextview;

    private ListView pgpListView;

    private String trainingName[];
    private Float trainingValue[];
    private String trainingColor[];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("EXTRA_MESSAGE");
        ArrayList<Object> abilities = (ArrayList<Object>) args.getSerializable("ABILITY");
        Boolean gkValue  = (Boolean) args.getBoolean("GK_VALUE");

        resultTraining = (TextView) findViewById(R.id.result_training);
        totalAverage = (TextView) findViewById(R.id.total_average);
        whiteAverage = (TextView) findViewById(R.id.white_average);
        bestPGPTextview = (TextView) findViewById(R.id.best_pgp);


        // calcolo la percentuale totale e mostro la percentuale sulla TextView
        totalPercentage = getTotalPercentage(abilities);
        if (totalPercentage != null && !Float.isNaN(totalPercentage)) {
            totalAverage.setText(String.valueOf(round(totalPercentage,2)) + "%");
        }

        // calcolo la percentuale delle abilita bianche e mostro la percentuale sulla TextView
        whitePercentage = getWhitePercentage();
        if (whitePercentage != null && !Float.isNaN(whitePercentage)) {
            whiteAverage.setText(String.valueOf(round(whitePercentage, 2)) + "%");
        }

        // questo metodo associa le abilità inserite (arraylist abilities) con i nomi corretti. Per costruzione l'assaylist differenzia le posizioni delle abilita in base al ruolo
        // Nel caso sia un portiere nelle prime 10 posizioni ci saranno le abilità da portiere e nelle ultime 5 le abilità di tipo Fisico e Mentale.
        // Nel caso sia un giocatore di ruolo nelle prime 5 posizioni ci saranno le abilità di tipo difesa, nelle altre 5 le abilità di tipo attacco, nelle ultime 5 le abilita di tipo Fisico e Mentale
        insertedMapAbility = associateInsertedAbilities(abilities, gkValue);


        // calcolo il miglior allenamento
        getBestTraining(insertedMapAbility, gkValue);
    }

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private void getBestTraining(Map<String, String> insertedMapAbility, Boolean isGoalkeeper) {
        Map<String, List<String>> trainingMap = getTrainingList();
        Map<String, Float> crescitePotenziali = new HashMap<>();

        // ciclo tutti gli allenamenti
        for (Map.Entry<String,List<String>> entry : trainingMap.entrySet()) {
            List<String> trainingAbilities = entry.getValue();
            String trainingName = entry.getKey();
            // ciclo le abilità all'interno del training i
            List<Integer> abilitiesValue = new ArrayList<>();
            for (int i=0; i < trainingAbilities.size() ; i++) {
                if (insertedMapAbility.get(trainingAbilities.get(i)) != null) {
                    abilitiesValue.add(Integer.valueOf(insertedMapAbility.get(trainingAbilities.get(i))));
                }
            }

            float average = calculateAverage(abilitiesValue);
            if (!Float.isNaN(average)) {
                crescitePotenziali.put(trainingName, 180 - calculateAverage(abilitiesValue));
            }
     
        }

        crescitePotenziali = sortPGP(crescitePotenziali);

        // questo è il punto dove settare gli array di stringhe
        trainingName = getFormattedArrayString(crescitePotenziali.keySet().toArray(new String[0]));
        trainingValue = getFormattedValues(crescitePotenziali.values().toArray(new Float[0]));
        trainingColor = getHexaColorForPGP(crescitePotenziali.values().toArray(new Float[0]));

        Map.Entry<String, Float> crescitaPotenzialeMax = getMaxValueFromList(crescitePotenziali);
        String trainingResultString = formatTrainingString(crescitaPotenzialeMax.getKey());

        //setto la crescita potenziale del migliore allenamento
        bestPGPTextview.setText(String.valueOf(crescitaPotenzialeMax.getValue()));
        // setto il colore e la stringa per visualizzare l'allenamento migliore e la bonta della crescita potenziale
        setTrainingColor(crescitaPotenzialeMax.getValue());
        resultTraining.setText(trainingResultString);
    }

    private Map<String, Float> sortPGP(Map<String, Float> crescitePotenziali) {
        List<String> mapKeys = new ArrayList<>(crescitePotenziali.keySet());
        List<Float> mapValues = new ArrayList<>(crescitePotenziali.values());
        Collections.sort(mapValues, Collections.reverseOrder());

        LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();

        Iterator<Float> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Float val = valueIt.next();

            String key = getKeyByValue(crescitePotenziali, val);
            sortedMap.put(key, val);
        }
        return sortedMap;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value == entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String[] getHexaColorForPGP(Float[] array) {
        String[] result = new String[array.length];

        for (int i=0; i<array.length; i++) {
            if (array[i] >= 100) {
                result[i] = "#00cc44";
            } else if (array[i] >= 80 && array[i] < 100) {
                result[i] = "#ffff00";
            } else if (array[i] >= 60 && array[i] < 80) {
                result[i] = "#ffbf00";
            } else if (array[i] >= 20 && array[i] < 60) {
                result[i] = "#ff8000";
            } else {
                result[i] = "#ff3300";
            }
        }

        return result;
    }

    private Float[] getFormattedValues(Float[] array) {
        Float[] result = new Float[array.length];

        DecimalFormat df = new DecimalFormat("##.##");
        df.setMaximumFractionDigits(2);

        for (int i=0; i<array.length; i++) {
            if (String.valueOf(array[i]) == "NaN") {
                result[i] = new Float(0);
            } else {
                result[i] = Float.valueOf(df.format(array[i]).replace(",","."));
            }
        }
        return result;
    }

    private String[] getFormattedArrayString(String[] array) {
        String[] result = new String[array.length];
        for (int i=0; i<array.length; i++) {
            if (array[i] != null) {
                result[i] = formatTrainingString(array[i]);
            }
        }
        return result;
    }

    private void setTrainingColor(Float value) {
        if (value >= 100) {
            resultTraining.setBackgroundColor(Color.parseColor("#00cc44"));
        } else if (value >= 80 && value < 100) {
            resultTraining.setBackgroundColor(Color.parseColor("#ffff00"));
        } else if (value >= 60 && value < 80) {
            resultTraining.setBackgroundColor(Color.parseColor("#ffbf00"));
        } else if (value >= 20 && value < 60) {
            resultTraining.setBackgroundColor(Color.parseColor("#ff8000"));
        } else {
            resultTraining.setBackgroundColor(Color.parseColor("#ff3300"));
        }
    }

    private String formatTrainingString(String key) {
        String result = "";
        String [] arraySplitString = null;
        arraySplitString = key.split("_");
        for (int i=0; i<arraySplitString.length; i++) {
            result = result + " " + arraySplitString[i].substring(0, 1).toUpperCase() + arraySplitString[i].substring(1);
        }
        return result;
    }

    private Map.Entry<String, Float> getMaxValueFromList(Map<String, Float> crescitePotenziali) {
        Map.Entry<String, Float> maxEntry = null;
        for (Map.Entry<String, Float> entry : crescitePotenziali.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0 ) {
                if (entry.getValue() != null && !Float.isNaN(entry.getValue())) {
                    maxEntry = entry;
                }
            }
        }
        return maxEntry;
    }

    // metodo per il calcolo della media
    private float calculateAverage (List<Integer> list) {
        float sum = 0;
        int i=0;

        while(i < list.size()) {
            sum += list.get(i);
            i++;
        }

        //compute average
        float average = (sum / list.size());
        return average;
    }



    private Float getTotalPercentage(ArrayList<Object> abilities) {
        Log.i(LOG, "Get Total Percentage method");
        float sum = 0;
        int i=0;

        while(i < abilities.size()) {
            sum += Float.parseFloat(String.valueOf(abilities.get(i)));
            i++;
        }

        //compute average
        float average = (sum / abilities.size());
        return average;
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

    // PGP: potential growth of the player
    public void createNewPGPListDialog(View view) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View pgpListPopupView = getLayoutInflater().inflate(R.layout.pgp_popup_layout, null);

        //popupPGPTextview = (TextView) pgpListPopupView.findViewById(R.id.textview_pgp_popup_title);
        popupPGPButton = (Button) pgpListPopupView.findViewById(R.id.button_pgp_popup);

        dialogBuilder.setView(pgpListPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        // inizializzo la list view per visualizzare la lista degli allenamenti
        pgpListView = (ListView) pgpListPopupView.findViewById(R.id.pgp_list_view);
        PgpAdapter pgpAdapter = new PgpAdapter(this, trainingName, trainingValue, trainingColor);
        pgpListView.setAdapter(pgpAdapter);

        popupPGPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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


    private Map<String, List<String>> getTrainingList () {
        Map<String, List<String>> result = new HashMap<>();


        // ALLENAMENTI DI ATTACCO
        List<String> unoDueTiro = new ArrayList<>();
        unoDueTiro.add("velocita");
        unoDueTiro.add("passaggio");
        unoDueTiro.add("tiro");
        unoDueTiro.add("anticipo");
        result.put("uno_due_tiro", unoDueTiro);

        List<String> contropiediVeloci = new ArrayList<>();
        contropiediVeloci.add("creativita");
        contropiediVeloci.add("passaggio");
        contropiediVeloci.add("cross");
        contropiediVeloci.add("finalizzazione");
        contropiediVeloci.add("comunicazione");
        result.put("contropiedi_veloci", contropiediVeloci);

        List<String> controlloPalla = new ArrayList<>();
        controlloPalla.add("creativita");
        controlloPalla.add("dribbling");
        controlloPalla.add("colpo_di_testa");
        controlloPalla.add("concentrazione");
        result.put("controllo_palla", controlloPalla);

        List<String> tecnicaDiTiro = new ArrayList<>();
        tecnicaDiTiro.add("forza");
        tecnicaDiTiro.add("tiro");
        tecnicaDiTiro.add("finalizzazione");
        tecnicaDiTiro.add("riflessi");
        tecnicaDiTiro.add("agilita");
        result.put("tecnica_di_tiro", tecnicaDiTiro);

        List<String> calciPiazzati = new ArrayList<>();
        calciPiazzati.add("cross");
        calciPiazzati.add("tiro");
        calciPiazzati.add("marcatura");
        calciPiazzati.add("colpo_di_testa");
        calciPiazzati.add("scatto");
        result.put("calci_piazzati", calciPiazzati);

        List<String> slalom = new ArrayList<>();
        slalom.add("forma");
        slalom.add("velocita");
        slalom.add("passaggio");
        slalom.add("dribbling");
        result.put("slalom", slalom);

        List<String> giocoSulleFasce = new ArrayList<>();
        giocoSulleFasce.add("cross");
        giocoSulleFasce.add("tiro");
        giocoSulleFasce.add("finalizzazione");
        giocoSulleFasce.add("colpo_di_testa");
        giocoSulleFasce.add("pugni");
        result.put("gioco_sulle_fasce", giocoSulleFasce);

        List<String> duelloColPortiere = new ArrayList<>();
        duelloColPortiere.add("dribbling");
        duelloColPortiere.add("finalizzazione");
        duelloColPortiere.add("contrasto");
        duelloColPortiere.add("anticipo");
        duelloColPortiere.add("scatto");
        result.put("duello_col_portiere", duelloColPortiere);


        // ALLENAMENTI DI DIFESA
        List<String> pressing = new ArrayList<>();
        pressing.add("aggressivita");
        pressing.add("contrasto");
        pressing.add("marcatura");
        pressing.add("posizionamento");
        result.put("pressing", pressing);

        List<String> torello = new ArrayList<>();
        torello.add("forma");
        torello.add("aggressivita");
        torello.add("passaggio");
        torello.add("contrasto");
        torello.add("posizionamento");
        result.put("torello", torello);

        List<String> allenamentoPortiere = new ArrayList<>();
        allenamentoPortiere.add("riflessi");
        allenamentoPortiere.add("agilita");
        allenamentoPortiere.add("lancio");
        allenamentoPortiere.add("calcio");
        allenamentoPortiere.add("elevazione");
        result.put("allenamento_portiere", allenamentoPortiere);

        List<String> usaLaTesta = new ArrayList<>();
        usaLaTesta.add("creativita");
        usaLaTesta.add("passaggio");
        usaLaTesta.add("posizionamento");
        usaLaTesta.add("colpo_di_testa");
        result.put("usa_la_testa", usaLaTesta);

        List<String> fermaLattaccante = new ArrayList<>();
        fermaLattaccante.add("forza");
        fermaLattaccante.add("dribbling");
        fermaLattaccante.add("contrasto");
        fermaLattaccante.add("marcatura");
        fermaLattaccante.add("coraggio");
        result.put("ferma_l'_attaccante", fermaLattaccante);

        List<String> difesaSuiCross = new ArrayList<>();
        difesaSuiCross.add("cross");
        difesaSuiCross.add("marcatura");
        difesaSuiCross.add("colpo_di_testa");
        difesaSuiCross.add("coraggio");
        difesaSuiCross.add("elevazione");
        result.put("difesa_sui_cross", difesaSuiCross);

        List<String> analisiDeiVideo = new ArrayList<>();
        analisiDeiVideo.add("creativita");
        analisiDeiVideo.add("posizionamento");
        analisiDeiVideo.add("coraggio");
        analisiDeiVideo.add("comunicazione");
        result.put("analisi_dei_video", analisiDeiVideo);

        List<String> lineaDifensiva = new ArrayList<>();
        lineaDifensiva.add("marcatura");
        lineaDifensiva.add("posizionamento");
        lineaDifensiva.add("comunicazione");
        lineaDifensiva.add("concentrazione");
        result.put("linea_difensiva", lineaDifensiva);

        // ALLENAMENTI DI FISICO E MENTALE
        List<String> riscaldamento = new ArrayList<>();
        riscaldamento.add("forma");
        riscaldamento.add("aggressivita");
        riscaldamento.add("colpo_di_testa");
        riscaldamento.add("riflessi");
        result.put("riscaldamento", riscaldamento);

        List<String> stretching = new ArrayList<>();
        stretching.add("forma");
        stretching.add("forza");
        stretching.add("velocita");
        stretching.add("agilita");
        result.put("stretching", stretching);

        List<String> scattiInVelocita = new ArrayList<>();
        scattiInVelocita.add("forma");
        scattiInVelocita.add("velocita");
        scattiInVelocita.add("dribbling");
        scattiInVelocita.add("scatto");
        result.put("scatti_in_velocita", scattiInVelocita);

        List<String> cariocaConScale = new ArrayList<>();
        cariocaConScale.add("aggressivita");
        cariocaConScale.add("velocita");
        cariocaConScale.add("agilita");
        cariocaConScale.add("concentrazione");
        result.put("carioca_con_scale", cariocaConScale);

        List<String> corsaSullaDistanza = new ArrayList<>();
        corsaSullaDistanza.add("forma");
        corsaSullaDistanza.add("velocita");
        corsaSullaDistanza.add("concentrazione");
        result.put("corsa_sulla_distanza", corsaSullaDistanza);

        List<String> palestra = new ArrayList<>();
        palestra.add("forma");
        palestra.add("forza");
        palestra.add("lancio");
        palestra.add("calcio");
        result.put("palestra", palestra);

        List<String> testNavetta = new ArrayList<>();
        testNavetta.add("forza");
        testNavetta.add("velocita");
        testNavetta.add("coraggio");
        testNavetta.add("agilita");
        result.put("test_navetta", testNavetta);

        List<String> saltoAOstacoli = new ArrayList<>();
        saltoAOstacoli.add("aggressivita");
        saltoAOstacoli.add("velocita");
        saltoAOstacoli.add("coraggio");
        saltoAOstacoli.add("calcio");
        result.put("salto_a_ostacoli", saltoAOstacoli);

        return result;
    }

    class PgpAdapter extends ArrayAdapter<String> {
        Context context;
        String trainings[];
        Float pgpValues[];
        String colors[];

        PgpAdapter (Context context, String trainings[], Float pgpValues[], String colors[]) {
            super(context, R.layout.pgp_list_item, R.id.training_name_pgp_list_item, trainings);
            this.context = context;
            this.trainings = trainings;
            this.pgpValues = pgpValues;
            this.colors = colors;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.pgp_list_item, parent, false);
            TextView trainingNameTextview = row.findViewById(R.id.training_name_pgp_list_item);
            TextView pgpValuesTextview = row.findViewById(R.id.training_pgp_value_list_item);
            TextView trainingColorTextView = row.findViewById(R.id.training_color_pgp_list_item);

            trainingNameTextview.setText(trainings[position]);
            pgpValuesTextview.setText(String.valueOf(pgpValues[position]));
            trainingColorTextView.setBackgroundColor(Color.parseColor(colors[position]));

            return row;
        }
    }

}