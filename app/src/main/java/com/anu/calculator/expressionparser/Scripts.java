package com.anu.calculator.expressionparser;

/**
 *  Scripts: contains three enumerations (SuperScript, SubScript and Operators)
 *  that make it easier to retrieve the unicode characters used by the calculator
 *
 * @author: Samuel Brookes (u5380100)
 */

public class Scripts {

    public enum SuperScript
    {
        ONE('\u00b9', '1'),
        TWO('\u00b2', '2'),
        THREE('\u00b3', '3'),
        FOUR('\u2074', '4'),
        FIVE('\u2075', '5'),
        SIX('\u2076', '6'),
        SEVEN('\u2077', '7'),
        EIGHT('\u2078', '8'),
        NINE('\u2079', '9'),
        ZERO('\u2070', '0'),
        MINUS('\u207b', '-'),
        LEFT_PARENTHESIS('\u207d', '('),
        RIGHT_PARENTHESIS('\u207e', ')');

        private char unicode;
        private char normal;

        SuperScript(char unicode, char normal)
        {
            this.unicode = unicode;
            this.normal = normal;
        }

        public char getUnicode() { return unicode; }
        public int getNormal() { return normal; }
    }

    public enum SubScript
    {
        ONE('\u2081', '1'),
        TWO('\u2082', '2'),
        THREE('\u2083', '3'),
        FOUR('\u2084', '4'),
        FIVE('\u2085', '5'),
        SIX('\u2086', '6'),
        SEVEN('\u2087', '7'),
        EIGHT('\u2088', '8'),
        NINE('\u2089', '9'),
        ZERO('\u2080', '0'),
        LEFT_PARENTHESIS('\u208d', '('),
        RIGHT_PARENTHESIS('\u208e', ')');

        private char unicode;
        private char normal;

        SubScript(char unicode, char normal)
        {
            this.unicode = unicode;
            this.normal = normal;
        }

        public char getUnicode() { return unicode; }
        public int getNormal() { return normal; }
    }

    public enum Operators
    {
        SQRT('\u221a'),
        CUBE_ROOT('\u221b'),
        PI('\u03c0'),
        MULTIPLY('\u00d7'),
        DIVIDE('\u00f7');

        private char unicode;

        Operators(char unicode)
        {
            this.unicode = unicode;
        }

        public char getUnicode(){ return unicode; }
    }
}
