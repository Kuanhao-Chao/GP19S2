package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * ArcTangentExpression: This class is used to represent the expression of arctangent
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class ArcTangentExpression implements Expression {
	private Expression expression;

	ArcTangentExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String show() {
		return "tan" +
				Scripts.SuperScript.MINUS.getUnicode() +
				Scripts.SuperScript.ONE.getUnicode() +
				"(" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		return Math.toDegrees(Math.atan(expression.evaluate()));
	}

}
