package com.anu.calculator.expressionparser;

/**
 * UnkVarExp: This class is used to represent a variable with an unknown value (for formulas)
 */

public class UnkVarExp extends Exp {

	private char variable;

	public UnkVarExp(char variable) {
		this.variable = variable;
	}

	@Override
	public String show() {
		return "" + variable;
	}

	@Override
	public double evaluate() {
		return Double.MIN_VALUE; //placeholder method until we figure out how to handle unknown variables
	}
}
