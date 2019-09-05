package com.anu.calculator;

/**
 * Interface for Expressions, any function or operation implemented in the calculator should
 * implement this interface.
 */
public interface Expression {

	/**
	 * Show the expression in a text representation.
	 *
	 * @return The expression as a string, it should be formatted "prettily"
	 */
	String show();

	/**
	 * Evaluate the expression, returning its value as a double.
	 *
	 * @return The expressions literal result as a double.
	 */
	double evaluate();
}
