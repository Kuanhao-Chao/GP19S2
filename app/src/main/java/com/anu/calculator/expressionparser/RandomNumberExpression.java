package com.anu.calculator.expressionparser;
import com.anu.calculator.Expression;

import java.util.Random;

/**
 * RandomNumberExpression: This class is used to represent a random number
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
 */

public class RandomNumberExpression implements Expression {
	private double randDouble;
	private Random rand;

	RandomNumberExpression() {
		rand = new Random();
		randDouble = rand.nextDouble();
	}

	@Override
	public String show() {
		return "rand(" + randDouble + ")";
	}

	@Override
	public double evaluate() {
		return randDouble;
	}

	public String getValue(){ return String.valueOf(randDouble); }
}
