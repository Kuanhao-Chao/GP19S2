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
        if(_buffer.charAt(0) == '+')
            currentToken = new Token("+", Token.Type.ADD);
        else if(_buffer.charAt(0) == Scripts.Operators.MINUS.getUnicode())
            currentToken = new Token("" + Scripts.Operators.MINUS.getUnicode(), Token.Type.SUBTRACT);
        else if(_buffer.charAt(0) == Scripts.Operators.DIVIDE.getUnicode())
            currentToken = new Token("" + Scripts.Operators.DIVIDE.getUnicode(), Token.Type.DIVIDE);
        else if(_buffer.charAt(0) == '(')
            currentToken = new Token("(", Token.Type.LEFT_PARENTHESIS);
        else if(_buffer.charAt(0) == ')')
            currentToken = new Token(")", Token.Type.RIGHT_PARENTHESIS);
        else if(_buffer.charAt(0) == '!')
            currentToken = new Token("!", Token.Type.FACTORIAL);
        else if(_buffer.charAt(0) == 'e')
            currentToken = new Token("e", Token.Type.E);
        else if(_buffer.charAt(0) == Scripts.Operators.MULTIPLY.getUnicode())
            currentToken = new Token("" + Scripts.Operators.MULTIPLY.getUnicode(), Token.Type.MULTIPLY);
        else if(_buffer.charAt(0) == '%')
            currentToken = new Token("%", Token.Type.PERCENT);
        else if(_buffer.charAt(0) == Scripts.Operators.PI.getUnicode())
            currentToken = new Token("" + Scripts.Operators.PI.getUnicode(), Token.Type.PI);
        else if(_buffer.charAt(0) == Scripts.Operators.SQRT.getUnicode())
            currentToken = new Token("" + Scripts.Operators.SQRT.getUnicode(), Token.Type.SQUARE_ROOT);
        else if(_buffer.charAt(0) == Scripts.Operators.CUBE_ROOT.getUnicode())
            currentToken = new Token("" + Scripts.Operators.CUBE_ROOT.getUnicode(), Token.Type.CUBED_ROOT);
        else if(_buffer.charAt(0) == Scripts.Operators.NEGATIVE.getUnicode())
            currentToken = new Token("" + Scripts.Operators.NEGATIVE.getUnicode(), Token.Type.NEGATIVE);
        else if(_buffer.charAt(0) == 'r')
            currentToken = new Token("rand", Token.Type.RANDOM_NUMBER);
        else if(_buffer.charAt(0) == '^')
            currentToken = new Token("^", Token.Type.POWER);
        else if(_buffer.charAt(0) == Scripts.SuperScript.TWO.getUnicode())
            currentToken = new Token("" + Scripts.SuperScript.TWO.getUnicode(), Token.Type.SQUARE);
        else if(_buffer.charAt(0) == Scripts.SuperScript.THREE.getUnicode())
            currentToken = new Token("" + Scripts.SuperScript.THREE.getUnicode(), Token.Type.CUBE);
        else if(_buffer.charAt(0) == 's')
        {
            if(_buffer.charAt(3) == Scripts.SuperScript.MINUS.getUnicode())
            {
                currentToken = new Token("sin" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_SINE);
            }
            else currentToken = new Token("sin", Token.Type.SINE);
        }
        else if(_buffer.charAt(0) == 'c')
        {
            if(_buffer.charAt(3) == Scripts.SuperScript.MINUS.getUnicode())
            {
                currentToken = new Token("cos" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_COSINE);
            }
            else currentToken = new Token("cos", Token.Type.COSINE);
        }
        else if(_buffer.charAt(0) == 't')
        {
            if(_buffer.charAt(3) == Scripts.SuperScript.MINUS.getUnicode())
            {
                currentToken = new Token("tan" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_TANGENT);
            }
            else currentToken = new Token("tan", Token.Type.TANGENT);
        }
        else if(_buffer.charAt(0) == 'l')
        {
            if(_buffer.charAt(1) == 'n') currentToken = new Token("ln", Token.Type.LOG_NATURAL);
            else currentToken = new Token("log" +
                    Scripts.SubScript.ONE.getUnicode() +
                    Scripts.SubScript.ZERO.getUnicode(), Token.Type.LOG_TEN);
        }
        else if(_buffer.charAt(0) == 'n')
        {
            if(_buffer.charAt(1) == 'P') currentToken = new Token("nPr", Token.Type.PERMUTATION);
            else currentToken = new Token("nCr", Token.Type.COMBINATION);
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
}