package com.anu.calculator;

import com.anu.calculator.exceptions.UnassignedVariableException;
import com.anu.calculator.parsers.ExpressionParser;

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
        ExpressionParser fp = new ExpressionParser();
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
        ExpressionParser fp = new ExpressionParser();
        Expression exp = fp.parse(test, true, 0, history);

        // Assert the first return as the literal evaluation of the input
        assertEquals(exp.evaluate(),4d);

        // Push the expression we just parsed onto a stack to use as history
        history.push(exp);

        // Create a new test case that updates the value of the variable
        test = "x=5+1";
        exp = fp.parse(test,true, 0,  history);
        assertEquals(exp.evaluate(),6d);
        history.push(exp);

        test = "x=5+3";
        exp = fp.parse(test, true, 0,  history);
        assertEquals(exp.evaluate(),8.0);
        history.push(exp);

        test = "x=5+10";
        exp = fp.parse(test,true, 0,   history);
        assertEquals(exp.evaluate(),15.0);
        history.push(exp);

        // Create a new test case to use as the recall.
        test = "x";
        exp = fp.parse(test,true, 0,  history);
        assertEquals(exp.evaluate(),15d);
        history.push(exp);

        // Test the recall without an equality expression
        test = "5x";
        exp = fp.parse(test, true, 0, history);
        assertEquals(exp.evaluate(),75d);
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
        ExpressionParser fp = new ExpressionParser();
        Expression exp = fp.parse(test1,true, 0,  history);
        history.push(exp);

        exp = fp.parse(test2, true, 0,  history);
        history.push(exp);

        exp = fp.parse(test3, true, 0,  history);
        assertEquals(exp.evaluate(),30.0);
    }

    /**
     * Tests retroactive assignment
     * @author: Samuel Brookes (u5380100)
     */
    @Test
    public void retroactiveAssignment() throws ParserException
    {
        ExpressionParser parser = new ExpressionParser();
        Stack<Expression> history = new Stack<>();
        String testCase1 = "x=2", testCase2 = "y=2x", testCase3 = "x=5", testCase4 = "y";
        Expression exp;

        exp = parser.parse(testCase1, true, 0, history);
        assertEquals(2d, exp.evaluate());
        history.push(exp);

        exp = parser.parse(testCase2, true, 0, history);
        assertEquals(4d, exp.evaluate());
        history.push(exp);

        exp = parser.parse(testCase3, true, 0, history);
        assertEquals(5d, exp.evaluate());
        history.push(exp);

        //This tests whether a retroactive assignment evaluates correctly
        exp = parser.parse(testCase4, true, 0, history);
        assertEquals(10d, exp.evaluate());
        history.push(exp);
    }


    /**
     * This test checks whether a variable can be assigned, then referenced by another expression
     * alongside another variable. This is the more complex use case of the parser.
     *
     * @author: Howard Chao
     * @modified: Samuel Brookes (u5380100)
     *  - 19/09/2019: cleaned up after merge
     */
    @Test
    public void testComplexReferencing() throws ParserException {
        ExpressionParser fp = new ExpressionParser();
        Stack<Expression> history = new Stack<>();
        Expression exp;

        // Declare the test case and an empty history stack
        String test1 = "x=5"; //5
        String test2 = "y=2x"; //10
        String test3 = "z=3y+y"; //40
        String test4 = "w=z+y-x"; //40+10-5= 45
        String test5 = "z=2y-x"; // 20-5=15. w is now 15+10-5=20
        String test6 = "y=x+3"; // y = 5 + 3 = 8
        String test7 = "z=2y-x"; //11
        String test8 = "w=z+y-x"; //21

        // Instantiate a parser, evaluate each test case, pushing the parsed expression onto the
        // stack as we go.
        exp = fp.parse(test1, true, 0,  history);
        assertEquals(exp.evaluate(),5d);
        history.push(exp);

        exp = fp.parse(test2, true, 0,  history);
        assertEquals(exp.evaluate(),10d);
        history.push(exp);

        exp = fp.parse(test3, true, 0,  history);
        assertEquals(exp.evaluate(),40d);
        history.push(exp);

        exp = fp.parse(test4, true, 0,  history);
        assertEquals(exp.evaluate(),45d);
        history.push(exp);

        exp = fp.parse(test5, true, 0,  history);
        assertEquals(exp.evaluate(),15d);
        history.push(exp);

        exp = fp.parse(test6, true, 0,  history);
        assertEquals(exp.evaluate(),8d);
        history.push(exp);

        exp = fp.parse(test7, true, 0,  history);
        assertEquals(exp.evaluate(),11d);
        history.push(exp);

        exp = fp.parse(test8, true, 0,  history);
        assertEquals(exp.evaluate(),18d);
        history.push(exp);
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
        ExpressionParser fp = new ExpressionParser();
        Expression exp = fp.parse(test1, true, 0, history);
        exp.evaluate(); //evaluate throws exception, not parse
    }
}
