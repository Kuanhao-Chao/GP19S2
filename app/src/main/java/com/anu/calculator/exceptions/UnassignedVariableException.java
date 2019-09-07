package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

public class UnassignedVariableException extends ParserException {
    public UnassignedVariableException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
