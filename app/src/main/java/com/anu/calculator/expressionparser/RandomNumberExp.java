package com.anu.calculator.expressionparser;
import java.util.Random;

/**
 * RandomNumberExp: This class is used to represent a random number
 */

public class RandomNumberExp extends Exp {
	private double randDouble;
	private Random rand;

	public RandomNumberExp() {
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
