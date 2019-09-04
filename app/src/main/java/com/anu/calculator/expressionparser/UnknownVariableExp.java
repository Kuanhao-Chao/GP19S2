package com.anu.calculator.expressionparser;

/**
 * UnknownVariableExp: This class is used to represent a variable with an unknown value (for formulas)
 */

public class UnknownVariableExp extends Exp {

	private char variable;
	private double value;

	public UnknownVariableExp(char variable) {
		this.variable = variable;
		this.value = 0;
	}

	@Override
	public String show() {
		return " #" + variable;
	}

	@Override
	public double evaluate() {
		return value; //placeholder method until we figure out how to handle unknown variables
	}

	public void assignValue(double value)
	{
		this.value = value;
	}
}
