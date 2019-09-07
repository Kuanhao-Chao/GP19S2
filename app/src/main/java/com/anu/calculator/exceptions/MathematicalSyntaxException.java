package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

public class MathematicalSyntaxException extends ParserException {
    public MathematicalSyntaxException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
