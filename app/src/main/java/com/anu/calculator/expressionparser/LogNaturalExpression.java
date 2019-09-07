package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * LogNaturalExpression: This class is used to represent the expression of natural logarithm (base e)
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class LogNaturalExpression implements Expression {
	private Expression expression;

	LogNaturalExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "(ln" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		return Math.log(expression.evaluate());
	}

}
