package com.anu.calculator.functionExpression;

import com.anu.calculator.Expression;
import com.anu.calculator.expressionparser.Parser;
import com.anu.calculator.expressionparser.Tokenizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The Function class which stores function that user inputs.
 *
 * @author  Howard Chao
 * @version 2.0
 * @since   2019-09-04
 *
 */
public class Function {
    /**
     * The default constructor for a test case.
     *
     * @param scopeParameters  All possible parameters.
     * @param input  User input after space trimmed.
     * @param subStrings  The function is separated as left-hand side string & right-hand side
     *                    string. There are only two elements in subStrings. The first one is
     *                    left-hand side string; the second one is right-hand side string. When
     *                    users update the function instance, subStrings value will not be changed.
     * @param assignedSubStrings The function is separated as left-hand side string & right-hand
     *                           side string. There are only two elements in subStrings. The first
     *                           one is left-hand side string; the second one is right-hand side
     *                           string. When users update the function instance, subStrings value
     *                           will be changed according to the assigned parameters
     * @param twoExpression   Run Parser with subStrings as input. There are only two elements
     *                 in twoExpression. The first one stored the result of first element of subStrings.
     *                 And the second one stored the result of second element of subStrings.
     * @param assignedTwoExpression    Run Parser with assignedSubStrings as input. Same process
     *                          as twoExpression.
     * @param twoExpParameters  Store all scopeParameters parameters result. Each element is stored
     *                          as Hashmap. The key is the parameters; the value is a tuple which
     *                          stores 'whether parameter is defined' and 'the value of parameter'.
     *                          If the parameter is in the function and has a value, then result
     *                          would be (true, double); if the parameter is in the function and
     *                          does not have a value, then result would be (true, null); if
     *                          parameter is not in the function, the result would be (false, null).
     * @param isValid  Whether the function is valid.
     *
     */
    //        w | x | y | z | a | β | ɣ | Δ
    private List<Character> scopeParameters = Arrays.asList('w', 'x', 'y', 'z', 'ɑ', 'β', 'ɣ', 'Δ');
    private String input = null;
    private List<String> subStrings = null;
    private List<String> assignedSubStrings = null;
    private List<Expression> twoExpression = new ArrayList<>(0);
    private List<Expression> assignedTwoExpression = new ArrayList<>(0);
    private List<HashMap<Character, Tuple<Boolean, Double>>> twoExpParameters= new ArrayList<>(0);
    private boolean isValid = true;

    public Function (String input) {
        /**
         * Initialization for Parameters in two expression.
         */
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                HashMap<Character, Tuple<Boolean, Double>> oneExpParameters = new HashMap<Character, Tuple<Boolean, Double>>();
                for (Character parameter : scopeParameters) {
                    oneExpParameters.put(parameter, new Tuple<Boolean, Double>(false, null));
                }
                twoExpParameters.add(oneExpParameters);
            }
        }

        /**
         * First step: Remove spaces in the input string
         */
        input = input.replaceAll(" ", "");
        this.input = input;
        if (input.equals("")) {
            this.isValid = false;
            System.out.println("Equal ''");
            System.out.println("Input: " + this.input);
            System.out.println("isValid: " + this.isValid);
        } else {
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
//                System.out.println(i + ": " + subStrings.get(i));
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
                /**
                 * Copy to assignedSubStrings
                 */
                List<String> assignedSubStrings = new ArrayList<>(0);
                for (int i = 0; i < 2; i++) {
                    assignedSubStrings.add(this.subStrings.get(i));
                }
                this.assignedSubStrings = assignedSubStrings;

                /**
                 * Do parsing for both subString and assignedSubStrings
                 */
                for (int i = 0; i < 2; i++) {
                    Expression exp = new Parser().parse(this.subStrings.get(i));
                    Expression assignedExp = new Parser().parse(this.assignedSubStrings.get(i));
                    twoExpression.add(exp);
                    assignedTwoExpression.add(assignedExp);
                }
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < this.twoExpression.get(i).show().length(); j++) {
                        for (Character parameter : scopeParameters) {
                            if (this.twoExpression.get(i).show().charAt(j) == parameter) {
                                Tuple<Boolean, Double> new_parameter = new Tuple<Boolean, Double>(true, null);
                                twoExpParameters.get(i).put(parameter, new_parameter);
                            }
                        }
                    }
                }

                functionSummary();


