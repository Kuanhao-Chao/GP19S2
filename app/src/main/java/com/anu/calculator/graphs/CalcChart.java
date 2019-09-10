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
    private int text_size = 25;

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
    }

    public void draw_grids(){
        Paint text_P = new Paint();
        Paint p = new Paint();
        int digit_offset = 30;
        text_P.setColor(Color.BLACK);
        text_P.setTextSize(text_size);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4.0f);
        canvas.drawLine(0.0f, origin.y, dim.x , origin.y ,p); // draw main grid
        canvas.drawLine(origin.x,0.0f,origin.x, dim.y, p); // draw main grid
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2.0f);
        p.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));

        for (int i = 1; i <= (range.y/label_scale.y) ; i++) {
            canvas.drawLine(0.0f, origin.y - lines.y * i , dim.x, origin.y - lines.y * i,p);
            canvas.drawLine(0.0f, origin.y + lines.y * i , dim.x, origin.y + lines.y * i,p);
            if (i  >= 10 / label_scale.y) {digit_offset = 40;}
            canvas.drawText(String.valueOf(i*label_scale.y),origin.x - digit_offset, origin.y - (lines.y * i - 10) ,text_P);
            canvas.drawText(String.valueOf(-i*label_scale.y),origin.x -  digit_offset, origin.y + (lines.y *i + 10) ,text_P);
        }
        digit_offset = 10; //additional offset to compensate for 2 digits
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(origin.x + lines.x * i , 0.0f,origin.x + lines.x *i, dim.y, p);
            canvas.drawLine(origin.x - lines.x * i ,0.0f,origin.x - lines.x *i, dim.y, p);
            if (i  >= 10/ label_scale.x) {digit_offset = 20;}
            canvas.drawText(String.valueOf(i*label_scale.x),origin.x + (lines.x * i - digit_offset), origin.y + 30 ,text_P);
            canvas.drawText(String.valueOf(-i*label_scale.y),origin.x - (lines.x * i + digit_offset), origin.y + 30 ,text_P);
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
