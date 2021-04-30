package com.example.toptrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MotionEvent;
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
        if (viewPager.getCurrentItem() == 3) {
            buttonAllena.setEnabled(true);
            buttonNext.setEnabled(false);
        }
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
                case 1: return TrainingAbilityFragment.newInstance("Fragment Ability 1", position);
                case 2: return TrainingAbilityFragment.newInstance("Fragment Ability 2", position);
                case 3: return TrainingAbilityFragment.newInstance("Fragment Ability 3", position);
                default: return TrainingAbilityFragment.newInstance("Default",position);
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

}