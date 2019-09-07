package com.anu.calculator;

import android.util.Log;

public abstract class ExpressionParserException extends Exception {

    private String source;
    private String errorMessage;

    public ExpressionParserException(String source, String errorMessage)
    {
        this.errorMessage = errorMessage;
        this.source = source;
        logMe();
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Default logging method for ExpressionParserException.
     */
    public void logMe()
    {
        Log.d(source, getErrorMessage(), this);
    }
}
