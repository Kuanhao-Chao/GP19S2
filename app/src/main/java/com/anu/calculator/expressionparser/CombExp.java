package com.anu.calculator.expressionparser;

/**
 * CombExp: This class is used to represent an expression of a combination
 */

public class CombExp extends Exp {
	private Exp n;
	private Exp k;

	public CombExp(Exp n, Exp k) {
		this.n = n;
		this.k = k;
	}

	@Override
	public String show() {
		return "comb(" + n.show() + "," + k.show() + ")";
	}

	@Override
	public double evaluate() {
		FacExp numerator = new FacExp(n);
		MultExp denominator = new MultExp(new FacExp(new SubExp(n, k)), new FacExp(k));
		return numerator.evaluate() / denominator.evaluate();
	}
}
