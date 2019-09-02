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
    public void next()
    {
        //remove whitespace
        _buffer = _buffer.trim();

        //if the string is empty, set currentToken to null
        if(_buffer.isEmpty())
        {
            currentToken = null;
            return;
        }

        //assign a new Token to currentToken
        if(_buffer.charAt(0) == '\u002b') currentToken = new Token("\u002b", Token.Type.ADD);
        else if(_buffer.charAt(0) == '\u002d') currentToken = new Token("\u002d", Token.Type.SUBTRACT);
        else if(_buffer.charAt(0) == '\u002f') currentToken = new Token("\u002f", Token.Type.DIVIDE);
        else if(_buffer.charAt(0) == '\u0028') currentToken = new Token("\u0028", Token.Type.LEFT_PARENTHESIS);
        else if(_buffer.charAt(0) == '\u0029') currentToken = new Token("\u0029", Token.Type.RIGHT_PARENTHESIS);
        else if(_buffer.charAt(0) == '\u0021') currentToken = new Token("\u0021", Token.Type.FACTORIAL);
        else if(_buffer.charAt(0) == '\u002c') currentToken = new Token("\u002c", Token.Type.COMMA);
        else if(_buffer.charAt(0) == '\u0065') currentToken = new Token("\u0065", Token.Type.E);
        else if(_buffer.charAt(0) == '\u02e3') currentToken = new Token("\u02e3", Token.Type.MULTIPLY);
        else if(_buffer.charAt(0) == '\u0025') currentToken = new Token("\u0025", Token.Type.PERCENT);
        else if(_buffer.charAt(0) == '\u03c0') currentToken = new Token("\u03c0", Token.Type.PI);
        else if(_buffer.charAt(0) == '\u221a') currentToken = new Token("\u221a", Token.Type.SQUARE_ROOT);
        else if(_buffer.charAt(0) == '\u221b') currentToken = new Token("\u221b", Token.Type.CUBED_ROOT);
        else if(_buffer.charAt(0) == '\u2010') currentToken = new Token("\u2010", Token.Type.NEGATIVE);
        else if(_buffer.charAt(0) == '\u0072') currentToken = new Token("rand", Token.Type.RANDOM_NUMBER);
        else if(_buffer.charAt(0) == '\u0073')
        {
            if(_buffer.charAt(3) == '\u207b') currentToken = new Token("sin" + "\u207b" + "\u00b9", Token.Type.ARC_SINE);
            else currentToken = new Token("sin", Token.Type.SINE);
        }
        else if(_buffer.charAt(0) == '\u0063')
        {
            if(_buffer.charAt(3) == '\u207b') currentToken = new Token("cos" + "\u207b" + "\u00b9", Token.Type.ARC_COSINE);
            else currentToken = new Token("cos", Token.Type.COSINE);
        }
        else if(_buffer.charAt(0) == '\u0074')
        {
            if(_buffer.charAt(3) == '\u207b') currentToken = new Token("tan" + "\u207b" + "\u00b9", Token.Type.ARC_TANGENT);
            else currentToken = new Token("tan", Token.Type.TANGENT);
        }
        else if(_buffer.charAt(0) == '\u006C')
        {
            if(_buffer.charAt(1) == '\u006e') currentToken = new Token("ln", Token.Type.LOG_NATURAL);
            else currentToken = new Token("log" + "\u2081" + "\u2080", Token.Type.LOG_TEN);
        }
        else if(_buffer.charAt(0) == '\u006e')
        {
            if(_buffer.charAt(1) == '\u0050') currentToken = new Token("nPr", Token.Type.PERMUTATION);
            else currentToken = new Token("nCr", Token.Type.COMBINATION);
        }
        else if(isPowerDigit(_buffer.charAt(0)))
        {
            int i;
            for(i=0; i<_buffer.length(); i++)
            {
                if(!isPowerDigit(_buffer.charAt(i))) break;
            }
            currentToken = new Token(_buffer.substring(0, i), Token.Type.EXPONENT);
        }
        else if(Character.isDigit(_buffer.charAt(0)))
        {
            int i;
            for(i=0; i<_buffer.length(); i++)
            {
                if(!Character.isDigit(_buffer.charAt(i)))
                {
                    if(_buffer.charAt(i) != '.') break;
                }
            }
            currentToken = new Token(_buffer.substring(0, i), Token.Type.DOUBLE);
        }
        else currentToken = new Token(_buffer.substring(0, 1), Token.Type.UNKNOWN_VARIABLE);

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

    /**
     * Checks whether a digit is a 'PowerDigit', or part of an exponent.
     * @param c
     * @return type: boolean
     */
    private boolean isPowerDigit(char c)
    {
        for(Token.PowerDigit pd : Token.PowerDigit.values())
        {
            if(c == pd.getUnicode()) return true;
        }
        return false;
    }
}