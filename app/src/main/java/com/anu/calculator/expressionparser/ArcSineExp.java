package com.anu.calculator.expressionparser;

/**
 * ArcSineExp: This class is used to represent the expression of arcsine
 */

public class ArcSineExp extends Exp {
	private Exp exp;

	public ArcSineExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public String show() {
		return "sin" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + exp.show() + ")";
	}

	@Override
	public double evaluate() {
		return Math.toDegrees(Math.asin(exp.evaluate()));
	}

}
