package com.anu.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import java.util.Objects;

public class OperationsFragment extends Fragment {

    private static final String TAG = "OPERATIONS_TAB";

    public static OperationsFragment newInstance() {
        return new OperationsFragment();
    }

    /**
     * Inserts the provided textToAdd into the selected region of an editText area. If the user has
     * selected an area of text (multiple characters), that text is replaced with the new text.
     *
     * @param editText  An Edit Text Area to add text to.
     * @param textToAdd A String to insert into the Edit Text Area.
     */
    private void addText(@org.jetbrains.annotations.NotNull EditText editText, String textToAdd) {
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        editText.getText().replace(Math.min(start, end), Math.max(start, end),
                textToAdd, 0, textToAdd.length());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final EditText calculation_area = Objects.requireNonNull(getActivity()).findViewById(R.id.calculation_textarea);

        View rootView = inflater.inflate(R.layout.operations_fragment, container, false);

        Button sin = (Button) rootView.findViewById(R.id.sin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.sin) + getString(R.string.lbra) + getString(R.string.rbra);
                addText(calculation_area, input);
            }
        });

        Button cos = (Button) rootView.findViewById(R.id.cos);
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.cos) + getString(R.string.lbra) + getString(R.string.rbra);
                addText(calculation_area, input);
            }
        });

        Button tan = (Button) rootView.findViewById(R.id.tan);
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.tan) + getString(R.string.lbra) + getString(R.string.rbra);
                addText(calculation_area, input);
            }
        });

        Button squared = (Button) rootView.findViewById(R.id.squared);
        squared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.squared);
                addText(calculation_area, input);
            }
        });

        Button natural_log = (Button) rootView.findViewById(R.id.natural_log);
        natural_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.natural_log);
                addText(calculation_area, input);
            }
        });

        Button log_10 = (Button) rootView.findViewById(R.id.log10);
        log_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.log10);
                addText(calculation_area, input);
            }
        });

        Button power = (Button) rootView.findViewById(R.id.power);
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.power);
                addText(calculation_area, input);
            }
        });

        Button cubed = (Button) rootView.findViewById(R.id.cubed);
        cubed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.cubed);
                addText(calculation_area, input);
            }
        });

        Button permutation = (Button) rootView.findViewById(R.id.permutation);
        permutation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.npr);
                addText(calculation_area, input);
            }
        });

        Button combinations = (Button) rootView.findViewById(R.id.combinations);
        combinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.ncr);
                addText(calculation_area, input);
            }
        });

        Button pi = (Button) rootView.findViewById(R.id.pi);
        pi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.pi);
                addText(calculation_area, input);
            }
        });

        Button sqrt = (Button) rootView.findViewById(R.id.sqrt);
        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.sqrt) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button arc_sin = (Button) rootView.findViewById(R.id.arc_sin);
        arc_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.arc_sin) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button arc_cos = (Button) rootView.findViewById(R.id.arc_cos);
        arc_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.arc_cos) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button arc_tan = (Button) rootView.findViewById(R.id.arc_tan);
        arc_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.arc_tan) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button cubed_root = (Button) rootView.findViewById(R.id.cubed_root);
        cubed_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.cubed_root) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button lbra = (Button) rootView.findViewById(R.id.lbra);
        lbra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.lbra) + getString(R.string.lbra) + getString(R.string.rbra);;
                addText(calculation_area, input);
            }
        });

        Button rbra = (Button) rootView.findViewById(R.id.rbra);
        rbra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.rbra);
                addText(calculation_area, input);
            }
        });

        Button rand = (Button) rootView.findViewById(R.id.random_number);
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.rand);
                addText(calculation_area, input);
            }
        });

        Button factorial = (Button) rootView.findViewById(R.id.factorial);
        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.factorial);
                addText(calculation_area, input);
            }
        });

        return rootView;
    }

}
