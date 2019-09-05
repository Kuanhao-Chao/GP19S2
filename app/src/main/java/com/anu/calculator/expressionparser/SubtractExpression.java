package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * SubtractExpression: This class is used to represent the expression of subtraction
 */

public class SubtractExpression implements Expression {
	private Expression expression;
	private Expression term;


	SubtractExpression(Expression term, Expression expression) {
		this.term = term;
		this.expression = expression;

	}

	@Override
	public String show() {
		return "(" + term.show() + Scripts.Operators.MINUS.getUnicode() + expression.show() + ")";
	}

	@Override
	public double evaluate() {
		return (term.evaluate() - expression.evaluate());
	}

}
