package com.anu.calculator;

import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.functionparser.Parser;

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
        FunctionParser fp = new Parser();
        Expression exp = fp.evaluate(test, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),5.0);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.evaluate(test, history);
        assertEquals(exp.evaluate(),5.0);
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
        FunctionParser fp = new Parser();
        Expression exp = fp.evaluate(test, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),4.0);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case that updates the value of the variable
        test = "x=5+1";
        exp = fp.evaluate(test, history);
        assertEquals(exp.evaluate(),6.0);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.evaluate(test, history);
        assertEquals(exp.evaluate(),6.0);
    }

    /**
     * This test checks whether a variable can be assigned, then referenced by another expression.
     * This is the general use-case of the function system.
     */
    @Test
    public void testVariableReferencing() throws ParserException {
        // Declare the test case and an empty history stack
        String test1 = "x=5";
        String test2 = "y=2×x";
        String test3 = "z=3×y";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser, evaluate each test cash, pushing the parsed expression onto the
        // stack as we go.
        FunctionParser fp = new Parser();
        Expression exp = fp.evaluate(test1, history);
        history.push(exp);
        exp = fp.evaluate(test2, history);
        history.push(exp);
        exp = fp.evaluate(test3, history);
        assertEquals(exp.evaluate(),30.0);
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
        FunctionParser fp = new Parser();
        fp.evaluate(test1, history);
    }
}
