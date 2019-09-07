package com.anu.calculator.functionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.expressionparser.Tokenizer;
import com.anu.calculator.expressionparser.UnknownVariableExpression;

import java.util.Stack;

/**
 * EqualityExpression: This class is used to represent equality and variable/function assignment
 *
 * @author: Michael Betterton (u6797866)
 * @modified: Samuel Brookes (u5380100)
 * @modified: Howard Chao (u7022787)
 *  - 07/09/2019: changed exception to UnassignedVariableException
 *
 */

public class Parser implements com.anu.calculator.FunctionParser {
    @Override
    public Expression evaluate(String input, Stack<Expression> history) throws UnassignedVariableException {
        /**
         * If length is 1. It is a unknown variable. Iterate through the history stack to find the value.
         */
        if (input.length() == 1 ) {
            for (int i = 0; i < history.size(); i++) {
                String outputString = history.elementAt(i).show();
                if (outputString.contains(input.charAt(0) + "=")) {
                    // The unknown variable is in the stack!
                    UnknownVariableExpression unknownVariableExpression = new UnknownVariableExpression(input.charAt(0));
                    unknownVariableExpression.assignValue(history.elementAt(i));
                    return unknownVariableExpression;
                }
            }
        } else if (input.contains("=")) {
            /**
             * Return an EqualityExpression. (Need to update the stack value)
             */
            Character leftUnknown = input.charAt(0);
            input = input.substring(2);
            UnknownVariableExpression unknownVariableExpression = new UnknownVariableExpression(leftUnknown);

            Expression exp = new com.anu.calculator.expressionparser.Parser().parse(input, history);
            try {
                System.out.println("exp.show(): " + exp.show() + " exp.evaluate(): " + exp.evaluate());
            } catch (ParserException e) {
                e.printStackTrace();
            }
            unknownVariableExpression.assignValue(exp);
            try {
                System.out.println("Unknown variable info 2: " + unknownVariableExpression.show() + " " + unknownVariableExpression.hasValue() + " unknownVariableExpression.evaluate(): " + unknownVariableExpression.evaluate());
            } catch (ParserException e) {
                e.printStackTrace();
            }

            EqualityExpression equalityExpression = new EqualityExpression(unknownVariableExpression, exp);

            /**
             * Check the history stack and update stack!
             */
            for (int i = 0; i < history.size(); i++) {
                String outputString = history.elementAt(i).show();
                if (outputString.contains(leftUnknown + "=")) {
//                    System.out.println("Inside" + leftUnknown + "=");
//                    // The unknown variable is in the stack!
//                    UnknownVariableExpression unknownVariableExpressionUpdate = new UnknownVariableExpression(leftUnknown);
//                    unknownVariableExpressionUpdate.assignValue(history.elementAt(i));
                    history.set(i, unknownVariableExpression);
                }
            }
            return equalityExpression;
        } else {
            /**
             * Normal expression. Return a expression.
             */
            Expression exp = new com.anu.calculator.expressionparser.Parser().parse(input, history);
            try {
                System.out.println("exp.show(): " + exp.show() + " exp.evaluate(): " + exp.evaluate());
            } catch (ParserException e) {
                e.printStackTrace();
            }
            return exp;
        }
        return null;
    }
}
