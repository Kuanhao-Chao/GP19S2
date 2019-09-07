package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParserException;

/**
 * FactorialExpression: This class is used to represent the expression of a factorial
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class FactorialExpression implements Expression {
	private Expression expression;

	FactorialExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "(!" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ExpressionParserException {
		return factorial(expression.evaluate());
	}

	private double factorial(double value)
	{
		if(value <= 0) return 0;
		if(value == 1) return 1;
		return value * factorial(value - 1);
	}

}
