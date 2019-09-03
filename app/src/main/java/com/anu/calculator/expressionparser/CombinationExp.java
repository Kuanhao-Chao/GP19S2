package com.anu.calculator.expressionparser;

/**
 * CombinationExp: This class is used to represent an expression of a combination
 */

public class CombinationExp extends Exp {
	private Exp n;
	private Exp k;

	public CombinationExp(Exp n, Exp k) {
		this.n = n;
		this.k = k;
	}

	@Override
	public String show() {
		return "nCr(" + n.show() + "," + k.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExp numerator = new FactorialExp(n);
		MultiplyExp denominator = new MultiplyExp(new FactorialExp(new SubtractExp(n, k)), new FactorialExp(k));
		return numerator.evaluate() / denominator.evaluate();
	}
}
