package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PiExpression: This class is used to represent PI
 */

public class PiExpression implements Expression {

	@Override
	public String show() {
		return "" + Scripts.Operators.PI.getUnicode();
	}

	@Override
	public double evaluate() {
		return Math.PI;
	}

}
