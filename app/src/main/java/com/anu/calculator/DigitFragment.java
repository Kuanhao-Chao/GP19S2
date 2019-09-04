package com.anu.calculator;

import android.os.Bundle;

import com.anu.calculator.expressionparser.Exp;
import com.anu.calculator.expressionparser.ExpressionParser;
import com.anu.calculator.expressionparser.Tokenizer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import static com.anu.calculator.Util.*;

public class DigitFragment extends Fragment {

    private static final String TAG = "DIGIT_TAB";

    public static DigitFragment newInstance() {
        return new DigitFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final EditText calculation_area = Objects.requireNonNull(getActivity()).findViewById(R.id.calculation_textarea);
        calculation_area.setCursorVisible(true);

        final View rootView = inflater.inflate(R.layout.digit_fragment, container, false);

        Button btn_dgt_0 = rootView.findViewById(R.id.dgt_0);
        btn_dgt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_0);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_1 = rootView.findViewById(R.id.dgt_1);
        btn_dgt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_1);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_2 = rootView.findViewById(R.id.dgt_2);
        btn_dgt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_2);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_3 = rootView.findViewById(R.id.dgt_3);
        btn_dgt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_3);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_4 = rootView.findViewById(R.id.dgt_4);
        btn_dgt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_4);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_5 = rootView.findViewById(R.id.dgt_5);
        btn_dgt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_5);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_6 = rootView.findViewById(R.id.dgt_6);
        btn_dgt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_6);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_7 = rootView.findViewById(R.id.dgt_7);
        btn_dgt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_7);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_8 = rootView.findViewById(R.id.dgt_8);
        btn_dgt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_8);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_9 = rootView.findViewById(R.id.dgt_9);
        btn_dgt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_9);
                addText(calculation_area, input);
            }
        });

        Button answer = rootView.findViewById(R.id.answer);
        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.answer);
                addText(calculation_area, input);
            }
        });

        Button decimal = rootView.findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.decimal);
                addText(calculation_area, input);
            }
        });

        Button addition = rootView.findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.addition);
                addText(calculation_area, input);
            }
        });

        Button subtraction = rootView.findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.subtraction);
                addText(calculation_area, input);
            }
        });

        Button multiply = rootView.findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.multiply);
                addText(calculation_area, input);
            }
        });

        Button divide = rootView.findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.divide);
                addText(calculation_area, input);
            }
        });

        Button percentage = rootView.findViewById(R.id.percentage);
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.percentage);
                addText(calculation_area, input);
            }
        });

        Button all_clear = rootView.findViewById(R.id.all_clear);
        all_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                resetTextArea(calculation_area);
            }
        });

        Button delete = rootView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int length = calculation_area.length();
                if (length > 0) {
                    calculation_area.setText(calculation_area.getText().subSequence(0, length - 1));
                }
            }
        });

        Button equals = rootView.findViewById(R.id.evaluate);
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Parse the expression and evaluate it
                String expression = calculation_area.getText().toString();
                Tokenizer tokenizer = new Tokenizer(expression);
                Exp exp = new ExpressionParser(tokenizer).parseExp();
                double evaluation = exp.evaluate();

                // Reset the Text Area
                resetTextArea(calculation_area);

                // Add the text to the screen
                String calcAreaText = "=" + fmt(exp.evaluate());
                calculation_area.setText(calcAreaText);
            }
        });

        return rootView;
    }
}
