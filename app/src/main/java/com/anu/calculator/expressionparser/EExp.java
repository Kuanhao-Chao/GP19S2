package com.anu.calculator.expressionparser;

/**
 * EExp: This class is used to represent the variable e
 */

public class EExp extends Exp {

	@Override
	public String show() {
		return "e";
	}

	@Override
	public double evaluate() {
		return Math.E;
	}

}
