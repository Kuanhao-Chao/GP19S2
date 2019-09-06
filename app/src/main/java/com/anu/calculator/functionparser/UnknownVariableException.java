package com.anu.calculator.functionparser;

import com.anu.calculator.ParserException;

/**
 * This exception handles the scenario when a user provides a function referencing a variable that
 * has not yet been declared in the application.
 */
public class UnknownVariableException extends ParserException {
    public UnknownVariableException(String errorMessage) {
        throw new RuntimeException("Unknown Variable '"+errorMessage+"', try defining it first.");
    }
}
