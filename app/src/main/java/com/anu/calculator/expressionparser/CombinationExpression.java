package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParserException;

/**
 * CombinationExpression: This class is used to represent an expression of a combination
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class CombinationExpression implements Expression {
	private Expression n;
	private Expression r;

	CombinationExpression(Expression n, Expression r) {
		this.n = n;
		this.r = r;
	}

	@Override
	public String show() {
		return "(" + n.show() + "nCr" + r.show() + ")";
	}

	@Override
	public double evaluate() throws ExpressionParserException {
		FactorialExpression numerator = new FactorialExpression(n);
		MultiplyExpression denominator = new MultiplyExpression(new FactorialExpression(new SubtractExpression(n, r)), new FactorialExpression(r));
		return numerator.evaluate() / denominator.evaluate();
	}
}
