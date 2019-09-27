package com.anu.calculator.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

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
    }
    public boolean isAxis = true;
    public boolean isAxisLabel = true;
    public boolean isGrid = true;
    private GraphRange range;
    public ArrayList<ListModel> functionList = new ArrayList<>();


    private ScaleGestureDetector mScaleGestureDetctor;
    private float mScaleFactor_x = 1.0f;
    private float mScaleFactor_y = 1.0f;
    private float x_ratio = 0.5f;
    private float y_ratio = 0.5f;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetctor.onTouchEvent(event);
        return true;
    }

    private class ScaleListner extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            System.out.println(mScaleGestureDetctor.getCurrentSpanX());
            System.out.println(mScaleGestureDetctor.getCurrentSpanY());
            x_ratio = mScaleGestureDetctor.getCurrentSpanX() / (mScaleGestureDetctor.getCurrentSpanX() +mScaleGestureDetctor.getCurrentSpanY());
            y_ratio = mScaleGestureDetctor.getCurrentSpanY() / (mScaleGestureDetctor.getCurrentSpanX() +mScaleGestureDetctor.getCurrentSpanY());

            mScaleFactor_x = 1.0f + 2.0f * x_ratio * (mScaleGestureDetctor.getScaleFactor() - 1.0f) ;
            mScaleFactor_y = 1.0f + 2.0f * y_ratio * (mScaleGestureDetctor.getScaleFactor() - 1.0f) ;
            //range.rescale(mScaleFactor);
            range.rescale_x(mScaleFactor_x);
            range.rescale_y(mScaleFactor_y);
            System.out.println(mScaleFactor_x);
            System.out.println(mScaleFactor_y);
            System.out.println("range" + range.span.x);
            System.out.println("range" + range.span.y);
            init_functions(range);
            invalidate();

            return true;
        }
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
