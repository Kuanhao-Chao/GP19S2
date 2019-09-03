package com.anu.calculator.expressionparser;

/**
 * PiExp: This class is used to represent PI
 */

public class PiExp extends Exp {

	@Override
	public String show() {
		return "" + Scripts.Operators.PI.getUnicode();
	}

	@Override
	public double evaluate() {
		return Math.PI;
	}

}
