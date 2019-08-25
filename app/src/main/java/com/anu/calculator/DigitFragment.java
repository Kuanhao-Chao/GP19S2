package com.anu.calculator;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.anu.calculator.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class DigitFragment extends Fragment {

    private static final String TAG = "DIGIT_TAB";
    private static final String dgt_1 = "1";

    public static DigitFragment newInstance() {
        return new DigitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final TextView calculation_area = (TextView) getActivity().findViewById(R.id.calculation_textarea);

        View rootView = inflater.inflate(R.layout.digit_fragment, container, false);

        Button btn_dgt_0 = (Button) rootView.findViewById(R.id.dgt_0);
        btn_dgt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_0));
            }
        });

        Button btn_dgt_1 = (Button) rootView.findViewById(R.id.dgt_1);
        btn_dgt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_1));
            }
        });

        Button btn_dgt_2 = (Button) rootView.findViewById(R.id.dgt_2);
        btn_dgt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_2));
            }
        });

        Button btn_dgt_3 = (Button) rootView.findViewById(R.id.dgt_3);
        btn_dgt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_3));
            }
        });

        Button btn_dgt_4 = (Button) rootView.findViewById(R.id.dgt_4);
        btn_dgt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_4));
            }
        });

        Button btn_dgt_5 = (Button) rootView.findViewById(R.id.dgt_5);
        btn_dgt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_5));
            }
        });

        Button btn_dgt_6 = (Button) rootView.findViewById(R.id.dgt_6);
        btn_dgt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_6));
            }
        });

        Button btn_dgt_7 = (Button) rootView.findViewById(R.id.dgt_7);
        btn_dgt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_7));
            }
        });

        Button btn_dgt_8 = (Button) rootView.findViewById(R.id.dgt_8);
        btn_dgt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_8));
            }
        });

        Button btn_dgt_9 = (Button) rootView.findViewById(R.id.dgt_9);
        btn_dgt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.dgt_9));
            }
        });

        Button sign_switch = (Button) rootView.findViewById(R.id.sign_switch);
        sign_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.sign_switch));
            }
        });

        Button decimal = (Button) rootView.findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.decimal));
            }
        });

        Button addition = (Button) rootView.findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.addition));
            }
        });

        Button subtraction = (Button) rootView.findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.subtraction));
            }
        });

        Button multiply = (Button) rootView.findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.multiply));
            }
        });

        Button divide = (Button) rootView.findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.divide));
            }
        });

        Button percentage = (Button) rootView.findViewById(R.id.percentage);
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.append(getString(R.string.percentage));
            }
        });

        Button all_clear = (Button) rootView.findViewById(R.id.all_clear);
        all_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                calculation_area.setText("");
            }
        });

        Button delete = (Button) rootView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int length = calculation_area.length();
                if (length > 0) {
                    calculation_area.setText(calculation_area.getText().subSequence(0, length - 1));
                }
            }
        });


        return rootView;
    }
}
