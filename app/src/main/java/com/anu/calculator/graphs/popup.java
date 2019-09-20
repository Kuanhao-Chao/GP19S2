package com.anu.calculator.graphs;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.anu.calculator.R;
import com.anu.calculator.ui.GraphActivity;
import com.anu.calculator.ui.MainActivity;

import java.util.Objects;

public class popup extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        DisplayMetrics dim =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dim);
        int w = dim.widthPixels;
        int h = dim.heightPixels;
        getWindow().setLayout((int) (w*0.9),(int)(h*0.9));
        Button cancel_b = findViewById(R.id.popup_cancel_button);
        Button confirm_b = findViewById(R.id.popup_confirm_button);
        View.OnClickListener onClick_cancel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        View.OnClickListener onClick_confirm= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GraphActivity.setGraph(1);
                finish();
            }
        };

        confirm_b.setOnClickListener(onClick_confirm);
        cancel_b.setOnClickListener(onClick_cancel);
    }

}
