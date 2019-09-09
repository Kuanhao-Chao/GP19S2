package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

public class NothingEnteredException extends ParserException {
    public NothingEnteredException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
