package com.anu.calculator.expressionparser;

public class CubedRootExp extends Exp {

    private Exp exp;

    public CubedRootExp(Exp exp)
    {
        this.exp = exp;
    }

    @Override
    public String show()
    {
        return Scripts.Operators.CUBE_ROOT.getUnicode() + "(" + exp.show() + ")";
    }

    @Override
    public double evaluate()
    {
        return Math.cbrt(exp.evaluate());
    }

}
