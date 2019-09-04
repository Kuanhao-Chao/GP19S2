package com.anu.calculator;

import com.anu.calculator.expressionparser.Exp;
import com.anu.calculator.expressionparser.ExpressionParser;
import com.anu.calculator.expressionparser.Tokenizer;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExpressionParserTest {

    /**
     * This class encapsulates test cases for the expression parser within the test suite. It takes
     * a string as the input expression, the expected output of the string as an equation and a
     * delta figure. The delta is an allowable difference between the input and parsed expression
     * output.
     */
    private class TestCase {

        String input;
        Double expected;
        Double delta;

        /**
         * The default constructor for a test case.
         *
         * @param input    The input expression as a string. It should conform to the known tokens of
         *                 the application. Token parsing is tested in a different test suite.
         * @param expected The expected output of the expression as a double. Use a known working
         *                 calculator to generate this figure.
         * @param delta    The allowable difference between the input after parsing and the expected
         *                 output. This is to allow some trig functions and constants to be rounded
         *                 appropriately.
         */
        TestCase(String input, Double expected, Double delta) {
            this.input = input;
            this.expected = expected;
            this.delta = delta;
        }
    }

    /**
     * Dynamically runs all test cases added to the testCases ArrayList inside the method. Each test
     * case has an allowable margin of error in the delta attribute.
     * To add test cases to the test suite, add them to the array list by appending them in the
     * commented area.
     */
    @Test
    public void runTests() {
        ArrayList<TestCase> testCases = new ArrayList<>(0);

        // Add test cases here by adding them to the test case array
        // This section contains some simple operations to confirm each operation is working as
        // expected.
        testCases.add(new TestCase("5", (double) 5, (double) 0));
        testCases.add(new TestCase("1.5", (double) 1.5, (double) 0));
        testCases.add(new TestCase("1.0+100", (double) 101, (double) 0));
        testCases.add(new TestCase("1.1×2.2", 2.42, 0.0001));
        testCases.add(new TestCase("12÷6", (double) 2, (double) 0));
        testCases.add(new TestCase("15.1−0.1", (double) 15, (double) 0));
        testCases.add(new TestCase("100×17.5%", 17.5, (double) 0));
        testCases.add(new TestCase("100×17.5%", 17.5, (double) 0));
        testCases.add(new TestCase("-1−5", (double) -6, (double) 0));

        //This section tests the operators
        testCases.add(new TestCase("sin45", 0.7071, 0.002));
        testCases.add(new TestCase("cos5", 0.9961, 0.002));
        testCases.add(new TestCase("tan12", 0.2125, 0.002));
        testCases.add(new TestCase("cos⁻¹0.7071", 45d, 0.002));
        testCases.add(new TestCase("sin⁻¹0.7071", 45d, 0.002));
        testCases.add(new TestCase("tan⁻¹0.2", 11.3099, 0.002));
        testCases.add(new TestCase("10nPr5", 30240d, 0d));
        testCases.add(new TestCase("15nCr7", 6435d, 0d));
        testCases.add(new TestCase("ln400", 5.99146, 0.0002));
        testCases.add(new TestCase("log₁₀876", 2.9425, 0.002));
        testCases.add(new TestCase("25^4", 390625d, 0d));
        testCases.add(new TestCase("10!", 3628800d, 0d));
        testCases.add(new TestCase("√26", 5.099, 0.002));
        testCases.add(new TestCase("∛50", 3.684, 0.002));
        testCases.add(new TestCase("15%", 0.15, 0d));
        testCases.add(new TestCase("180×e−π", 486.1491365, 0.000002));



        // This section is for more complex test cases demonstrating BODMAS/BOMDAS function ordering
        testCases.add(new TestCase("55.888×1000.0÷80.1", 697.7278402, 0.00000002));

        //testCases.add(new TestCase( , , ));

        // End of test case area, do not modify the code below.

        for (TestCase testCase : testCases) {
            Tokenizer tokenizer = new Tokenizer(testCase.input);
            Exp exp = new ExpressionParser(tokenizer).parseExp();
            String assetString = String.format("Expression Parser Error, raw equation: %s; parsed equation: %s", testCase.input, exp.show());
            assertEquals(assetString, exp.evaluate(), testCase.expected, testCase.delta);
        }
    }

}


