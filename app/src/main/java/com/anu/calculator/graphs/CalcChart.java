package com.anu.calculator.graphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.text.format.Formatter;


import androidx.arch.core.util.Function;

import com.google.android.gms.common.util.ArrayUtils;

import java.text.Format;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;

public class CalcChart {

    private ChartVect dim;
    private ChartVect lines;
    private ChartVect step;
    private ChartVect origin;
    private Canvas canvas;
    private GraphRange range;
    private ChartVect label_scale;
    private final int GRID_NUM = 10;//per quadrant
    private int text_size;
    private static int[] pallet = {Color.RED,
            Color.GREEN,
            Color.CYAN,
            Color.MAGENTA,
            Color.YELLOW,
            Color.BLUE,
            Color.BLACK,
            Color.rgb(210,245,60),
            Color.rgb(0,0,128),
            Color.rgb(245,130,48),
            Color.rgb(145,30,180),
            Color.rgb(170,110,40),
            Color.rgb(128,128,0),
            Color.rgb(250,190,190),
            Color.rgb(170,255,195),
            Color.rgb(225,250,200)};
    public static int getPallet(int i){
        return pallet[i % 16];
    }
    public CalcChart(int dim_x, int dim_y, GraphRange range, Canvas canvas){
        this.canvas = canvas;
        this.range = range;

         //+-x,+-y so range is -x to x and -y to y
        label_scale = new ChartVect(range.span.x / GRID_NUM,range.span.y/GRID_NUM);

        //initialise the scales
        dim = new ChartVect(dim_x,dim_y);
        origin = new ChartVect(dim_x/2, dim_y/2);
        step = new ChartVect(dim.x /(range.span.x),dim.y /(range.span.y)); //add a factor of 2 since its -x to x not 0 to x
        lines = new ChartVect(step.x * label_scale.x, step.y * label_scale.y);
        text_size = (int) (2.0f * lines.x / GRID_NUM);
    }

    public float[] convert_coord(float[] points) {
        int new_len = points.length*2 -4;
        float[] coord = new float[new_len];
        ChartVect o = new ChartVect(range.min.x + range.span.x/2, range.min.y + range.span.y/2);

        coord[0] = (-o.x + points[0])* step.x  + origin.x;
        coord[1] = (-o.y - points[1])* step.y - origin.y;
        coord[new_len-2] = (-o.x + points[points.length-2]) * step.x + origin.x;
        coord[new_len-1] = (-o.y - points[points.length-1])* step.y + origin.y;
        for (int i = 2; i < points.length-2; i = i + 2){
            coord[(i-2)*2 + 2] = (-o.x +points[i]) * step.x + origin.x;
            coord[(i-2)*2 + 3] = (-o.y -points[i+1]) * step.y + origin.y;
            coord[(i-2)*2 + 4] = (-o.x +points[i]) * step.x + origin.x;
            coord[(i-2)*2 + 5] = (-o.y -points[i+1]) * step.y+ origin.y;
        }
        return coord;
    }
    public void draw_functions(ArrayList<ListModel> functionList){
        for (int i = 0; i < functionList.size(); i++) {
            if (functionList.get(i).checked) {
                Paint f1_p = new Paint();
                f1_p.setColor(getPallet(i));
                f1_p.setStrokeWidth(5.0f);
                float[] points;
                graphParser g = new graphParser(functionList.get(i).func);
                points = g.genData(range);
                float[] coord = convert_coord(points);
                canvas.drawLines(coord, f1_p);
            }
        }
    }
    public String format_label_text(float label){
        String label_text = String.valueOf(label);
        if (Math.abs(label) >= 100){
            label_text = String.format("%3.1e",label);
        } else if (Math.abs(label) <= 1){
            label_text = String.format("%3.1e",label);
        } else {
            label_text = String.format("%.1f%n",label);
        }

        if (label == 0.0) {
            label_text = String.format("%.0f%n",label);
        }

        return label_text;
    }

    public void draw_grids(boolean isAxis, boolean isGrid, boolean isAxisLabel){
        Paint text_P = new Paint();
        Paint p = new Paint();
        int digit_offset = text_size;
        text_P.setColor(Color.BLACK);
        text_P.setTextSize(text_size);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4.0f);

        if (isAxis) {
            canvas.drawLine(0.0f, origin.y, dim.x, origin.y, p); // draw main grid
            canvas.drawLine(origin.x, 0.0f, origin.x, dim.y, p); // draw main grid
        }

        p.setColor(Color.BLUE);
        p.setStrokeWidth(2.0f);
        p.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        //Draw y axis

        for (int i = -(int)(range.span.y/(2*label_scale.y)); i <= range.span.y/(2*label_scale.y); i++) {
            if (isGrid) {
                canvas.drawLine(0.0f, origin.y - lines.y * i, dim.x, origin.y - lines.y * i, p);
                //canvas.drawLine(0.0f, origin.y + lines.y * i, dim.x, origin.y + lines.y * i, p);
            }

            if (isAxisLabel) {
                String text = format_label_text(-i * label_scale.y - (range.min.y + range.span.y/2));
                digit_offset = text_size * text.length()/2;
                canvas.drawText(text, origin.x - digit_offset, origin.y + (lines.y * i - 10), text_P);
                //canvas.drawText(text, origin.x - digit_offset, origin.y + (lines.y * i + 10), text_P);
            }
        }
        //Draw x axis
        for (int i = -(int)(range.span.x/(2* label_scale.x)); i <= range.span.x/(2*label_scale.x); i++) {
            if (isGrid) {
                canvas.drawLine(origin.x + lines.x * i, 0.0f, origin.x + lines.x * i, dim.y, p);
                //canvas.drawLine(origin.x - lines.x * i, 0.0f, origin.x - lines.x * i, dim.y, p);
            }
            if (isAxisLabel) {

                String text = format_label_text(i * label_scale.x + (range.min.x + range.span.x/2));
               //canvas.drawText(text, origin.x + (lines.x * i ), origin.y + 30, text_P);//
                 canvas.drawText(text, origin.x + (lines.x * i ), origin.y + 30, text_P);
            }
        }
    }
}
