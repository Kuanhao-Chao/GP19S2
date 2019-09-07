package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.UnassignedVariableException;

/**
 * UnknownVariableExpression: This class is used to represent a variable with an unknown value,
 * i.e. for formulas.
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * 	- 05/09/2019: Refactored class to implement Expression interface
 * 	- 05/09/2019: Refactored name from Exp to Expression
 */

public class UnknownVariableExpression implements Expression {

	private final String TAG = "UNKNOWN_VARIABLE_EXPRESSION";
	private char variable;
	private Expression value;

	public UnknownVariableExpression(char variable) {
		this.variable = variable;
		this.value = null;
	}

	/**
	 * An alternative constructor in case the value of
	 * the variable is known at the time of instantiation.
	 */

	public UnknownVariableExpression(char variable, Expression value)
	{
		this.variable = variable;
		this.value = value;
	}

	@Override
	public String show() {
		return "" + variable;
	}

	@Override
	public double evaluate() throws ParserException {
		try
		{
			if(hasValue())
			{
				return value.evaluate();
			}
			else throw new UnassignedVariableException(TAG, "Variable " + variable + " has no value assigned to it.");
		}
		catch(ParserException e)
		{
			e.logMe();
			throw e;
		}
	}

	public void assignValue(Expression value)
	{
		this.value = value;
	}

	public boolean hasValue()
	{
		return value != null;
	}
}
