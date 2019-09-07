package com.anu.calculator.exceptions;

import com.anu.calculator.ExpressionParserException;

public class DivisionByZeroException extends ExpressionParserException {
    public DivisionByZeroException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
