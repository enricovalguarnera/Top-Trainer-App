package com.example.toptrainer;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private String mParam1;
    private Integer position;

    private Button buttonGK;
    private TextView textViewCategory;

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
    public static TrainingAbilityFragment newInstance(String param1, Integer param2) {
        TrainingAbilityFragment fragment = new TrainingAbilityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            position = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_training_ability, container, false);
        buttonGK = (Button) getActivity().findViewById(R.id.button_gk);
        textViewCategory = (TextView) view.findViewById(R.id.category_heading);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (buttonGK.isSelected()) {
            Log.i("POSITION", String.valueOf(position));
            if (position == 1) {
                textViewCategory.setText("Portiere 1");
            } else if (position == 2) {
                textViewCategory.setText("Portiere 2");
            } else if (position == 3) {
                textViewCategory.setText("Fisico e Mentale");
            }
        } else {
            textViewCategory.setText("Attacco");
        }
        Log.i("ON_RESUME", "resume");
    }
}