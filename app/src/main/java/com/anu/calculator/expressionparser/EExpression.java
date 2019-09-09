package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

/**
 * EExpression: This class is used to represent the variable e
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class EExpression implements Expression {

	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	@Override
	public String show() {
		return "e";
	}

	@Override
	public double evaluate() throws ParserException {
		if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", Math.E));
		else return Math.E;
	}

}
