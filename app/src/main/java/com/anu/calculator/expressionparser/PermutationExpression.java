package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;

/**
 * PermutationExpression: This class is used to represent an expression of a permutation
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 09/05/2019: Refactored class to implement Expression interface
 * 	- 09/05/2019: Refactored name from Exp to Expression
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
		return "(" + n.show() + "nPr" + r.show() + ")";
	}

	@Override
	public double evaluate() {
		FactorialExpression numerator = new FactorialExpression(n);
		FactorialExpression denominator = new FactorialExpression(new SubtractExpression(n, r));
		return numerator.evaluate() / denominator.evaluate();
	}
}
