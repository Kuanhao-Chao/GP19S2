package com.anu.calculator;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.anu.calculator.R;
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
import android.widget.TextView;

import java.util.Objects;

public class DigitFragment extends Fragment {

    private static final String TAG = "DIGIT_TAB";

    public static DigitFragment newInstance() {
        return new DigitFragment();
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

    /**
     * Returns a double nicely formatted without unnecessary trailing spaces.
     *
     * @param d The double to return nicely formatted
     * @return The nicely formatted double as a string.
     */
    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final EditText calculation_area = Objects.requireNonNull(getActivity()).findViewById(R.id.calculation_textarea);
        calculation_area.setCursorVisible(true);

        View rootView = inflater.inflate(R.layout.digit_fragment, container, false);

        Button btn_dgt_0 = (Button) rootView.findViewById(R.id.dgt_0);
        btn_dgt_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_0);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_1 = (Button) rootView.findViewById(R.id.dgt_1);
        btn_dgt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_1);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_2 = (Button) rootView.findViewById(R.id.dgt_2);
        btn_dgt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_2);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_3 = (Button) rootView.findViewById(R.id.dgt_3);
        btn_dgt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_3);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_4 = (Button) rootView.findViewById(R.id.dgt_4);
        btn_dgt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_4);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_5 = (Button) rootView.findViewById(R.id.dgt_5);
        btn_dgt_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_5);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_6 = (Button) rootView.findViewById(R.id.dgt_6);
        btn_dgt_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_6);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_7 = (Button) rootView.findViewById(R.id.dgt_7);
        btn_dgt_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_7);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_8 = (Button) rootView.findViewById(R.id.dgt_8);
        btn_dgt_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_8);
                addText(calculation_area, input);
            }
        });

        Button btn_dgt_9 = (Button) rootView.findViewById(R.id.dgt_9);
        btn_dgt_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.dgt_9);
                addText(calculation_area, input);
            }
        });

        Button sign_switch = (Button) rootView.findViewById(R.id.sign_switch);
        sign_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.sign_switch);
                addText(calculation_area, input);
            }
        });

        Button decimal = (Button) rootView.findViewById(R.id.decimal);
        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.decimal);
                addText(calculation_area, input);
            }
        });

        Button addition = (Button) rootView.findViewById(R.id.addition);
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.addition);
                addText(calculation_area, input);
            }
        });

        Button subtraction = (Button) rootView.findViewById(R.id.subtraction);
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.subtraction);
                addText(calculation_area, input);
            }
        });

        Button multiply = (Button) rootView.findViewById(R.id.multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.multiply);
                addText(calculation_area, input);
            }
        });

        Button divide = (Button) rootView.findViewById(R.id.divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.divide);
                addText(calculation_area, input);
            }
        });

        Button percentage = (Button) rootView.findViewById(R.id.percentage);
        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.percentage);
                addText(calculation_area, input);
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

        Button equals = (Button) rootView.findViewById(R.id.evaluate);
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String expression = calculation_area.getText().toString();
                Tokenizer tokenizer = new Tokenizer(expression);
                Exp exp = new ExpressionParser(tokenizer).parseExp();
                String evaluation = "=" + fmt(exp.evaluate());
                calculation_area.setText(evaluation);
            }
        });

        return rootView;
    }
}
