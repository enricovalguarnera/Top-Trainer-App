package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptrainer.db.TopTrainerReaderContract;
import com.example.toptrainer.db.TopTrainerReaderDbHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainingActivity extends AppCompatActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 4;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private FragmentStateAdapter pagerAdapter;
    private Button buttonBack;
    private Button buttonAllena;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setUserInputEnabled(false);


        buttonAllena = (Button) findViewById(R.id.button_allena);
        buttonNext = (Button) findViewById(R.id.button_find_training_next);


        if (viewPager.getCurrentItem() == 0) {
            buttonBack = (Button) findViewById(R.id.button_find_training_back);
            buttonBack.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager.getCurrentItem() == 3) {
            buttonBack.setEnabled(true);
            buttonNext.setEnabled(false);
        }

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
            buttonBack.setEnabled(false);
            buttonAllena.setEnabled(false);
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            if (viewPager.getCurrentItem() == 0) {
                buttonBack.setEnabled(false);
            }
            if (viewPager.getCurrentItem() == 1) {
                buttonNext.setEnabled(true);
                buttonAllena.setEnabled(false);
            }
            if (viewPager.getCurrentItem() == 2) {
                buttonNext.setEnabled(true);
                buttonAllena.setEnabled(false);
            }
        }
    }

    public void goToNextStep (View view) {
        if (viewPager.getCurrentItem() == 0) {
            Boolean isValidStep = checkValidityData();
            if (isValidStep) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                buttonBack.setEnabled(true);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Selezionare almeno un ruolo", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (viewPager.getCurrentItem() == 1) {
            Boolean isValidStep = checkAbilitiesEditing();
            if (isValidStep) {
                setAbilityData(1);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Editare tutti le abilità prima di andare avanti. Il valore delle abilità non può essere zero", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (viewPager.getCurrentItem() == 2) {
            Boolean isValidStep = checkAbilitiesEditing();
            if (isValidStep) {
                setAbilityData(2);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                buttonAllena.setEnabled(true);
                buttonNext.setEnabled(false);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Editare tutti le abilità prima di andare avanti. Il valore delle abilità non può essere zero", Toast.LENGTH_LONG);
                toast.show();
            }
        }  // TODO la validazione del terzo step deve avvenire al click del bottone allena.
    }

    private void setAbilityData (Integer param) {

        EditText firstEditText = (EditText) findViewById(R.id.first_edit_text_ability);
        EditText secondEditText = (EditText) findViewById(R.id.second_edit_text_ability);
        EditText thirdEditText = (EditText) findViewById(R.id.third_edit_text_ability);
        EditText fourthEditText = (EditText) findViewById(R.id.fourth_edit_text_ability);
        EditText fivethEditText = (EditText) findViewById(R.id.five_edit_text_ability);

        List<String> abilitiList = new ArrayList<>();
        abilitiList.add(firstEditText.getText().toString());
        abilitiList.add(secondEditText.getText().toString());
        abilitiList.add(thirdEditText.getText().toString());
        abilitiList.add(fourthEditText.getText().toString());
        abilitiList.add(fivethEditText.getText().toString());

        if (param == 1) {
            SharedPreferences abilities1 = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = abilities1.edit();
            editor.putString("ABILITY_1", abilitiList.get(0));
            editor.putString("ABILITY_2", abilitiList.get(1));
            editor.putString("ABILITY_3", abilitiList.get(2));
            editor.putString("ABILITY_4", abilitiList.get(3));
            editor.putString("ABILITY_5", abilitiList.get(4));
            editor.apply();
        } else if (param == 2) {
            SharedPreferences abilities2 = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = abilities2.edit();
            editor.putString("ABILITY_6", abilitiList.get(0));
            editor.putString("ABILITY_7", abilitiList.get(1));
            editor.putString("ABILITY_8", abilitiList.get(2));
            editor.putString("ABILITY_9", abilitiList.get(3));
            editor.putString("ABILITY_10", abilitiList.get(4));
            editor.apply();
        }
    }

    public void goToTraining (View view) {
        if (viewPager.getCurrentItem() == 3) {
            Boolean isValid = checkAbilitiesEditing();
            if (isValid) {
                // salvo i dati della terza istanza del fragment ability
                EditText firstEditText = (EditText) findViewById(R.id.first_edit_text_ability);
                EditText secondEditText = (EditText) findViewById(R.id.second_edit_text_ability);
                EditText thirdEditText = (EditText) findViewById(R.id.third_edit_text_ability);
                EditText fourthEditText = (EditText) findViewById(R.id.fourth_edit_text_ability);
                EditText fivethEditText = (EditText) findViewById(R.id.five_edit_text_ability);

                List<String> abilities = new ArrayList<>();
                abilities = getAbilityList();
                abilities.add(firstEditText.getText().toString());
                abilities.add(secondEditText.getText().toString());
                abilities.add(thirdEditText.getText().toString());
                abilities.add(fourthEditText.getText().toString());
                abilities.add(fivethEditText.getText().toString());

                Intent intent = new Intent(this, ResultActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ABILITY", (Serializable) abilities);
                args.putBoolean("GK_VALUE", getGkValue());
                intent.putExtra("EXTRA_MESSAGE", args);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Editare tutti le abilità prima di andare avanti. Il valore delle abilità non può essere zero", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private Boolean getGkValue () {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Boolean gkValue = sharedPref.getBoolean("GK_VALUE", false);
        return gkValue;
    }

    private List<String> getAbilityList () {
        List<String> abilities = new ArrayList<>();

        SharedPreferences abilities1 = getPreferences(Context.MODE_PRIVATE);
        abilities.add(abilities1.getString("ABILITY_1", new String()));
        abilities.add(abilities1.getString("ABILITY_2", new String()));
        abilities.add(abilities1.getString("ABILITY_3", new String()));
        abilities.add(abilities1.getString("ABILITY_4", new String()));
        abilities.add(abilities1.getString("ABILITY_5", new String()));
        abilities.add(abilities1.getString("ABILITY_6", new String()));
        abilities.add(abilities1.getString("ABILITY_7", new String()));
        abilities.add(abilities1.getString("ABILITY_8", new String()));
        abilities.add(abilities1.getString("ABILITY_9", new String()));
        abilities.add(abilities1.getString("ABILITY_10", new String()));

//        EditText
        return abilities;
    }

    private Boolean checkBeforeTrain () {
        Boolean isValid = false;

        return isValid;
    }

    private Boolean checkAbilitiesEditing () {
        Boolean isValid = false;
        EditText editTextFirst = (EditText) findViewById(R.id.first_edit_text_ability);
        EditText editTextSecond = (EditText) findViewById(R.id.second_edit_text_ability);
        EditText editTextThird = (EditText) findViewById(R.id.third_edit_text_ability);
        EditText editTextFourth = (EditText) findViewById(R.id.fourth_edit_text_ability);
        EditText editTextFiveth = (EditText) findViewById(R.id.five_edit_text_ability);

        String firstAbility = editTextFirst.getText().toString();
        String secondAbility = editTextSecond.getText().toString();
        String thirdAbility = editTextThird.getText().toString();
        String fourthAbility = editTextFourth.getText().toString();
        String fivethAbility = editTextFiveth.getText().toString();

        if (!firstAbility.matches("") && !firstAbility.matches("0") && !secondAbility.matches("") && !secondAbility.matches("0")
                && !thirdAbility.matches("") && !thirdAbility.matches("0") && !fourthAbility.matches("") && !fourthAbility.matches("0")
                && !fivethAbility.matches("") && !fivethAbility.matches("0") ) {
            isValid = true;
        }
        return isValid;
    }

    private Boolean checkValidityData() {
        Boolean isValid = false;
        Integer count = 0;

        LinearLayout layout = (LinearLayout) findViewById(R.id.roles_vertical);
        for (int i=0; i<layout.getChildCount(); i++) {
            if (layout.getChildAt(i) instanceof LinearLayout) {
                LinearLayout linearLayout = (LinearLayout) layout.getChildAt(i);
                for (int j=0; j<linearLayout.getChildCount(); j++) {
                    if (linearLayout.getChildAt(j) instanceof Button) {
                        Button b =  (Button) linearLayout.getChildAt(j);
                        if (b.isSelected()) {
                            count += 1;
                        }
                    }
                }
            }
        }

        if (count >= 1) {
            isValid = true;
        }
        return isValid;
    }

    public void goToPreviousStep (View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        if (viewPager.getCurrentItem() == 0) {
            buttonBack.setEnabled(false);
        }
        if (viewPager.getCurrentItem() == 2) {
            buttonAllena.setEnabled(false);
            buttonNext.setEnabled(true);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return TrainingPlayerDataFragment.newInstance("Fragment Player Data","1");
                case 1: return TrainingAbilityFragment.newInstance(false, position);
                case 2: return TrainingAbilityFragment.newInstance(false, position);
                case 3: return TrainingAbilityFragment.newInstance(false, position);
                default: return TrainingAbilityFragment.newInstance(false,position);
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}