package com.anu.calculator.expressions;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;

/**
 * DoubleExpression: This class is used to represent a double literal
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class DoubleExpression implements Expression {

	private static final String TAG = "DOUBLE_EXPRESSION";
	private double value;
	private Integer precision;

	public DoubleExpression(double value) {
		this.value = value;
	}

	@Override
	public String show() {
		return "" + value;
	}

	@Override
	public double evaluate() throws ParserException
	{
		//if the value of evaluation is too large for a double type, throw an infinity exception
		if(value == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me");

		//check if this expression is the root of the parsing tree
		if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", value));
		else return value;
	}

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

}
