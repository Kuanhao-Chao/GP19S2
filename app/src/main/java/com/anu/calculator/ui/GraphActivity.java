package com.anu.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.anu.calculator.R;
import com.anu.calculator.graphs.ChartVect;
import com.anu.calculator.graphs.GraphRange;
import com.anu.calculator.graphs.graphViewer;
import com.anu.calculator.graphs.ListModel;
import com.anu.calculator.graphs.popup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {
    static graphViewer gView;
    public static final float INIT_RANGE = 20.0f;
    public static void setGraph(){
        gView.invalidate();
    }
    public static ArrayList<ListModel> getFunctions(){
        gView.refresh_function();
        return gView.functionList;
    }
    public static void setFunctions(ArrayList<ListModel> new_functions){
        gView.functionList = new_functions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        createGraph(new GraphRange(new ChartVect(-INIT_RANGE,-INIT_RANGE), new ChartVect(INIT_RANGE,INIT_RANGE)));
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
    public void createGraph(GraphRange range){
        gView = findViewById(R.id.graph_view);
        gView.init_functions(range);


    }
    public void createFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_Main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMain = new Intent(GraphActivity.this, MainActivity.class);
                toMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityForResult(toMain, 0);
            }
        });
    }
}