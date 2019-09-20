package com.anu.calculator.utilities;

/**
 *  Scripts: contains three enumerations (SuperScript, SubScript and Operators)
 *  that make it easier to retrieve the unicode characters used by the calculator
 *
 * @author: Samuel Brookes (u5380100)
 */

public class Scripts {

    public enum SuperScript
    {
        ONE('\u00b9'),
        TWO('\u00b2'),
        THREE('\u00b3'),
        FOUR('\u2074'),
        FIVE('\u2075'),
        SIX('\u2076'),
        SEVEN('\u2077'),
        EIGHT('\u2078'),
        NINE('\u2079'),
        ZERO('\u2070'),
        MINUS('\u207b'),
        LEFT_PARENTHESIS('\u207d'),
        RIGHT_PARENTHESIS('\u207e');

        private char unicode;

        SuperScript(char unicode)
        {
            this.unicode = unicode;
        }

        public char getUnicode() { return unicode; }
    }

    public enum SubScript
    {
        ONE('\u2081'),
        TWO('\u2082'),
        THREE('\u2083'),
        FOUR('\u2084'),
        FIVE('\u2085'),
        SIX('\u2086'),
        SEVEN('\u2087'),
        EIGHT('\u2088'),
        NINE('\u2089'),
        ZERO('\u2080'),
        LEFT_PARENTHESIS('\u208d'),
        RIGHT_PARENTHESIS('\u208e');

        private char unicode;

        SubScript(char unicode)
        {
            this.unicode = unicode;
        }

        public char getUnicode() { return unicode; }
    }

    public enum Operators
    {
        SQRT('\u221a'),
        CUBE_ROOT('\u221b'),
        PI('\u03c0'),
        MULTIPLY('\u00d7'),
        DIVIDE('\u00f7'),
        DEGREES('\u00b0'),
        RADIANS('\u33ad');

        private char unicode;

        Operators(char unicode)
        {
            this.unicode = unicode;
        }

        public char getUnicode(){ return unicode; }
    }
}
