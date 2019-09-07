package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * SubtractExpression: This class is used to represent the expression of subtraction
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Added the SUBTRACT unicode to the show() method
 */

public class SubtractExpression implements Expression {
	private Expression expression;
	private Expression term;
	private boolean negative;

	SubtractExpression(Expression term, Expression expression) {
		this.term = term;
		this.expression = expression;
		try
		{
			if(term instanceof DoubleExpression && term.evaluate() == 0)
			{
				negative = true;
			}
		}
		catch(ParserException e)
		{
			negative = false;
		}
	}

	@Override
	public String show() {
		if(negative)
		{
			return "(-" + expression.show() + ")";
		}
		else return "(" + term.show() + "-" + expression.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		return (term.evaluate() - expression.evaluate());
	}

}
