package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * EExpression: This class is used to represent the variable e
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
