package com.anu.calculator.exceptions;

import com.anu.calculator.ParserException;

/**
 * An exception that handles the case in which the user presses 'Solve' without entering anything
 *
 * @author Samuel Brookes (u5380100)
 */

public class NothingEnteredException extends ParserException {
    public NothingEnteredException(String source, String errorMessage)
    {
        super(source, errorMessage);
    }
}
