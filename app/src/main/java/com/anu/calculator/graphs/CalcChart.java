package com.anu.calculator.graphs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import androidx.arch.core.util.Function;

public class CalcChart {

    private ChartVect dim;
    private ChartVect lines;
    private ChartVect step;
    private ChartVect origin;
    private Canvas canvas;
    private ChartVect range;
    private ChartVect label_scale;
    private int text_size;

    public CalcChart(int dim_x, int dim_y, Canvas canvas){

        this.canvas = canvas;
        // TO BE CHANGED
        range = new ChartVect(20,20); //+-x,+-y so range is -x to x and -y to y
        label_scale = new ChartVect(5,5);

        //initialise the scales
        dim = new ChartVect(dim_x,dim_y);
        origin = new ChartVect(dim_x/2, dim_y/2);
        step = new ChartVect(dim.x /(range.x *2),dim.y /(range.y * 2)); //add a factor of 2 since its -x to x not 0 to x
        lines = new ChartVect(step.x * label_scale.x, step.y * label_scale.y);
        text_size = lines.x / 5;
    }

    public float[] convert_coord(float[] points) {
        int new_len = points.length*2 -4;
        float[] coord = new float[new_len];
        coord[0] = points[0]* step.x + origin.x;
        coord[1] = -points[1]* step.y + origin.y;
        coord[new_len-2] = points[points.length-2] * step.x + origin.x;
        coord[new_len-1] = -points[points.length-1] * step.y + origin.y;
        for (int i = 2; i < points.length-2; i = i + 2){
            coord[(i-2)*2 + 2] = points[i] * step.x + origin.x;
            coord[(i-2)*2 + 3] = -points[i+1] * step.y + origin.y;
            coord[(i-2)*2 + 4] = points[i] * step.x + origin.x;
            coord[(i-2)*2 + 5] = -points[i+1] * step.y+ origin.y;
        }
        return coord;
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

        for (int i = 0; i <= (range.y/label_scale.y) ; i++) {
            if (isGrid) {
                canvas.drawLine(0.0f, origin.y - lines.y * i, dim.x, origin.y - lines.y * i, p);
                canvas.drawLine(0.0f, origin.y + lines.y * i, dim.x, origin.y + lines.y * i, p);
            }
            if (isAxisLabel) {
                if (i >= 10 / label_scale.y) {
                    digit_offset = text_size + 20;
                }
                canvas.drawText(String.valueOf(i * label_scale.y), origin.x - digit_offset, origin.y - (lines.y * i - 10), text_P);
                canvas.drawText(String.valueOf(-i * label_scale.y), origin.x - digit_offset, origin.y + (lines.y * i + 10), text_P);
            }
        }
        digit_offset = text_size - 10; //additional offset to compensate for 2 digits
        for (int i = 0; i <= range.x/label_scale.x; i++) {
            if (isGrid) {
                canvas.drawLine(origin.x + lines.x * i, 0.0f, origin.x + lines.x * i, dim.y, p);
                canvas.drawLine(origin.x - lines.x * i, 0.0f, origin.x - lines.x * i, dim.y, p);
            }
            if (isAxisLabel) {
                if (i >= 10 / label_scale.x) {
                    digit_offset = text_size;
                }
                canvas.drawText(String.valueOf(i * label_scale.x), origin.x + (lines.x * i - digit_offset), origin.y + 30, text_P);
                canvas.drawText(String.valueOf(-i * label_scale.y), origin.x - (lines.x * i + digit_offset), origin.y + 30, text_P);
            }
        }
    }






    public class plot {
        public void plot(){

        }
        /**
         *
         * @param f - function input to generate points based on the chart object
         *
         */
        public void plot(Function f){


        }
    }
}
