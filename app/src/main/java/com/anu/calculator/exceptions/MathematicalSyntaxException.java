package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

/**
 * A catch-all exception class that handles general errors in mathematical syntax
 *
 * @author Samuel Brookes (u5380100)
 */

public class MathematicalSyntaxException extends ParserException {
    public MathematicalSyntaxException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
