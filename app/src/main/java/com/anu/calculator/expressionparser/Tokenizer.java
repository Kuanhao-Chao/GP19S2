package com.anu.calculator.expressionparser;

/**
 * Tokenizer: This is the tokenizer that converts components of a mathematical expression in
 * String form into tokens for the parser. The Tokenizer next() method works in reverse.
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 *  - Wrote the next() method
 */

public class Tokenizer {

    private String _buffer;
    private Token currentToken;

    public Tokenizer(String text)
    {
        _buffer = text;
        next();
    }

    public Token current()
    {
        return currentToken;
    }

    public boolean hasNext()
    {
        return currentToken != null;
    }

    public Token checkAhead(int numTokens)
    {
        Tokenizer checker = new Tokenizer(_buffer);
        while(numTokens > 1 && checker.current() != null)
        {
            checker.next();
            numTokens--;
        }
        return checker.current();
    }

    public void next()
    {
        _buffer = _buffer.trim();

        if(_buffer.equals(""))
        {
            currentToken = null;
            return;
        }

        char lastChar = _buffer.charAt(_buffer.length() - 1);

        if(lastChar == '+')
            currentToken = new Token("+", Token.Type.ADD);
        else if(lastChar == '-')
            currentToken = new Token("-", Token.Type.SUBTRACT);
        else if(lastChar == Scripts.Operators.DIVIDE.getUnicode())
            currentToken = new Token("" + Scripts.Operators.DIVIDE.getUnicode(), Token.Type.DIVIDE);
        else if(lastChar == '(')
            currentToken = new Token("(", Token.Type.LEFT_PARENTHESIS);
        else if(lastChar == '{')
            currentToken = new Token("{", Token.Type.LEFT_BRACE);
        else if(lastChar == '[')
            currentToken = new Token("[", Token.Type.LEFT_BRACKET);
        else if(lastChar == ')')
            currentToken = new Token(")", Token.Type.RIGHT_PARENTHESIS);
        else if(lastChar == '}')
            currentToken = new Token("}", Token.Type.RIGHT_BRACE);
        else if(lastChar == ']')
            currentToken = new Token("]", Token.Type.RIGHT_BRACKET);
        else if(lastChar == '!')
            currentToken = new Token("!", Token.Type.FACTORIAL);
        else if(lastChar == 'e')
            currentToken = new Token("e", Token.Type.E);
        else if(lastChar == Scripts.Operators.MULTIPLY.getUnicode())
            currentToken = new Token("" + Scripts.Operators.MULTIPLY.getUnicode(), Token.Type.MULTIPLY);
        else if(lastChar == '%')
            currentToken = new Token("%", Token.Type.PERCENT);
        else if(lastChar == Scripts.Operators.PI.getUnicode())
            currentToken = new Token("" + Scripts.Operators.PI.getUnicode(), Token.Type.PI);
        else if(lastChar == Scripts.Operators.SQRT.getUnicode())
            currentToken = new Token("" + Scripts.Operators.SQRT.getUnicode(), Token.Type.SQUARE_ROOT);
        else if(lastChar == Scripts.Operators.CUBE_ROOT.getUnicode())
            currentToken = new Token("" + Scripts.Operators.CUBE_ROOT.getUnicode(), Token.Type.CUBED_ROOT);
        else if(lastChar == 'd')
            currentToken = new Token("rand", Token.Type.RANDOM_NUMBER);
        else if(lastChar == '^')
            currentToken = new Token("^", Token.Type.POWER);
        else if(lastChar == Scripts.SuperScript.TWO.getUnicode())
            currentToken = new Token("" + Scripts.SuperScript.TWO.getUnicode(), Token.Type.SQUARE);
        else if(lastChar == Scripts.SuperScript.THREE.getUnicode())
            currentToken = new Token("" + Scripts.SuperScript.THREE.getUnicode(), Token.Type.CUBE);
        else if(lastChar == 's')
            currentToken = new Token("cos", Token.Type.COSINE);
        else if(lastChar == Scripts.SubScript.ZERO.getUnicode())
            currentToken = new Token("log" +
                    Scripts.SubScript.ONE.getUnicode() +
                    Scripts.SubScript.ZERO.getUnicode(), Token.Type.LOG_TEN);
        else if(lastChar == 'n')
        {
            switch(_buffer.charAt(_buffer.length() - 2))
            {
                case 'i': currentToken = new Token("sin", Token.Type.SINE); break;
                case 'a': currentToken = new Token("tan", Token.Type.TANGENT); break;
                case 'l': currentToken = new Token("ln", Token.Type.LOG_NATURAL);
            }
        }
        else if(lastChar == Scripts.SuperScript.ONE.getUnicode())
        {
            switch(_buffer.charAt(_buffer.length() - 4))
            {
                case 'i': currentToken = new Token("sin" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_SINE); break;
                case 'o': currentToken = new Token("cos" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_COSINE); break;
                case 'a': currentToken = new Token("tan" + Scripts.SuperScript.MINUS.getUnicode() +
                        Scripts.SuperScript.ONE.getUnicode(), Token.Type.ARC_TANGENT);
            }
        }
        else if(lastChar == 'r')
        {
            switch(_buffer.charAt(_buffer.length() - 2))
            {
                case 'P': currentToken = new Token("nPr", Token.Type.PERMUTATION); break;
                case 'C': currentToken = new Token("nCr", Token.Type.COMBINATION);
            }
        }
        else if(Character.isDigit(lastChar))
        {
            int i;
            for(i=_buffer.length() - 1; i>=0; i--)
            {
                if(!Character.isDigit(_buffer.charAt(i)))
                {
                    if(_buffer.charAt(i) != '.') break;
                }
            }
            currentToken = new Token(_buffer.substring(i + 1), Token.Type.DOUBLE);
        }
        else currentToken = new Token(_buffer.substring(_buffer.length() - 1), Token.Type.UNKNOWN_VARIABLE);

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(0, _buffer.length() - tokenLen);
    }
}