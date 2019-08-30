package com.anu.calculator.expressionparser;

/**
 * Name: Tokenizer.java
 * <p>
 * Remind:
 * 1. Your job is to implement next() method.
 * 2. Please do not modify anything else.
 * 3. Check the correctness of implementation via "com.anu.calculator.TokenizerTest.java" before the submission.
 * 4. You may create additional fields or methods to finish your implementation.
 *
 * @author: Michael Betterton
 * @Uid: u6797866
 * @Date: 15 Aug 2019
 */

public class Tokenizer {

    private String _buffer;        //save text
    private Token currentToken;    //save token extracted from next()

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
     * **** please do not modify this part ****
     */
    public Tokenizer(String text) {
        _buffer = text;        // save input text (string)
        next();        // extracts the first token.
    }

    /**
     * This function will find and extract a next token from {@code _buffer} and
     * save the token to {@code currentToken}.
     */
    public void next() {
        _buffer = _buffer.trim(); // remove whitespace

        if (_buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }
        char firstChar = _buffer.charAt(0);
        if (firstChar == '+')
            currentToken = new Token("+", Token.Type.ADD);
        if (firstChar == '-')
            currentToken = new Token("-", Token.Type.SUB);
        if (firstChar == '*')
            currentToken = new Token("*", Token.Type.MUL);
        if (firstChar == '/')
            currentToken = new Token("/", Token.Type.DIV);
        if (firstChar == '(')
            currentToken = new Token("(", Token.Type.LBRA);
        if (firstChar == ')')
            currentToken = new Token(")", Token.Type.RBRA);
        if (Character.isDigit(firstChar)) {
            StringBuilder sb = new StringBuilder();
            String tempbfr = _buffer;
            int currPos = 0;
            while (Character.isDigit(tempbfr.charAt(currPos))) {
                sb.append(tempbfr.charAt(currPos));
                currPos ++;
                if(currPos >= tempbfr.length()){
                    break;
                }
            }
            currentToken = new Token(sb.toString(), Token.Type.INT);
        }
        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     * returned the current token extracted by {@code next()}
     * **** please do not modify this part ****
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * check whether there still exists another tokens in the buffer or not
     * **** please do not modify this part ****
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}