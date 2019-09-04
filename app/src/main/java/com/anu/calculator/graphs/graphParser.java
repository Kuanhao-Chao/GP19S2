package com.anu.calculator.graphs;

import com.anu.calculator.expressionparser.Exp;

public class graphParser {
    Exp exp;
    public void graphParser(Exp exp) {
        this.exp = exp;
    }
    public void graphParser() {
        this.exp = exp;
    }

    public float dummyfunction1(float x) {
        return x;
    }

    public float dummyfunction2(float x) {
        return 5* (float)Math.cos((double)x);
    }


    public float[] genData(){
        float[] points = new float[400];
        float x,y = 0.0f;
        for (int i = -20 * 10; i < 20 * 10 - 1; i = i + 2) {
            x = i * 0.1f;
            y = dummyfunction1(x);
            points[i+200] = x;
            points[i+200+1] = y;
            //System.out.println("x=" + x);
            //System.out.println("y=" + y);
        }
        return points;
    }

    public float[] genData(int form){
        float[] points = new float[400];
        float x,y = 0.0f;
        for (int i = (-20 * 10); i < (20 * 10); i = i + 2) {
            x = i * 0.1f;
            y = dummyfunction2(x);
            points[i+200] = x;
            points[i+200+1] = y;
            System.out.println("x=" + x);
            System.out.println("y=" + y);
        }
        return points;
    }

}
