package com.anu.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.anu.calculator.R;
import com.anu.calculator.graphs.graphViewer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        createFloatingButton();
        createFunctionsList();
        createGraph();
    }
    public void createFunctionsList(){

    }
    public void createGraph(){
        graphViewer gView = findViewById(R.id.graph_view);

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