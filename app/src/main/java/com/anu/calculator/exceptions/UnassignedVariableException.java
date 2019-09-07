package com.anu.calculator.exceptions;

import com.anu.calculator.ExpressionParserException;

public class UnassignedVariableException extends ExpressionParserException {
    public UnassignedVariableException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
