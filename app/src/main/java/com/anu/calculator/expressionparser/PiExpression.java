package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PiExpression: This class is used to represent PI
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
