package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * DivideExpression: This class is used to represent the expression of division
 */

public class DivideExpression implements Expression {
	private Expression factor;
	private Expression term;

	DivideExpression(Expression factor, Expression term) {
		this.factor = factor;
		this.term = term;		
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.DIVIDE.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() {
		return (factor.evaluate() / term.evaluate());
	}


}