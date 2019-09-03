package com.anu.calculator.expressionparser;

/**
 * PermutationExp: This class is used to represent an expression of a permutation
 */

public class PermutationExp extends Exp {
	private Exp n;
	private Exp k;

	public PermutationExp(Exp n, Exp k) {
		this.n = n;
		this.k = k;
	}

	@Override
	public String show() {
		return "nPr(" + n.show() + "," + k.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExp numerator = new FactorialExp(n);
		FactorialExp denominator = new FactorialExp(new SubtractExp(n, k));
		return numerator.evaluate() / denominator.evaluate();
	}
}
