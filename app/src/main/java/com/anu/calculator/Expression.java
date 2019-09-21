package com.anu.calculator;

import java.io.Serializable;

/**
 * Interface for Expressions, any function or operation implemented in the calculator should
 * implement this interface.
 */
public interface Expression extends Serializable {

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
	double evaluate() throws ParserException;

	/**
	 * Enter a precision value into the Expression so that the top-level
	 * Expression returns an evaluation with the required precision
	 * @param precision
	 */
	void updatePrecision(Integer precision);
}
