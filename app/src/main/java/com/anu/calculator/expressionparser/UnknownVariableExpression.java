package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * UnknownVariableExpression: This class is used to represent a variable with an unknown value,
 * i.e. for formulas.
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class UnknownVariableExpression implements Expression {

	private char variable;
	private double value;

	UnknownVariableExpression(char variable) {
		this.variable = variable;
		this.value = 0;
	}

	@Override
	public String show() {
		return "" + variable;
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
