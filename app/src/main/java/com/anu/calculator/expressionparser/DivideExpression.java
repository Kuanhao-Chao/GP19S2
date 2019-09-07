package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.DivisionByZeroException;

/**
 * DivideExpression: This class is used to represent the expression of division
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Added the DIVIDE unicode to the show() method
 */

public class DivideExpression implements Expression {
	private Expression factor;
	private Expression term;

	DivideExpression(Expression factor, Expression term) {
		this.factor = factor;
		this.term = term;
	}

	@Override
	public String show() {
		return "(" + factor.show() + Scripts.Operators.DIVIDE.getUnicode() + term.show() + ")";
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			double checkForZero = term.evaluate();
			if(checkForZero == 0) throw new DivisionByZeroException(this.getClass().getName(), "Cannot divide by zero");
			else return (factor.evaluate() / term.evaluate());
		}
		catch(ParserException e)
		{
			throw e;
		}
	}


}