
package com.example.toptrainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toptrainer.db.TopTrainerReaderContract;
import com.example.toptrainer.db.TopTrainerReaderDbHelper;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG_ADMOB = "---AD MOB----";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createPersonalizeAd();
            }
        });


        //Connection to database and writing ...
        TopTrainerReaderDbHelper dbHelper = new TopTrainerReaderDbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TopTrainerReaderContract.AbilityEntry.COLUMN_ABILITY_NAME, "Tiro");
        values.put(TopTrainerReaderContract.AbilityEntry.COLUMN_ABILITY_TYPE, 1);
        values.put(TopTrainerReaderContract.AbilityEntry.COLUMN_ABILITY_VALUE, 180);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TopTrainerReaderContract.AbilityEntry.TABLE_NAME, null, values);
    }

    private void createPersonalizeAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        createInterstitialAd(adRequest);
    }

    public void createInterstitialAd(AdRequest adRequest) {
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    // The mInterstitialAd reference will be null until
                    // an ad is loaded.
                    mInterstitialAd = interstitialAd;
                    Log.i(TAG_ADMOB, "onAdLoaded");

                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when fullscreen content is dismissed.
                            Log.d("TAG", "The ad was dismissed.");
                            Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when fullscreen content failed to show.
                            Log.d("TAG", "The ad failed to show.");
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when fullscreen content is shown.
                            // Make sure to set your reference to null so you don't
                            // show it a second time.
                            mInterstitialAd = null;
                            Log.d("TAG", "The ad was shown.");
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.i(TAG_ADMOB, loadAdError.getMessage());
                    mInterstitialAd = null;
                }
            }
        );
    }

    public void findTraining(View view) {
//
        // Eseguo pubblicità AdMob
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            Log.d(TAG_ADMOB, "The interstitial ad wasn't ready yet.");
            Intent intent = new Intent(this, TrainingActivity.class);
            startActivity(intent);
        }

    }

    public void workInProgress (View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Work In Progress", Toast.LENGTH_SHORT);
        toast.show();
    }

}