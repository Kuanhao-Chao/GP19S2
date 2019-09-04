package com.anu.calculator.expressionparser;

/**
 * CombinationExp: This class is used to represent an expression of a combination
 */

public class CombinationExp extends Exp {
	private Exp n;
	private Exp r;

	public CombinationExp(Exp n, Exp r) {
		this.n = n;
		this.r = r;
	}

	@Override
	public String show() {
		return "(" + n.show() + ")nCr(" + r.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExp numerator = new FactorialExp(n);
		MultiplyExp denominator = new MultiplyExp(new FactorialExp(new SubtractExp(n, r)), new FactorialExp(r));
		return numerator.evaluate() / denominator.evaluate();
	}
}
