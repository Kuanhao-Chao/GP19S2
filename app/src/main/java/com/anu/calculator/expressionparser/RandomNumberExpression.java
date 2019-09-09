package com.anu.calculator.expressionparser;
import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;

import java.util.Random;

/**
 * RandomNumberExpression: This class is used to represent a random number
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class RandomNumberExpression implements Expression {
	private double randDouble;
	private Random rand;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	RandomNumberExpression() {
		rand = new Random();
		randDouble = rand.nextDouble();
	}

	@Override
	public String show() {
		return "rand(" + randDouble + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", randDouble));
		else return randDouble;
	}

	public String getValue(){ return String.valueOf(randDouble); }
}
