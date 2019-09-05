package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PermutationExpression: This class is used to represent an expression of a permutation
 */

public class PermutationExpression implements Expression {
	private Expression n;
	private Expression r;

	PermutationExpression(Expression n, Expression r) {
		this.n = n;
		this.r = r;
	}

	@Override
	public String show() {
		return "(" + n.show() + ")nPr(" + r.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExpression numerator = new FactorialExpression(n);
		FactorialExpression denominator = new FactorialExpression(new SubtractExpression(n, r));
		return numerator.evaluate() / denominator.evaluate();
	}
}
