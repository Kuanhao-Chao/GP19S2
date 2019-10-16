package com.anu.calculator.graphs;

public class GraphRange {
    public ChartVect min;
    public ChartVect max;
    public ChartVect span;
    public GraphRange(ChartVect min, ChartVect max){
        this.min = min;
        this.max = max;
        span = new ChartVect(max.x - min.x, max.y - min.y);
    }
    public void rescale_x(float scale){
        this.min.x = min.x * scale;
        this.max.x = max.x * scale;
        span.x = max.x - min.x;

    }
    public void rescale_y(float scale){
        this.min.y = min.y * scale;
        this.max.y = max.y* scale;
        span.y = max.y - min.y;
    }
    public void move_x(float moved){
        this.min.x = this.min.x + moved;
        this.max.x = this.max.x + moved;
    }
    public void move_y(float moved){
        this.min.y = this.min.y + moved;
        this.max.y = this.max.y + moved;
    }
}
