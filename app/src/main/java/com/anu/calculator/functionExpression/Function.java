package com.anu.calculator.functionExpression;

import com.anu.calculator.expressionparser.Exp;
import com.anu.calculator.expressionparser.ExpressionParser;
import com.anu.calculator.expressionparser.Token;
import com.anu.calculator.expressionparser.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Function class which stores function that user inputs.
 *
 * @author  Howard Chao
 * @version 1.0
 * @since   2019-09-04
 */
public class Function {
    private String input = null;
    private List<String> subStrings = null;
    private boolean isValid = true;

    public Function (String input) {
        /**
         * First step: Remove spaces in the input string
         */
        input = input.replaceAll(" ", "");
        if (input.equals("")) {
            this.isValid = false;
            this.input = input;
            System.out.println("Equal ''");
            System.out.println("Input: " + this.input);
            System.out.println("isValid: " + this.isValid);
        } else {
            this.input = input;
            /**
             * Second step: Split the function into sub_expression
             */
            /**
             * Error checking: splited subStrings
             * 1. '=' cannot be the first element of input
             *      method: check directly
             * 2. '=' cannot be the second element of input
             *      method: check directly
             * 3. function must have at least one '='
             *      method: check '=' exist in the input
             * 4. '=' cannot be next to '='
             *      method: after split, make sure that there is no "" in the list
             * 5. function must has only one '='
             *      method: After split, there are exactly two substring(Expression)
             */

            /**
             * Checking for 1.
             */
            if (input.charAt(0) == '=') {
                System.out.println("First character of input should not be '='");
                this.isValid = false;
            }

            /**
             * Checking for 2.
             */
            if (input.charAt(input.length()-1) == '=') {
                System.out.println("Last character of input should not be '='");
                this.isValid = false;
            }

            /**
             * Checking for 3.
             */
            if (!input.contains("=")) {
                System.out.println("There must be a '=' inside the input string!");
                this.isValid = false;
            }

            /**
             * Checking for 4.
             * Check there is not adjacent '='
             */
            List<String> subStrings = Arrays.asList(input.split("="));
            for (int i = 0; i < subStrings.size(); i++) {
                System.out.println(i + ": " + subStrings.get(i));
                if (subStrings.get(i).isEmpty()) {
                    System.out.println("'=' should not next to each other!");
                    this.isValid = false;
                }
            }
            /**
             * Checking for 5.
             * Check there is not adjacent '='
             */
            if (subStrings.size() != 2) {
                this.isValid = false;
                System.out.println("There must be only two variables");
            }
            this.subStrings = subStrings;
            if ( this.isValid == false) {
                this.subStrings = null;
            }


            if (this.subStrings != null) {
                Tokenizer tokenizer_1 = new Tokenizer(this.subStrings.get(0));
//                s
                Exp exp_1 = new ExpressionParser(tokenizer_1).parseExp();
//
//                Tokenizer tokenizer_2 = new Tokenizer(this.subStrings.get(1));
//                Exp exp_2 = new ExpressionParser(tokenizer_2).parseExp();
                System.out.println("Input: " +  this.subStrings.get(0));
                System.out.println("exp_1" + exp_1.show());
//                System.out.println("exp_2" + exp_2.show());
            }
        }
    }


    public boolean checkValid () {
        return isValid;
    }

    public List<String> getSubString() { return subStrings;}
}
