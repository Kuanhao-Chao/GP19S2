package com.anu.calculator.expressionparser;
import java.util.Random;

/**
 * RandExp: This class is used to represent a random number
 */

public class RandExp extends Exp {
	private double randDouble;
	private Random rand;

	public RandExp() {
		rand = new Random();
		randDouble = rand.nextDouble();
	}

	@Override
	public String show() {
		return "" + randDouble;
	}

	@Override
	public double evaluate() {
		return randDouble;
	}
}
