package com.anu.calculator.expressionparser;

/**
 * DoubleExp: This class is used to represent a double literal
 */

public class DoubleExp extends Exp {
	private double value;

	public DoubleExp(double value) {
		this.value = value;
	}

	@Override
	public String show() {
		return "" + value;
	}

	@Override
	public double evaluate() {
		return value;
	}

}
