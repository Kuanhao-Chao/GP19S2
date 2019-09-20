package com.anu.calculator.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.view.View;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import com.anu.calculator.R;

import java.util.Arrays;


public class graphViewer extends View {
    public graphViewer(Context c, AttributeSet as) {
        super(c, as);
    }
    private boolean on[] = {false,false};
    public boolean isAxis = true;
    public boolean isAxisLabel = true;
    public boolean isGrid = true;
    private int[] pallet = {Color.RED, Color.GREEN};

    public void turnon(int i){
        on[i] = true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        CalcChart chart = new CalcChart(width, height, canvas);
        chart.draw_grids(isAxis, isGrid, isAxisLabel);
        for (int i = 0; i < 2; i++) {
            if (on[i]) {
                Paint f1_p = new Paint();
                f1_p.setColor(pallet[i]);
                f1_p.setStrokeWidth(5.0f);
                graphParser g = new graphParser();
                float[] points;
                //to be replaced with actual function
                if (i == 1){
                    points = g.genData(i);
                } else {
                    points = g.genData();
                }
                float[] coord = chart.convert_coord(points);
                canvas.drawLines(coord, f1_p);
            }
        }
    }
}
