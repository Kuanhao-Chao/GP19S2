package com.anu.calculator;

import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.expressionparser.Parser;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertEquals;

public class FunctionParserTest {

    /**
     * Simple test case that evaluates a single variable assignment to a expression.
     */
    @Test
    public void testSimpleAssignment() throws ParserException {
        // Declare the test case and an empty history stack
        String test = "x=4+1";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser
        ExpressionParser fp = new Parser();
        Expression exp = fp.parse(test, true, 0, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),5d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.parse(test, true, 0, history);
        assertEquals(exp.evaluate(),5d);
        history.push(exp);

        // Test the recall without an equality expression
        test = "2x";
        exp = fp.parse(test, true, 0, history);
        assertEquals(exp.evaluate(),10d);
    }

    /**
     * Tests the fact a variable can be assigned to a new value. The parser should return the latest
     * value to the user.
     */
    @Test
    public void testVariableUpdating() throws ParserException {
        // Declare the test case and an empty history stack
        String test = "x=5-1";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser
        ExpressionParser fp = new Parser();
        Expression exp = fp.parse(test, true, 0, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),4d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case that updates the value of the variable
        test = "x=5+1";
        exp = fp.parse(test,true, 0,  history);
        assertEquals(exp.evaluate(),6d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.parse(test,true, 0,  history);
        assertEquals(exp.evaluate(),6d);

        // Test the recall without an equality expression
        test = "5x";
        exp = fp.parse(test, true, 0, history);
        assertEquals(exp.evaluate(),30d);
    }

    /**
     * This test checks whether a variable can be assigned, then referenced by another expression.
     * This is the general use-case of the function system.
     */
    @Test
    public void testVariableReferencing() throws ParserException {
        // Declare the test case and an empty history stack
        String test1 = "x=5";
        String test2 = "y=2x";
        String test3 = "z=3y";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser, evaluate each test cash, pushing the parsed expression onto the
        // stack as we go.
        ExpressionParser fp = new Parser();
        Expression exp = fp.parse(test1,true, 0,  history);
        history.push(exp);
        exp = fp.parse(test2,true, 0,  history);
        history.push(exp);
        exp = fp.parse(test3,true, 0,  history);
        assertEquals(exp.evaluate(),30d);
    }

    /**
     * Confirms that when a unknown variable is referenced, a exception is returned.
     */
    @Test(expected = UnassignedVariableException.class)
    public void testExceptionSimple() throws ParserException {
        // Declare the test case and an empty history stack
        String test1 = "x=y";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser, and try and generate an exception.
        ExpressionParser fp = new Parser();
        Expression exp = fp.parse(test1, true, 0, history);
        exp.evaluate(); //evaluate throws exception, not parse
    }
}
