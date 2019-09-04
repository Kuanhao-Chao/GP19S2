package com.anu.calculator.expressionparser;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 *
 */

public class Token {

    public enum Type {
        ADD(2),
        ARC_COSINE(1),
        ARC_SINE(1),
        ARC_TANGENT(1),
        COMMA(0),
        COSINE(1),
        COMBINATION(2),
        CUBE(1),
        CUBED_ROOT(1),
        DIVIDE(2),
        DOUBLE(0),
        E(0),
        FACTORIAL(1),
        LEFT_PARENTHESIS(0),
        LOG_TEN(1),
        LOG_NATURAL(1),
        NEGATIVE(1),
        MULTIPLY(2),
        PERCENT(1),
        PERMUTATION(2),
        PI(0),
        POWER(2),
        RANDOM_NUMBER(0),
        RIGHT_PARENTHESIS(0),
        SINE(1),
        SUBTRACT(2),
        SQUARE(1),
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
