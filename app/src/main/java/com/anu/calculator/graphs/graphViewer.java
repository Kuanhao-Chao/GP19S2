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


public class graphViewer extends View {
    public graphViewer(Context c, AttributeSet as) {
        super(c, as);
    }
    private boolean on = false;
    public void turnon(){
        on = true;
        System.out.println("WORKED");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int offset = 150;
        int mid_y = height/2 + offset/2;
        int mid_x = width/2;
        int scale_x = 50;
        int scale_y = 50;
        int text_size = 25;
        Paint p = new Paint();
        Paint text_P = new Paint();
        text_P.setColor(Color.BLACK);
        text_P.setTextSize(text_size);
        p.setColor(Color.BLACK);
        p.setStrokeWidth(4.0f);
        canvas.drawLine(0.0f,mid_y, width, mid_y,p); // draw main grid
        canvas.drawLine(mid_x,offset,mid_x, height, p); // draw main grid

        //Draw minor lines
        p.setColor(Color.BLUE);
        p.setStrokeWidth(1.0f);
        p.setPathEffect(new DashPathEffect(new float[]{10, 20}, 0));
        int DIGIT_OFFSET = 0; //additional offset to compensate for 2 digits

        //draw y axis
        for (int i = 1; i <= 6; i++) {
            canvas.drawLine(0.0f, mid_y - scale_y * i, width, mid_y - scale_y * i,p);
            canvas.drawLine(0.0f, mid_y + scale_y * i, width, mid_y + scale_y * i,p);
            if (i  >= 5) {DIGIT_OFFSET = 10;}
            canvas.drawText(String.valueOf(i*2),mid_x - (scale_x - 20 + DIGIT_OFFSET), mid_y - (scale_y *i- 10) ,text_P);
            canvas.drawText(String.valueOf(-i*2),mid_x - (scale_x - 20 + DIGIT_OFFSET), mid_y + (scale_y *i + 10) ,text_P);
        }

        //draw x axis
        DIGIT_OFFSET = 0; //additional offset to compensate for 2 digits
        for (int i = 1; i <= 10; i++) {
            canvas.drawLine(mid_x + scale_x * i ,offset,mid_x + scale_x*i, height, p);
            canvas.drawLine(mid_x - scale_x * i ,offset,mid_x - scale_x*i, height, p);
            if (i  >= 5) {DIGIT_OFFSET = 10;}
            canvas.drawText(String.valueOf(i*2),mid_x + (scale_x * i - 10 - DIGIT_OFFSET), mid_y + (scale_y - 20) ,text_P);
            canvas.drawText(String.valueOf(-i*2),mid_x - (scale_x * i + 10 + DIGIT_OFFSET), mid_y + (scale_y - 20   ) ,text_P);
        }
        if (on) {
            canvas.drawText("WORKED", mid_x, mid_y, text_P);
        }
    }
}
