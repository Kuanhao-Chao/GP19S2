package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * AddExpression: This class is used to represent the expression of addition
 *
 * @author: Michael Betterton (u6797866)
 *
 */

public class AddExpression implements Expression {
	private Expression expression;
	private Expression term;

	AddExpression(Expression term, Expression expression) {
		this.term = term;
		this.expression = expression;
	}

	@Override
	public String show() {
		return "(" + term.show() + "+" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			return (term.evaluate() + expression.evaluate());
		}
		catch(NullPointerException e1)
		{
			throw new MathematicalSyntaxException(this.getClass().getName(), "Syntax error");
		}
	}

}