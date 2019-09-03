package com.anu.calculator;

import com.anu.calculator.expressionparser.Token;
import com.anu.calculator.expressionparser.Tokenizer;
import com.anu.calculator.functionExpression.Function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FunctionParserTest {
    /**
     * This class encapsulates test cases for the function parser within the test suite. It takes
     * a string as the input expression,
     */
    private class TestCase {

        String input;
        List<String> twoExpression;
        Boolean validityExpected;

        /**
         * The default constructor for a test case.
         *
         * @param input    The input expression as a string. It should conform to the known tokens of
         *                 the application. Token parsing is tested in a different test suite.
         */
        TestCase(String input, Boolean expected) {
            this.input = input;
            this.validityExpected = expected;
        }
    }
    // Instantiate a tokenizer to use in the tests.
    @Test
    public void testFunctionValidity() {
        // Declare each of the test cases
        ArrayList<FunctionParserTest.TestCase> testCases = new ArrayList<>(0);
        testCases.add(new FunctionParserTest.TestCase("", false));
        testCases.add(new FunctionParserTest.TestCase(" ", false));
        testCases.add(new FunctionParserTest.TestCase("   ", false));
        testCases.add(new FunctionParserTest.TestCase("=", false));
        testCases.add(new FunctionParserTest.TestCase("==", false));
        testCases.add(new FunctionParserTest.TestCase("a", false));
        testCases.add(new FunctionParserTest.TestCase("a=", false));
        testCases.add(new FunctionParserTest.TestCase("a==", false));
        testCases.add(new FunctionParserTest.TestCase("=a", false));
        testCases.add(new FunctionParserTest.TestCase("==a", false));
        testCases.add(new FunctionParserTest.TestCase("a=b", true));
        testCases.add(new FunctionParserTest.TestCase("a==b", false));
        testCases.add(new FunctionParserTest.TestCase("=a=b", false));
        testCases.add(new FunctionParserTest.TestCase("a=b=", false));
        testCases.add(new FunctionParserTest.TestCase("=a=b=", false));
        testCases.add(new FunctionParserTest.TestCase("a=b    ", true));
        testCases.add(new FunctionParserTest.TestCase("    a=b", true));
        testCases.add(new FunctionParserTest.TestCase("a    =b", true));
        testCases.add(new FunctionParserTest.TestCase("a=     b", true));
        testCases.add(new FunctionParserTest.TestCase("a=b=c", false));
        testCases.add(new FunctionParserTest.TestCase("a========bc", false));
        testCases.add(new FunctionParserTest.TestCase("a====b====c", false));
        testCases.add(new FunctionParserTest.TestCase("a=b=c=d=e=f", false));
        testCases.add(new FunctionParserTest.TestCase("b=a=", false));


        // Run each test case programmatically by looping over the cases
        int counter = 0;
        for (TestCase testCase : testCases) {
            Function function = new Function(testCase.input);
            System.out.println("Finish test " + counter + " !\n");
            assertEquals("Invalid function assessment", function.checkValid(), testCase.validityExpected);
            counter ++;
        }
    }
}
