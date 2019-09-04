package com.anu.calculator.functionExpression;

import com.anu.calculator.expressionparser.Exp;
import com.anu.calculator.expressionparser.ExpressionParser;
import com.anu.calculator.expressionparser.Token;
import com.anu.calculator.expressionparser.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleToLongFunction;

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
    private List<Exp> twoExp = new ArrayList<>(0);
    private List<HashMap<Character, Tuple<Boolean, Double>>> twoExpParameters= new ArrayList<>(0);
    private boolean isValid = true;

    public Function (String input) {
        /**
         * Initialization for Parameters in two expression.
         */
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                HashMap<Character, Tuple<Boolean, Double>> oneExpParameters = new HashMap<Character, Tuple<Boolean, Double>>();
                oneExpParameters.put('w', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('x', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('y', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('z', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('ɑ', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('β', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('ɣ', new Tuple<Boolean, Double>(false, null));
                oneExpParameters.put('Δ', new Tuple<Boolean, Double>(false, null));
                twoExpParameters.add(oneExpParameters);
            }
        }

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
                Tokenizer tokenizer_2 = new Tokenizer(this.subStrings.get(1));
                Exp exp_1 = new ExpressionParser(tokenizer_1).parseExp();
                Exp exp_2 = new ExpressionParser(tokenizer_2).parseExp();

                twoExp.add(exp_1);
                twoExp.add(exp_2);
                System.out.println("exp_1: " + this.twoExp.get(0).show());
                System.out.println("exp_2: " + this.twoExp.get(1).show());
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < this.twoExp.get(i).show().length(); j++) {
                        if (this.twoExp.get(i).show().charAt(j) == 'w') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('w', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'x') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('x', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'y') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('y', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'z') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('z', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'ɑ') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('ɑ', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'β') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('β', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'ɣ') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('ɣ', new_parameter);
                        }
                        if (this.twoExp.get(i).show().charAt(j) == 'Δ') {
                            Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                            twoExpParameters.get(i).put('Δ', new_parameter);
                        }
                    }
                }

                System.out.println(getVariable());
            }
        }
    }


    public boolean checkValid () {
        return isValid;
    }

    public List<String> getSubString() { return subStrings;}

    public List<String> getVariable() {
        for (int i =0; i < 2; i++) {
            for (Character j : twoExpParameters.get(i).keySet()) {
                System.out.println("getVariable: " + j + " value: " + twoExpParameters.get(i).get(j).x + " " + twoExpParameters.get(i).get(j).y);
            }
        }
        return null;
    }


    public class Tuple<X, Y> {
        public final X x;
        public final Y y;
        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
