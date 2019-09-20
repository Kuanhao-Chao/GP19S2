package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.utilities.Scripts;

/**
 * MultiplyExpression: This class is used to represent the expression of multiplication
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Added the MULTIPLY unicode to the show() method
 */

public class MultiplyExpression implements Expression {

	private static final String TAG = "MULTIPLY_EXPRESSION";
	private Expression term;
	private Expression factor;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}


	public MultiplyExpression(Expression factor, Expression term) {
		this.factor = factor;		
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.MULTIPLY.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double evaluation = factor.evaluate() * term.evaluate();

			if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me.");
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}

}