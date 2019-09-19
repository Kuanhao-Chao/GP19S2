package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

/**
 * An exception class that handles division by zero.
 *
 * @author Samuel Brookes (u5380100)
 */

public class InfinityException extends ParserException {
    public InfinityException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
