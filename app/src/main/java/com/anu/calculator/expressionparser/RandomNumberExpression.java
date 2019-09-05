package com.anu.calculator.expressionparser;
import com.anu.calculator.Expression;

import java.util.Random;

/**
 * RandomNumberExpression: This class is used to represent a random number
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
