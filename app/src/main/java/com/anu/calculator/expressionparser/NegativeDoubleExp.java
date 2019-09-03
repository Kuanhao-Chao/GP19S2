package com.anu.calculator.expressionparser;

/**
 * DoubleExp: This class is used to represent a double literal
 */

public class NegativeDoubleExp extends Exp {
	private double value;

	public NegativeDoubleExp(double value) {
		this.value = value;
	}

	@Override
	public String show() {
		return "" + value;
	}

	@Override
	public double evaluate() {
		return -1 * value;
	}
}
