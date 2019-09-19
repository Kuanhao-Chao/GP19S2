package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;

/**
 * CombinationExpression: This class is used to represent an expression of a combination
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class CombinationExpression implements Expression {

	private static final String TAG = "COMBINATION_EXPRESSION";
	private Expression n;
	private Expression r;
	private Integer precision;

	@Override
	public void updatePrecision(Integer precision)
	{
		this.precision = precision;
	}

	CombinationExpression(Expression n, Expression r) {
		this.n = n;
		this.r = r;
	}

	@Override
	public String show() {
		return "(" + n.show() + "nCr" + r.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try {

			FactorialExpression numerator = new FactorialExpression(n);
			MultiplyExpression denominator = new MultiplyExpression(new FactorialExpression(new SubtractExpression(n, r)), new FactorialExpression(r));
			double evaluation = numerator.evaluate() / denominator.evaluate();

			if(evaluation == Double.POSITIVE_INFINITY) throw new InfinityException(TAG, "Number is too large for little old me");
			if(precision != null) return Double.parseDouble(String.format("%." + precision + "f", evaluation));
			else return evaluation;
		}
		catch(NullPointerException e)
		{
			throw new MathematicalSyntaxException(TAG, "Syntax error");
		}
	}
}
