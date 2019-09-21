package com.anu.calculator.utilities;

import com.anu.calculator.Expression;

import java.io.Serializable;

public class HistoryItem implements Serializable {
    private boolean graphable;
    private Expression expression;

    HistoryItem(boolean graphable, Expression expression) {
        this.graphable = graphable;
        this.expression = expression;
    }

    boolean isGraphable() {
        return graphable;
    }

    public Expression getExpression() {
        return expression;
    }
}
