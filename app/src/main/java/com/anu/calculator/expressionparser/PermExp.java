package com.anu.calculator.expressionparser;

/**
 * PermExp: This class is used to represent an expression of a permutation
 */

public class PermExp extends Exp {
	private Exp n;
	private Exp k;

	public PermExp(Exp n, Exp k) {
		this.n = n;
		this.k = k;
	}

	@Override
	public String show() {
		return "perm(" + n.show() + "," + k.show() + ")";
	}

	@Override
	public double evaluate() {
		FacExp numerator = new FacExp(n);
		FacExp denominator = new FacExp(new SubExp(n, k));
		return numerator.evaluate() / denominator.evaluate();
	}
}
