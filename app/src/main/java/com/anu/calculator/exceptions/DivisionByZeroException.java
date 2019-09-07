package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

public class DivisionByZeroException extends ParserException {
    public DivisionByZeroException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
