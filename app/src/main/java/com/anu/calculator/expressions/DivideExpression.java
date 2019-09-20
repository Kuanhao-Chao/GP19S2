package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.DivisionByZeroException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.utilities.Scripts;

/**
 * DivideExpression: This class is used to represent the expression of division
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Added the DIVIDE unicode to the show() method
 */

public class DivideExpression implements Expression {

	private static final String TAG = "DIVIDE_EXPRESSION";
	private Expression factor;
	private Expression term;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	public DivideExpression(Expression factor, Expression term) {
		this.factor = factor;
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.DIVIDE.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double checkForZero = term.evaluate();
			if(checkForZero == 0) throw new DivisionByZeroException(TAG, "Cannot divide by zero");
			else
			{
				double evaluation = factor.evaluate() / term.evaluate();

				if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me");
				if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
				else return evaluation;
			}
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
		catch(ParserException e)
		{
			throw e;
		}
	}


}