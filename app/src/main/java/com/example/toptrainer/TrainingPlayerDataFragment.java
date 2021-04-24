package com.example.toptrainer;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_data, container, false);
        // Inflate the layout for this fragment
        buttonGK = (Button) view.findViewById(R.id.button_gk);
        buttonGK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGK.setSelected(!buttonGK.isSelected());
//                if (buttonGK.isSelected()) {
//                    GradientDrawable gradientDrawable = new GradientDrawable();
//                    gradientDrawable.setColor(R.id.);
//                    gradientDrawable.setStroke(2, getResources().getColor(R.color.black));
//                    buttonGK.setBackground(gradientDrawable);
//                }
            }
        });

        buttonDC = (Button) view.findViewById(R.id.button_dc);
        buttonDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDC.setSelected(!buttonDC.isSelected());
            }
        });
        buttonDR = (Button) view.findViewById(R.id.button_dr);
        buttonDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDR.setSelected(!buttonDR.isSelected());
            }
        });
        buttonDL = (Button) view.findViewById(R.id.button_dl);
        buttonDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDL.setSelected(!buttonDL.isSelected());
            }
        });
        buttonDMC = (Button) view.findViewById(R.id.button_dmc);
        buttonDMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDMC.setSelected(!buttonDMC.isSelected());
            }
        });
        buttonMC = (Button) view.findViewById(R.id.button_mc);
        buttonMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMC.setSelected(!buttonMC.isSelected());
            }
        });
        buttonMR = (Button) view.findViewById(R.id.button_mr);
        buttonMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMR.setSelected(!buttonMR.isSelected());
            }
        });
        buttonML = (Button) view.findViewById(R.id.button_ml);
        buttonML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonML.setSelected(!buttonML.isSelected());
            }
        });
        buttonAMC = (Button) view.findViewById(R.id.button_amc);
        buttonAMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAMC.setSelected(!buttonAMC.isSelected());
            }
        });
        buttonAMR = (Button) view.findViewById(R.id.button_amr);
        buttonAMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAMR.setSelected(!buttonAMR.isSelected());
            }
        });
        buttonAML = (Button) view.findViewById(R.id.button_aml);
        buttonAML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAML.setSelected(!buttonAML.isSelected());
            }
        });
        buttonST = (Button) view.findViewById(R.id.button_st);
        buttonST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonST.setSelected(!buttonST.isSelected());
            }
        });

        return view;
    }
}