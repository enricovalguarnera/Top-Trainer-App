package com.example.toptrainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingPlayerDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingPlayerDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Integer MAX_SELECTED_BUTTON = 3;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //    Roles Buttons
    private Button buttonGK;
    private Button buttonDC;
    private Button buttonDR;
    private Button buttonDL;
    private Button buttonDMC;
    private Button buttonMC;
    private Button buttonMR;
    private Button buttonML;
    private Button buttonAMC;
    private Button buttonAMR;
    private Button buttonAML;
    private Button buttonST;

    public static Integer countSelected;


    public TrainingPlayerDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainingPlayerDataFragment newInstance(String param1, String param2) {
        TrainingPlayerDataFragment fragment = new TrainingPlayerDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            countSelected = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player_data, container, false);

        buttonGK = (Button) view.findViewById(R.id.button_gk);
        buttonGK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isValid = updateCountForGK(buttonGK.isSelected());
                if (isValid) {
                    if (!buttonGK.isSelected()) {
                        resetAllButtons();
                    }
                    buttonGK.setSelected(!buttonGK.isSelected());
                }
            }

            private void resetAllButtons() {
                LinearLayout layout = (LinearLayout) view.findViewById(R.id.roles_vertical);
                for (int i=0; i<layout.getChildCount(); i++) {
                    if (layout.getChildAt(i) instanceof LinearLayout) {
                        LinearLayout linearLayout = (LinearLayout) layout.getChildAt(i);
                        for (int j=0; j<linearLayout.getChildCount(); j++) {
                            if (linearLayout.getChildAt(j) instanceof Button && linearLayout.getChildAt(i).getId() != R.id.button_gk) {
                                Button b =  (Button) linearLayout.getChildAt(j);
                                b.setSelected(false);
                                TrainingPlayerDataFragment.countSelected = 1;
                            }
                        }
                    }
                }
            }
        });

        buttonDC = (Button) view.findViewById(R.id.button_dc);
        buttonDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonDC.isSelected());
                if (isValid) {
                    buttonDC.setSelected(!buttonDC.isSelected());
                }
            }
        });
        buttonDR = (Button) view.findViewById(R.id.button_dr);
        buttonDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonDR.isSelected());
                if (isValid) {
                    buttonDR.setSelected(!buttonDR.isSelected());
                }
            }
        });
        buttonDL = (Button) view.findViewById(R.id.button_dl);
        buttonDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonDL.isSelected());
                if (isValid) {
                    buttonDL.setSelected(!buttonDL.isSelected());
                }
            }
        });
        buttonDMC = (Button) view.findViewById(R.id.button_dmc);
        buttonDMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonDMC.isSelected());
                if (isValid) {
                    buttonDMC.setSelected(!buttonDMC.isSelected());
                }
            }
        });
        buttonMC = (Button) view.findViewById(R.id.button_mc);
        buttonMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonMC.isSelected());
                if (isValid) {
                    buttonMC.setSelected(!buttonMC.isSelected());
                }
            }
        });
        buttonMR = (Button) view.findViewById(R.id.button_mr);
        buttonMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonMR.isSelected());
                if (isValid) {
                    buttonMR.setSelected(!buttonMR.isSelected());
                }
            }
        });
        buttonML = (Button) view.findViewById(R.id.button_ml);
        buttonML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonML.isSelected());
                if (isValid) {
                    buttonML.setSelected(!buttonML.isSelected());
                }
            }
        });
        buttonAMC = (Button) view.findViewById(R.id.button_amc);
        buttonAMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonAMC.isSelected());
                if (isValid) {
                    buttonAMC.setSelected(!buttonAMC.isSelected());
                }
            }
        });
        buttonAMR = (Button) view.findViewById(R.id.button_amr);
        buttonAMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonAMR.isSelected());
                if (isValid) {
                    buttonAMR.setSelected(!buttonAMR.isSelected());
                }
            }
        });
        buttonAML = (Button) view.findViewById(R.id.button_aml);
        buttonAML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonAML.isSelected());
                if (isValid) {
                    buttonAML.setSelected(!buttonAML.isSelected());
                }
            }
        });
        buttonST = (Button) view.findViewById(R.id.button_st);
        buttonST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonGK.isSelected()) {
                    buttonGK.setSelected(false);
                    TrainingPlayerDataFragment.countSelected = 0;
                }
                Boolean isValid = updateCount(buttonST.isSelected());
                if (isValid) {
                    buttonST.setSelected(!buttonST.isSelected());
                }
            }
        });


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        // Logica salvataggio stato bottone GK all'onResume dei fragment. Viene fatta all'onPause per ottenere il valore aggiornato del bottone GK prima che la view cambi
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("GK_VALUE", buttonGK.isSelected());

        // ruoli aggionati
        editor.putBoolean("DC_VALUE", buttonDC.isSelected());
        editor.putBoolean("DR_VALUE", buttonDR.isSelected());
        editor.putBoolean("DL_VALUE", buttonDL.isSelected());
        editor.putBoolean("DMC_VALUE", buttonDMC.isSelected());
        editor.putBoolean("MC_VALUE", buttonMC.isSelected());
        editor.putBoolean("MR_VALUE", buttonMR.isSelected());
        editor.putBoolean("ML_VALUE", buttonML.isSelected());
        editor.putBoolean("AMC_VALUE", buttonAMC.isSelected());
        editor.putBoolean("AMR_VALUE", buttonAMR.isSelected());
        editor.putBoolean("AML_VALUE", buttonAML.isSelected());
        editor.putBoolean("ST_VALUE", buttonST.isSelected());

        editor.apply();
    }

    // this function take state button and return updated count of selected buttons
    private Boolean updateCount (Boolean isSelected) {
        Boolean isValid = false;
        if (!isSelected) {
            if (TrainingPlayerDataFragment.countSelected < MAX_SELECTED_BUTTON) {
                TrainingPlayerDataFragment.countSelected += 1;
                isValid = true;
            }
        } else {
            TrainingPlayerDataFragment.countSelected -= 1;
            isValid = true;
        }
        return isValid;
    }
    private Boolean updateCountForGK (Boolean isSelected) {
        Boolean isValid = false;
        if (!isSelected) {
            if (TrainingPlayerDataFragment.countSelected < MAX_SELECTED_BUTTON + 1) {
                TrainingPlayerDataFragment.countSelected += 1;
                isValid = true;
            }
        } else {
            TrainingPlayerDataFragment.countSelected -= 1;
            isValid = true;
        }
        return isValid;
    }

}