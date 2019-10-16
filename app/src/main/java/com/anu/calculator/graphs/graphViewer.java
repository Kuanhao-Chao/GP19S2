package com.anu.calculator.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.core.view.MotionEventCompat;

import com.anu.calculator.Expression;

import com.anu.calculator.ui.GraphActivity;
import com.anu.calculator.utilities.History;
import com.anu.calculator.utilities.HistoryItem;

import java.util.ArrayList;
import java.util.Map;


public class graphViewer extends View {
    public graphViewer(Context c, AttributeSet as) {
        super(c, as);
        mScaleGestureDetctor = new ScaleGestureDetector(c, new ScaleListner());
        mGestureDetctor = new GestureDetector(c, new otherGestureListener());



    }

    public boolean isAxis = true;
    public boolean isAxisLabel = true;
    public boolean isGrid = true;
    private GraphRange range;
    public ArrayList<ListModel> functionList = new ArrayList<>();
    private ScaleGestureDetector mScaleGestureDetctor;
    private GestureDetector mGestureDetctor;
    private float mScaleFactor_x = 1.0f;
    private float mScaleFactor_y = 1.0f;
    private float x_ratio = 0.5f;
    private float y_ratio = 0.5f;
    private float y_start = 0;
    private float x_start =0;
    private int delay = 0;
    private boolean isScale = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetctor.onTouchEvent(event);

        if (!mScaleGestureDetctor.isInProgress()) {
            if (delay <= 0) {
                mGestureDetctor.onTouchEvent(event);
            } else {delay = delay -1;}
        }
        return true;
    }


    private class otherGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (Math.abs(distanceX )> Math.abs(distanceY)) {
                range.move_x(range.span.x * distanceX /400);

            } else {
                range.move_y(range.span.y * distanceY / 400);
            }
            refresh_function();
            invalidate();
            return true;
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            reset_view();
            refresh_function();
            invalidate();
            return true;
        }

    }

    private class ScaleListner extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            delay = 3;
            System.out.println(mScaleGestureDetctor.getCurrentSpanX());
            System.out.println(mScaleGestureDetctor.getCurrentSpanY());
            x_ratio = mScaleGestureDetctor.getCurrentSpanX() / (mScaleGestureDetctor.getCurrentSpanX() +mScaleGestureDetctor.getCurrentSpanY());
            y_ratio = mScaleGestureDetctor.getCurrentSpanY() / (mScaleGestureDetctor.getCurrentSpanX() +mScaleGestureDetctor.getCurrentSpanY());

            mScaleFactor_x = 1.0f - 2.0f * x_ratio * (mScaleGestureDetctor.getScaleFactor() - 1.0f) ;
            mScaleFactor_y = 1.0f - 2.0f * y_ratio * (mScaleGestureDetctor.getScaleFactor() - 1.0f) ;
            range.rescale_x(mScaleFactor_x);
            range.rescale_y(mScaleFactor_y);
            refresh_function();
            invalidate();

            return true;
        }
    }
    public void reset_view(){
        this.range = new GraphRange(new ChartVect(-20,-20), new ChartVect(20, 20));
    }
    public void init_functions(GraphRange range){
        Map<Character, HistoryItem> funMap = History.loadGraphableHistory(getContext());
        this.range = range;
        for (Character key : funMap.keySet()){
            HistoryItem val = funMap.get(key);
            Expression val_e = val.getExpression();
            functionList.add(new ListModel(val_e,false));
        }
    }

    public void refresh_function(){
        Map<Character, HistoryItem> funMap = History.loadGraphableHistory(getContext());
        boolean isNew;
        for (Character key : funMap.keySet()){
            HistoryItem val = funMap.get(key);
            Expression val_e = val.getExpression();
            isNew = true;
            for (ListModel l : functionList) {
                if (l.func.show().equals(val_e.show())){
                    isNew = false;
                }
            }
            if (isNew) {
                functionList.add(new ListModel(val_e, false));
            }
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        CalcChart chart = new CalcChart(width, height, range, canvas);
        chart.draw_grids(isAxis, isGrid, isAxisLabel);
        chart.draw_functions(functionList);

    }
}
