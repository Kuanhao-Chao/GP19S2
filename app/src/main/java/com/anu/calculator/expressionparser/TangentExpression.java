package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * TangentExpression: This class is used to represent the expression of tangent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class TangentExpression implements Expression {
	private Expression expression;

	TangentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "tan(" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		return Math.tan(Math.toRadians(expression.evaluate()));
	}

}
