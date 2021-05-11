package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
            buttonBack.setEnabled(false);
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            if (viewPager.getCurrentItem() == 0) {
                buttonBack.setEnabled(false);
            }
            if (viewPager.getCurrentItem() == 2) {
                buttonNext.setEnabled(true);
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
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Editare tutti le abilità prima di andare avanti. Il valore delle abilità non può essere zero", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (viewPager.getCurrentItem() == 2) {
            Boolean isValidStep = checkAbilitiesEditing();
            if (isValidStep) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                buttonAllena.setEnabled(true);
                buttonNext.setEnabled(false);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Editare tutti le abilità prima di andare avanti. Il valore delle abilità non può essere zero", Toast.LENGTH_LONG);
                toast.show();
            }
        }  // TODO la validazione del terzo step deve avvenire al click del bottone allena.
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