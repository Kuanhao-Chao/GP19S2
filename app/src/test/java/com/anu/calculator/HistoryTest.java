package com.anu.calculator;

import com.anu.calculator.parsers.ExpressionParser;
import com.anu.calculator.utilities.History;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HistoryTest {

    /**
     * This test checks whether a variable can be assigned, then referenced by another expression.
     * This is the general use-case of the function system.
     */
    @Test
    public void testSimpleHistory() throws ParserException {
        // Declare the test case and an empty history stack
        String test1 = "x=5";
        String test2 = "y=2x";
        String test3 = "z=3y";

        // Instantiate a parser, evaluate each test cash, pushing the parsed expression onto the
        // stack as we go.
        History history1 = History.getInstance(false);
        ExpressionParser fp = new ExpressionParser();
        Expression exp = fp.parse(test1,true, 0,  history1);
        history1.put(exp);
        history1.save();

        // Load the history back in, run a expression through the parser and save it
        History history_2 = History.load(false);
        exp = fp.parse(test2, true, 0,  history_2);
        history_2.put(exp);
        history_2.save();

        // Do the same thing for a third time.
        History history_3  = History.load(false);
        exp = fp.parse(test3, true, 0,  history_3);
        assertEquals(exp.evaluate(),30.0);
    }
}
