package com.anu.calculator.expressionparser;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 *
 */

public class Token {

    public enum PowerDigit
    {
        PD_ONE('\u00b9', 1),
        PD_TWO('\u00b2', 2),
        PD_THREE('\u00b3', 3),
        PD_FOUR('\u2074', 4),
        PD_FIVE('\u2075', 5),
        PD_SIX('\u2076', 6),
        PD_SEVEN('\u2077', 7),
        PD_EIGHT('\u2078', 8),
        PD_NINE('\u2079', 9),
        PD_ZERO('\u2070', 10);

        private char unicode;
        private int intValue;

        PowerDigit(char unicode, int intValue)
        {
            this.unicode = unicode;
            this.intValue = intValue;
        }

        public char getUnicode() { return unicode; }
        public int getIntValue() { return intValue; }
    }

    public enum Type {
        ADD(2),
        ARC_COSINE(1),
        ARC_SINE(1),
        ARC_TANGENT(1),
        COMMA(0),
        COSINE(1),
        COMBINATION(2),
        CUBED_ROOT(1),
        DIVIDE(2),
        DOUBLE(0),
        E(0),
        EXPONENT(1),
        FACTORIAL(1),
        LEFT_PARENTHESIS(0),
        LOG_TEN(1),
        LOG_NATURAL(1),
        NEGATIVE(1),
        MULTIPLY(2),
        PERCENT(1),
        PERMUTATION(2),
        PI(0),
        RANDOM_NUMBER(0),
        RIGHT_PARENTHESIS(0),
        SINE(1),
        SUBTRACT(2),
        SQUARE_ROOT(1),
        TANGENT(1),
        UNKNOWN_VARIABLE(1);

        private int numArgs;

        Type(int numArgs)
        {
            this.numArgs = numArgs;
        }

        public int args()
        {
            return numArgs;
        }
    }

    private String _token;
    private Type _type;
    
    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }
    
    public String token() {
        return _token;
    }
    
    public Type type() {
        return _type;
    }
}
