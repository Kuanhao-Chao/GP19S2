package com.anu.calculator.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.anu.calculator.R;

import java.util.Objects;

import static com.anu.calculator.ui.Util.addText;

/**
 * A fragment with a Google +1 button.
 */
public class FunctionFragment extends Fragment {

    private static final String TAG = "FUNCTION_TAB";

    public static FunctionFragment newInstance() {
        return new FunctionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView: starting");

        final EditText calculation_area = Objects.requireNonNull(getActivity()).findViewById(R.id.calculation_textarea);
        calculation_area.setCursorVisible(true);

        final View rootView = inflater.inflate(R.layout.function_fragment, container, false);

        Button btn_alpha = rootView.findViewById(R.id.alpha);
        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.alpha);
                addText(calculation_area, input);
            }
        });

        Button btn_beta = rootView.findViewById(R.id.beta);
        btn_beta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.beta);
                addText(calculation_area, input);
            }
        });

        Button btn_delta = rootView.findViewById(R.id.delta);
        btn_delta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.delta);
                addText(calculation_area, input);
            }
        });

        Button btn_gamma = rootView.findViewById(R.id.gamma);
        btn_gamma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.gamma);
                addText(calculation_area, input);
            }
        });

        Button btn_w = rootView.findViewById(R.id.w);
        btn_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.w);
                addText(calculation_area, input);
            }
        });

        Button btn_x = rootView.findViewById(R.id.x);
        btn_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.x);
                addText(calculation_area, input);
            }
        });

        Button btn_y = rootView.findViewById(R.id.y);
        btn_y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.y);
                addText(calculation_area, input);
            }
        });

        Button btn_z = rootView.findViewById(R.id.z);
        btn_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String input = getString(R.string.z);
                addText(calculation_area, input);
            }
        });

        Button set_func_f = rootView.findViewById(R.id.set_func_f);
        set_func_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Set Function behaviour
            }
        });

        Button set_func_g = rootView.findViewById(R.id.set_func_g);
        set_func_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Set Function behaviour
            }
        });

        Button set_func_h = rootView.findViewById(R.id.set_func_h);
        set_func_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Set Function behaviour
            }
        });

        Button set_func_i = rootView.findViewById(R.id.set_func_i);
        set_func_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Set Function behaviour
            }
        });

        Button call_func_f = rootView.findViewById(R.id.call_func_f);
        call_func_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Call Function behaviour
            }
        });

        Button call_func_g = rootView.findViewById(R.id.call_func_g);
        call_func_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Call Function behaviour
            }
        });

        Button call_func_h = rootView.findViewById(R.id.call_func_h);
        call_func_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Call Function behaviour
            }
        });

        Button call_func_i = rootView.findViewById(R.id.call_func_i);
        call_func_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO: Call Function behaviour
            }
        });

        Log.d(TAG,"onCreateView: finishing");
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
    }


}
