package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

/**
 * An exception that handles loops in function stacks.
 *
 * @author: Samuel Brookes
 */
public class FunctionLoopException extends ParserException {
    public FunctionLoopException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
