package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * EExpression: This class is used to represent the variable e
 */

public class EExpression implements Expression {

	@Override
	public String show() {
		return "e";
	}

	@Override
	public double evaluate() {
		return Math.E;
	}

}