//                HashMap<Character,Double> userAssigned = new HashMap<>();
//                userAssigned.put('w', 10.0);
//                userAssigned.put('x', 20.0);
//                userAssigned.put('y', 30.0);
//                userAssigned.put('z', 1.0);
//
////            userAssigned.put('w', 10.0);
//                assignParameters(userAssigned);
//
//
//                functionSummary();
            }
        }
    }


    /****************************************************************
     * ********************* Public Get Methods *********************
     * **************************************************************
     */
    public String getInput() {
        return input;
    }

    public List<String> getSubStrings() {
        return subStrings;
    }

    public List<String> getAssignedSubStrings() {
        return assignedSubStrings;
    }

    public List<Expression> getTwoExpression() {
        return twoExpression;
    }

    public List<Expression> getAssignedTwoExpression() {
        return assignedTwoExpression;
    }

    public List<HashMap<Character, Tuple<Boolean, Double>>> getTwoExpParameters() {
        return twoExpParameters;
    }

    public boolean checkValid () {
        return isValid;
    }


    /************************************************************
     * ********************* Public Methods *********************
     * **********************************************************
     */
    public List<Character> parametersInFunction() {
        List<Character> parameters = new ArrayList<>(0);
        for (int i =0; i < 2; i++) {
            for (Character parameter : scopeParameters) {
                if (twoExpParameters.get(i).get(parameter).isParameter == true) {
                    parameters.add(parameter);
                }
            }
        }
        return parameters;
    }

    public void assignParameters(HashMap<Character,Double> assignValue) {
        for (Character i : assignValue.keySet()) {
            for (Character parameter : scopeParameters) {
                if (i == parameter) {
                    for (int j = 0; j < 2; j++) {
                        twoExpParameters.get(j).get(parameter).isParameter = true;
                        twoExpParameters.get(j).get(parameter).value = assignValue.get(parameter);
                    }
                }
            }
        }
        updateAssignedSubStrings(assignValue);
        updateAssignedTwoExp();
    }

    public void functionSummary() {
        for (int i =0; i < 2; i++) {
            if (i == 0) {
                System.out.println("Left-hand side: ");
            } else if (i == 1) {
                System.out.println("Right-hand side: ");
            }
            System.out.println("    Origin input: " + subStrings.get(i));
            System.out.println("  Assigned input: " + assignedSubStrings.get(i));
            System.out.println("    Parsed Origin input: " + twoExpression.get(i).show());
            System.out.println("  Parsed Assigned input: " +  assignedTwoExpression.get(i).show());
            for (Character j : twoExpParameters.get(i).keySet()) {
                System.out.println("    Variable: " + j + "; isParameter: " + twoExpParameters.get(i).get(j).isParameter + "; Value: " + twoExpParameters.get(i).get(j).value);
            }
        }
        System.out.println("Variables in function: " + parametersInFunction());
    }


    /*************************************************************
     * ********************* Private Methods *********************
     * ***********************************************************
     */
    /**
     *
     * @param assignValue
     */
    private void updateAssignedSubStrings(HashMap<Character,Double> assignValue) {
        for (int i = 0; i < 2; i++) {
            String oneExpString = this.subStrings.get(i);
            for (int j = 0; j < oneExpString.length(); j++) {
                for (Character parameter : scopeParameters) {
                    if (oneExpString.charAt(j) == parameter) {
                        if (assignValue.containsKey(parameter)) {
                            String rep = Double.toString(assignValue.get(parameter));
                            oneExpString = oneExpString.substring(0, j) + rep + oneExpString.substring(j + 1);
                            assignedSubStrings.set(i, oneExpString);
                        }
                    }
                }
            }
        }
    }

    private void updateAssignedTwoExp() {
        for (int i = 0; i < 2; i++) {
            Expression assignedExpression = new Parser().parse(this.assignedSubStrings.get(i));
            assignedTwoExpression.set(i, assignedExpression);
        }
    }

    /*********************************************************
     * ********************* Tuple Class *********************
     * *******************************************************
     */
    public class Tuple<X, Y> {
        public X isParameter;
        public Y value;
        public Tuple(X x, Y y) {
            this.isParameter = x;
            this.value = y;
        }
    }
}
//        w | x | y | z | ɑ | β | ɣ | Δ

