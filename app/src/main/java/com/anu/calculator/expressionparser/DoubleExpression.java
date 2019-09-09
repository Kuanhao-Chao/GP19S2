package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * DoubleExpression: This class is used to represent a double literal
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class DoubleExpression implements Expression {
	private double value;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	DoubleExpression(double value) {
		this.value = value;
	}

	@Override
	public String show() {
		return "" + value;
	}

	@Override
	public double evaluate() throws ParserException
	{
		if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", value));
		else return value;
	}

}
