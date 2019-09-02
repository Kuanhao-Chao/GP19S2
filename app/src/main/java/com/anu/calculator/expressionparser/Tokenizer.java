package com.anu.calculator.expressionparser;

public class Tokenizer {

    private String _buffer;        //save text
    private Token currentToken;    //save token extracted from next()

    /**
     * Tokenizer class constructor
     * The constructor extracts the first token and save it to currentToken
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
        if (firstChar == 'x')
            currentToken = new Token("x", Token.Type.MUL);
        if (firstChar == '/')
            currentToken = new Token("/", Token.Type.DIV);
        if (firstChar == '(')
            currentToken = new Token("(", Token.Type.LBRA);
        if (firstChar == ')')
            currentToken = new Token(")", Token.Type.RBRA);
        if(firstChar == 'f')
            currentToken = new Token("fac", Token.Type.FAC);
        if (firstChar == ',')
            currentToken = new Token(",", Token.Type.COMMA);
        if (firstChar == '#')
        {
            if(_buffer.charAt(1) == 'P') currentToken = new Token("#PI", Token.Type.PI);
            else if(_buffer.charAt(1) == 'E') currentToken = new Token("#E", Token.Type.E);
            else if(_buffer.charAt(1) == 'R') currentToken = new Token("#R", Token.Type.RAND);
            else currentToken = new Token(_buffer.substring(0, 2), Token.Type.UNKVAR);
        }
        if (firstChar == 's')
        {
            if(_buffer.charAt(1) == 'q') currentToken = new Token("sqrt", Token.Type.SQRT);
            else if(_buffer.charAt(3) == '-') currentToken = new Token("sin-1", Token.Type.ASIN);
            else currentToken = new Token("sin", Token.Type.SIN);
        }
        if (firstChar == 'c')
        {
            if(_buffer.charAt(2) == 'm') currentToken = new Token("comb", Token.Type.COMB);
            else if(_buffer.charAt(3) == '-') currentToken = new Token("cos-1", Token.Type.ACOS);
            else currentToken = new Token("cos", Token.Type.COS);
        }
        if (firstChar == 't')
        {
            if(_buffer.charAt(3) == '-') currentToken = new Token("tan-1", Token.Type.ATAN);
            else currentToken = new Token("tan", Token.Type.TAN);
        }
        if (firstChar == 'l')
        {
            if(_buffer.charAt(3) == '1') currentToken = new Token("log10", Token.Type.LOGTEN);
            else currentToken = new Token("log", Token.Type.LOGNAT);
        }
        if (firstChar == 'p')
        {
            if(_buffer.charAt(1) == 'w') currentToken = new Token("pwr", Token.Type.PWR);
            else currentToken = new Token("perm", Token.Type.PERM);
        }
        if(Character.isDigit(firstChar))
        {
            int pos = 0;
            while(pos < _buffer.length() && (_buffer.charAt(pos) == '.' || Character.isDigit(_buffer.charAt(pos))))
            {
                pos++;
            }
            currentToken = new Token(_buffer.substring(0, pos), Token.Type.DOUBLE);
        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    /**
     * returned the current token extracted by {@code next()}
     *
     * @return type: Token
     */
    public Token current() {
        return currentToken;
    }

    /**
     * check whether there still exists another tokens in the buffer or not
     *
     * @return type: boolean
     */
    public boolean hasNext() {
        return currentToken != null;
    }
}