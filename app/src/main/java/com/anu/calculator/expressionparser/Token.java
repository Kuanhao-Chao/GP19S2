package com.anu.calculator.expressionparser;

/**
 * Token class to save extracted token from tokenizer.
 * Each token has its surface form saved in {@code _token}
 * and type saved in {@code _type} which is one of the predefined type in Type enum.
 *
 */

public class Token {

    public enum Type {
        ADD('n'),
        ARC_COSINE('l'),
        ARC_SINE('l'),
        ARC_TANGENT('l'),
        COSINE('l'),
        COMBINATION('b'),
        CUBE('t'),
        CUBED_ROOT('l'),
        DIVIDE('n'),
        DOUBLE('n'),
        E('n'),
        FACTORIAL('t'),
        LEFT_PARENTHESIS('n'),
        LOG_TEN('l'),
        LOG_NATURAL('l'),
        NEGATIVE('l'),
        MULTIPLY('n'),
        PERCENT('t'),
        PERMUTATION('b'),
        PI('n'),
        POWER('b'),
        RANDOM_NUMBER('n'),
        RIGHT_PARENTHESIS('n'),
        SINE('l'),
        SUBTRACT('n'),
        SQUARE('t'),
        SQUARE_ROOT('l'),
        TANGENT('l'),
        UNKNOWN_VARIABLE('n');

        private char position; //tokenType: l = leading, t = trailing, b = both, n = not applicable

        Type(char position)
        {
            this.position = position;
        }

        public boolean isLeading(){ return position == 'l'; }
        public boolean isTrailing(){ return position == 't'; }
        public boolean isLeadingAndTrailing() { return position == 'b'; }
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
