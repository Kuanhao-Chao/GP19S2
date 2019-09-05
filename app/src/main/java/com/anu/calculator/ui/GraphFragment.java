package com.anu.calculator.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anu.calculator.R;
import com.anu.calculator.graphs.graphViewer;

public class GraphFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.graph_fragment, container, false);
        final graphViewer gView = rootView.findViewById(R.id.graph_view);

        Button btn_0 = (Button) rootView.findViewById(R.id.formula1);
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                graphViewer gView = rootView.findViewById(R.id.graph_view);
                gView.turnon(0);
                gView.invalidate();
            }
        });
        Button btn_1 = (Button) rootView.findViewById(R.id.formula2);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                graphViewer gView = rootView.findViewById(R.id.graph_view);
                gView.turnon(1);
                gView.invalidate();
            }
        });

        return rootView;
    }
}
