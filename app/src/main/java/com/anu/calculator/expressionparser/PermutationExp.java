package com.anu.calculator.expressionparser;

/**
 * PermutationExp: This class is used to represent an expression of a permutation
 */

public class PermutationExp extends Exp {
	private Exp n;
	private Exp r;

	public PermutationExp(Exp n, Exp r) {
		this.n = n;
		this.r = r;
	}

	@Override
	public String show() {
		return "(" + n.show() + ")nPr(" + r.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExp numerator = new FactorialExp(n);
		FactorialExp denominator = new FactorialExp(new SubtractExp(n, r));
		return numerator.evaluate() / denominator.evaluate();
	}
}
