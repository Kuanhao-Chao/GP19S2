package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

/**
 * An exception that handles the case in which an unknown variable has no value assigned to it
 *
 * @author Samuel Brookes (u5380100)
 */

public class UnassignedVariableException extends ParserException {
    public UnassignedVariableException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
