package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        if (viewPager.getCurrentItem() == 0) {
            buttonBack = (Button) findViewById(R.id.button_find_training_back);
            buttonBack.setEnabled(false);
        }


//        buttonFindTraining = (Button) findViewById(R.id.button_find_training);
//        buttonFindTraining.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playerNameEditText = (EditText) findViewById(R.id.player_name);
//                Log.i("PLAYER NAME", playerNameEditText.getText().toString());
//            }
//        });



//        button = findViewById(R.id.button_calculate);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                editTextFirst = findViewById(R.id.first_ability);
//                firstAbility = editTextFirst.getText().toString();
//
//                editTextSecond = findViewById(R.id.second_ability);
//                secondAbility = editTextSecond.getText().toString();
//                if (TextUtils.isEmpty(firstAbility) || TextUtils.isEmpty(secondAbility)) {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Compila tutti i campi!", Toast.LENGTH_SHORT);
//                    toast.show();
//                } else {
//                    firstToShow = (TextView) findViewById(R.id.textToShowFirst);
//                    firstToShow.setText(String.valueOf(multiplication(Integer.parseInt(firstAbility), Integer.parseInt(secondAbility))));
//
//                    secondToShow = (TextView) findViewById(R.id.textToShowSecond);
//                    secondToShow.setText(String.valueOf(summing(Integer.parseInt(firstAbility), Integer.parseInt(secondAbility))));
//
//                    thirdToShow = (TextView) findViewById(R.id.textToShowThird);
//                    thirdToShow.setText(String.valueOf(multiplication(Integer.parseInt(firstAbility), Integer.parseInt(secondAbility)) * 10));
//                }
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            buttonBack.setEnabled(false);
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void goToNextStep (View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        if (viewPager.getCurrentItem() != 0) {
            buttonBack.setEnabled(true);
        }
    }

    public void goToPreviousStep (View view) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        if (viewPager.getCurrentItem() == 0) {
            buttonBack.setEnabled(false);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0: return TrainingPlayerDataFragment.newInstance("Fragment Player Data","");
                case 1: return TrainingAbilityFragment.newInstance("Fragment Ability 1", "");
                case 2: return TrainingAbilityFragment.newInstance("Fragment Ability 2", "");
                case 3: return TrainingAbilityFragment.newInstance("Fragment Ability 3", "");
                default: return TrainingAbilityFragment.newInstance("Default","");
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}