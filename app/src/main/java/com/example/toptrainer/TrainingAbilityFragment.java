package com.example.toptrainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainingAbilityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainingAbilityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Boolean isGKSelected;
    private Integer position;

    private Button buttonGK;
    private TextView textViewCategory;
    private TextView textViewFirstAbility;
    private TextView textViewSecondAbility;
    private TextView textViewThirdAbility;
    private TextView textViewFourthAbility;
    private TextView textViewFivethAbility;

    private EditText firstEditText;
    private EditText secondEditText;
    private EditText thirdEditText;
    private EditText fourthEditText;
    private EditText fivethEditText;

    public TrainingAbilityFragment() {
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
    public static TrainingAbilityFragment newInstance(Boolean param1, Integer param2) {
        TrainingAbilityFragment fragment = new TrainingAbilityFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            isGKSelected = getArguments().getBoolean(ARG_PARAM1);
            position = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_training_ability, container, false);
//        buttonGK = (Button) getActivity().findViewById(R.id.button_gk);
//        if (buttonGK != null) {
//            isGKSelected = buttonGK.isSelected();
//        }
        textViewCategory = (TextView) view.findViewById(R.id.category_heading);
        textViewFirstAbility = (TextView) view.findViewById(R.id.first_text_view_ability);
        textViewSecondAbility = (TextView) view.findViewById(R.id.second_text_view_ability);
        textViewThirdAbility = (TextView) view.findViewById(R.id.third_text_view_ability);
        textViewFourthAbility = (TextView) view.findViewById(R.id.fourth_text_view_ability);
        textViewFivethAbility = (TextView) view.findViewById(R.id.fiveth_text_view_ability);

        firstEditText = (EditText) view.findViewById(R.id.first_edit_text_ability);
        secondEditText = (EditText) view.findViewById(R.id.second_edit_text_ability);
        thirdEditText = (EditText) view.findViewById(R.id.third_edit_text_ability);
        fourthEditText = (EditText) view.findViewById(R.id.fourth_edit_text_ability);
        fivethEditText = (EditText) view.findViewById(R.id.five_edit_text_ability);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Logica salvataggio stato bottone GK all'onResume dei fragment
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        Boolean gkValue = sharedPref.getBoolean("GK_VALUE", false);



        // TODO: controllare che al resume venga preso il valore corretto aggiornato. Il problema è che il fragment non ripassa dal metodo onCreateView non istanziando
        if (gkValue) {
            if (position == 1) {
                textViewCategory.setText(R.string.heading_ability_type_gk_1);
                textViewFirstAbility.setText(R.string.gk_ability_riflessi);
                textViewSecondAbility.setText(R.string.gk_ability_agilita);
                textViewThirdAbility.setText(R.string.gk_ability_anticipo);
                textViewFourthAbility.setText(R.string.gk_ability_scatto);
                textViewFivethAbility.setText(R.string.gk_ability_comunicazione);
            } else if (position == 2) {
                textViewCategory.setText(R.string.heading_ability_type_gk_2);
                textViewFirstAbility.setText(R.string.gk_ability_lancio);
                textViewSecondAbility.setText(R.string.gk_ability_calcio);
                textViewThirdAbility.setText(R.string.gk_ability_pugni);
                textViewFourthAbility.setText(R.string.gk_ability_elevazione);
                textViewFivethAbility.setText(R.string.gk_ability_concentrazione);
            } else if (position == 3) {
                textViewCategory.setText(R.string.heading_ability_type_physic);
                textViewFirstAbility.setText(R.string.fisico_ability_forma);
                textViewSecondAbility.setText(R.string.fisico_ability_forza);
                textViewThirdAbility.setText(R.string.fisico_ability_aggr);
                textViewFourthAbility.setText(R.string.fisico_ability_velocita);
                textViewFivethAbility.setText(R.string.fisico_ability_creativita);
            }
        } else {
            if (position == 1) {
                textViewCategory.setText(R.string.heading_ability_type_defence);
                textViewFirstAbility.setText(R.string.defence_ability_contrasto);
                textViewSecondAbility.setText(R.string.defence_ability_marcatura);
                textViewThirdAbility.setText(R.string.defence_ability_pos);
                textViewFourthAbility.setText(R.string.defence_ability_colpo_testa);
                textViewFivethAbility.setText(R.string.defence_ability_coraggio);
            } else if (position == 2) {
                textViewCategory.setText(R.string.heading_ability_type_forward);
                textViewFirstAbility.setText(R.string.forward_ability_pass);
                textViewSecondAbility.setText(R.string.forward_ability_dribbling);
                textViewThirdAbility.setText(R.string.forward_ability_cross);
                textViewFourthAbility.setText(R.string.forward_ability_tiro);
                textViewFivethAbility.setText(R.string.forward_ability_fin);
            } else if (position == 3) {
                textViewCategory.setText(R.string.heading_ability_type_physic);
                textViewFirstAbility.setText(R.string.fisico_ability_forma);
                textViewSecondAbility.setText(R.string.fisico_ability_forza);
                textViewThirdAbility.setText(R.string.fisico_ability_aggr);
                textViewFourthAbility.setText(R.string.fisico_ability_velocita);
                textViewFivethAbility.setText(R.string.fisico_ability_creativita);
            }
        }
    }
}