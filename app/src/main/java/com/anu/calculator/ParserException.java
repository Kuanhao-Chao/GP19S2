package com.anu.calculator;

import android.util.Log;

public abstract class ParserException extends Exception {

    private String tag;
    private String errorMessage;

    public ParserException(String tag, String errorMessage)
    {
        this.errorMessage = errorMessage;
        this.tag = tag;
        logMe();
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * Default logging method for ParserException.
     */
    public void logMe()
    {
        Log.d(tag, getErrorMessage(), this);
    }
}
