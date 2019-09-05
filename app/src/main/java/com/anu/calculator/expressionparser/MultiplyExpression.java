package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * MultiplyExpression: This class is used to represent the expression of multiplication
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Added the MULTIPLY unicode to the show() method
 */

public class MultiplyExpression implements Expression {
	private Expression term;
	private Expression factor;


	MultiplyExpression(Expression factor, Expression term) {
		this.factor = factor;		
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.MULTIPLY.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() {
		return (factor.evaluate() * term.evaluate());
	}

}