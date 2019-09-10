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
    private int[] pallet = {Color.RED, Color.GREEN};
    public void turnon(int i){
        on[i] = true;
    }
    public float[] convert_coord(float[] points, int scale_x, int scale_y, int mid_x, int mid_y) {
        int new_len = points.length*2 -4;
        float[] coord = new float[new_len];
        coord[0] = points[0]* scale_x + mid_x;
        coord[1] = -points[1]* scale_y + mid_y;
        coord[new_len-2] = points[points.length-2] * scale_x + mid_x;
        coord[new_len-1] = -points[points.length-1] * scale_y + mid_y;
        for (int i = 2; i < points.length-2; i = i + 2){
            coord[(i-2)*2 + 2] = points[i] * scale_x + mid_x;
            coord[(i-2)*2 + 3] = -points[i+1] * scale_y + mid_y;
            coord[(i-2)*2 + 4] = points[i] * scale_x + mid_x;
            coord[(i-2)*2 + 5] = -points[i+1] * scale_y + mid_y;
        }
        //System.out.println(Arrays.toString(coord));
        return coord;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        CalcChart chart = new CalcChart(width, height, canvas);

        int offset = 0;
        int mid_y = height/2 + offset/2;
        int mid_x = width/2;
        int scale_x = 25;
        int scale_y = 25;
        int text_size = 25;
        int label_factor = 2;
        Paint p = new Paint();
        Paint text_P = new Paint();
        text_P.setColor(Color.BLACK);
        text_P.setTextSize(text_size);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4.0f);




        chart.draw_grids();
        //canvas.drawLine(0.0f,mid_y, width, mid_y,p); // draw main grid
        //canvas.drawLine(mid_x,offset,mid_x, height, p); // draw main grid






        //Draw minor lines
        //p.setColor(Color.BLUE);
        //p.setStrokeWidth(1.0f);
        //p.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        //int DIGIT_OFFSET = 0; //additional offset to compensate for 2 digits

        /*//draw y axis
        for (int i = 1; i <= 6; i++) {
            canvas.drawLine(0.0f, mid_y - scale_y * i * label_factor, width, mid_y - scale_y * i* label_factor,p);
            canvas.drawLine(0.0f, mid_y + scale_y * i* label_factor, width, mid_y + scale_y * i* label_factor,p);
            if (i  >= 5) {DIGIT_OFFSET = 10;}
            canvas.drawText(String.valueOf(i*2),mid_x - (scale_x * label_factor- 20 + DIGIT_OFFSET), mid_y - (scale_y *i* label_factor- 10) ,text_P);
            canvas.drawText(String.valueOf(-i*2),mid_x - (scale_x * label_factor- 20 + DIGIT_OFFSET), mid_y + (scale_y *i* label_factor + 10) ,text_P);
        }*/

        /*//draw x axis
        DIGIT_OFFSET = 0; //additional offset to compensate for 2 digits
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(mid_x + scale_x * i * label_factor,offset,mid_x + scale_x*i* label_factor, height, p);
            canvas.drawLine(mid_x - scale_x * i * label_factor,offset,mid_x - scale_x*i* label_factor, height, p);
            if (i  >= 5) {DIGIT_OFFSET = 10;}
            canvas.drawText(String.valueOf(i*2),mid_x + (scale_x * i * label_factor- 10 - DIGIT_OFFSET), mid_y + (scale_y* label_factor - 20) ,text_P);
            canvas.drawText(String.valueOf(-i*2),mid_x - (scale_x * i * label_factor+ 10 + DIGIT_OFFSET), mid_y + (scale_y* label_factor - 20) ,text_P);
        }*/
        for (int i = 0; i < 2; i++) {
            if (!on[i]) {
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

                float[] coord = convert_coord(points, scale_x, scale_y, mid_x, mid_y);
                System.out.println("worked");
                canvas.drawLines(coord, f1_p);
            }
        }
    }
}
