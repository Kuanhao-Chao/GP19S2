package com.anu.calculator.graphs;

import com.anu.calculator.Expression;

public class ListModel {
    public Expression func;
    public boolean checked;

    public ListModel(Expression func, boolean checked) {
        this.func = func;
        this.checked = checked;
    }
}
