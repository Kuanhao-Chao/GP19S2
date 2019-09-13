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
        Expression exp = fp.parse(test, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),5d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.parse(test, history);
        assertEquals(exp.evaluate(),5d);
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
        Expression exp = fp.parse(test, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),4d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case that updates the value of the variable
        test = "x=5+1";
        exp = fp.parse(test, history);
        assertEquals(exp.evaluate(),6.0);

        test = "x=5+3";
        exp = fp.parse(test, history);
        assertEquals(exp.evaluate(),8.0);

        test = "x=5+10";
        exp = fp.parse(test, history);
        assertEquals(exp.evaluate(),15.0);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.parse(test, history);
        assertEquals(exp.evaluate(),15.0);
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
        Expression exp = fp.parse(test1, history);
        history.push(exp);
        exp = fp.parse(test2, history);
        history.push(exp);
        exp = fp.parse(test3, history);
        assertEquals(exp.evaluate(),30.0);
    }


    /**
     * This test checks whether a variable can be assigned, then referenced by another expression
     * alongside another variable. This is the more complex use case of the parser.
     */
    @Test
    public void testComplexReferencing() throws ParserException {
        // Declare the test case and an empty history stack
        String test1 = "x=5"; //5
        String test2 = "y=2x"; //10
        String test3 = "z=3y+y"; //40
        String test4 = "w=z+y-x"; //40+10-5= 45
        String test5 = "z=2y-x"; // 20-5=15. w is now 15+10-5=20
        String test6 = "y=x+3"; // y = 5 + 3 = 8. z = 2*y-x = 2*8-5 = 11, w = z+y-x = 11+8-5=14
        String test7 = "y";
        String test8 = "z";
        String test9 = "w";
        Stack<Expression> history = new Stack<>();

        // Instantiate a parser, evaluate each test cash, pushing the parsed expression onto the
        // stack as we go.
        ExpressionParser fp = new Parser();
        Expression exp = fp.parse(test1, history);
        history.push(exp);
        exp = fp.parse(test2, history);
        history.push(exp);
        exp = fp.parse(test3, history);
        history.push(exp);
        exp = fp.parse(test4, history);
        history.push(exp);
        assertEquals(exp.evaluate(),45d);
        exp = fp.parse(test5, history);
        history.push(exp);
        assertEquals(exp.evaluate(),15d);
        exp = fp.parse(test6, history);
        history.push(exp);
        assertEquals(exp.evaluate(),8d);
        exp = fp.parse(test7, history);
        history.push(exp);
        assertEquals(exp.evaluate(),8d);
        exp = fp.parse(test8, history);
        history.push(exp);
        assertEquals(exp.evaluate(),11d);
        exp = fp.parse(test9, history);
        history.push(exp);
        assertEquals(exp.evaluate(),14d);
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
        fp.parse(test1, history);
    }
}
