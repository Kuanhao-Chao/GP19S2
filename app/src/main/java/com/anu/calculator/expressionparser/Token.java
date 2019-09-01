package com.anu.calculator.expressionparser;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 *
 */

public class Token {

    public enum Type {
        UNKNOWN(0),
        COMMA(0),
        DOUBLE(0),
        ADD(2),
        SUB(2),
        MUL(2),
        DIV(2),
        LBRA(0),
        RBRA(0),
        SIN(1), //sine
        ASIN(1), //arcsine
        COS(1), //cosine
        ACOS(1), //arccosine
        TAN(1), //tangent
        ATAN(1), //arctangent
        LOGTEN(1), //log base 10
        LOGNAT(1), //log base e
        PWR(2), //exponent
        FAC(1), //factorial
        SQRT(1), //square root
        RAND(0), //random number
        PERM(2), //permutation
        COMB(2), //combination
        UNKVAR(1), //unknown variable
        PI(0), //pi
        E(0); //e

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

    private String _token = "";
    private Type _type = Type.UNKNOWN;
    
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
