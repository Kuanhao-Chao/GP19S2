package com.anu.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.anu.calculator.R;
import com.anu.calculator.graphs.graphViewer;
import com.anu.calculator.graphs.popup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GraphActivity extends AppCompatActivity {
    static graphViewer gView;
    public static void setGraph(int i){
        gView.turnon(i);
        gView.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        createGraph();
        createFloatingButton();
        createFunctionButton();
        createOptions();
    }
    public void createOptions(){
        CheckBox chkAxis = findViewById(R.id.checkBox_Axis);
        CheckBox chkAxisLabel= findViewById(R.id.checkBox_Label);
        CheckBox chkGrid= findViewById(R.id.checkBox_grid);
        chkAxis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    gView.isAxis = true;
                } else {
                    gView.isAxis = false;
                }
                gView.invalidate();
            }
        });
        chkAxisLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    gView.isAxisLabel= true;
                } else {
                    gView.isAxisLabel = false;
                }
                gView.invalidate();
            }
        });
        chkGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()){
                    gView.isGrid= true;
                } else {
                    gView.isGrid= false;
                }
                gView.invalidate();
            }
        });
    }
    public void createFunctionButton() {
        Button b = findViewById(R.id.select_function_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GraphActivity.this, popup.class));
            }
        });
    }
    public void createGraph(){
        gView = findViewById(R.id.graph_view);

    }
    public void createFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}