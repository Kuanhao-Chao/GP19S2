package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * AddExpression: This class is used to represent the expression of addition
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - 08/09/2019: Added precision, updatePrecision, updated evaluate
 */

public class AddExpression implements Expression {

	private static final String TAG = "ADD_EXPRESSION";
	private Expression expression;
	private Expression term;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	public AddExpression(Expression term, Expression expression) {
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
			double evaluation = term.evaluate() + expression.evaluate();

			if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me");
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e1)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}
}