package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParserException;

/**
 * SquareRootExpression: This class is used to represent the expression of a square root
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class SquareRootExpression implements Expression {
	private Expression expression;

	SquareRootExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return Scripts.Operators.SQRT.getUnicode() + "(" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ExpressionParserException {
		return Math.sqrt(expression.evaluate());
	}
}
