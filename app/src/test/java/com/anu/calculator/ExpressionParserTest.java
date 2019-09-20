package com.anu.calculator;


import com.anu.calculator.exceptions.InfinityException;
import com.anu.calculator.expressions.EExpression;
import com.anu.calculator.parsers.Parser;
import com.anu.calculator.expressions.UnknownVariableExpression;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;

public class ExpressionParserTest {

    /**
     * This class encapsulates test cases for the expression parser within the test suite. It takes
     * a string as the input expression, the expected output of the string as an equation and a
     * delta figure. The delta is an allowable difference between the input and parsed expression
     * output.
     *
     * @author: Michael Betterton (u6797866)
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
     *
     * @author: Michael Betterton (u6797866)
     * @modified: Samuel Brookes (u5380100)
     *  - 04/09/2019: Add operator tests
     *  - 06/09/2019: Add operator tests
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
        testCases.add(new TestCase("15.1-0.1", (double) 15, (double) 0));
        testCases.add(new TestCase("100×17.5%", 17.5, (double) 0));
        testCases.add(new TestCase("100×17.5%", 17.5, (double) 0));
        testCases.add(new TestCase("-1-5", (double) -6, (double) 0));

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
        testCases.add(new TestCase("180×e-π", 486.1491365, 0.000002));
        testCases.add(new TestCase("25²+5³", 750d, 0d));

        //This section tests shorthand multiplication
        testCases.add(new TestCase("2(10+2)" , 24d, 0d));
        testCases.add(new TestCase("2+15(30)" , 452d, 0d));

        // This section is for more complex test cases demonstrating BODMAS/BOMDAS function ordering
        testCases.add(new TestCase("55.888×1000.0÷80.1", 697.7278402, 0.00000002));
        testCases.add(new TestCase("45%×0.587+15nPr3", 2730.26415, 0d));
        testCases.add(new TestCase("sin(25×2+14)+sin5÷cos4", 0.9861626145, 0.0000000002));
        testCases.add(new TestCase("ln45+5×100-12.0008+log₁₀56", 493.5540505, 0.0000002));
        testCases.add(new TestCase("cos⁻¹0.2-sin⁻¹0.5+10nPr5-50", 30238.46304, 0.00002));
        testCases.add(new TestCase("√10+∛27-67%×e", 4.341028835, 0.000000002));
        testCases.add(new TestCase("4³×10²-(6!÷2)", 6040d, 0d));
        testCases.add(new TestCase("ln57-100×4^10+5!", -104857476d, 0.1d));
        testCases.add(new TestCase("2×sin30", 1d, 0.0001d));

        //testCases.add(new TestCase( , , ));

        // End of test case area, do not modify the code below.
        for (TestCase testCase : testCases) {
            try
            {
                Expression exp = new Parser().parse(testCase.input, true, 20);
                String assetString = String.format("Expression Parser Error, raw equation: %s; parsed equation: %s", testCase.input, exp.show());
                assertEquals(assetString, testCase.expected, exp.evaluate(), testCase.delta);
            }
            catch(ParserException e)
            {
                fail();
            }
        }
    }

    /**
     * Generates an overflow by asking the factorial of a large number. Then squares that number.
     * The parser should return a exception rather than crashing.
     *
     * @author: Michael Betterton (u6797866)
     * @modified: Samuel Brookes (u5380100)
     *  - 06/09/2019: added Exception expectation, corrected spelling
     */
    @Test (expected = InfinityException.class)
    public void test_infinity() throws ParserException {
        // First generate a obscenely large number
        String infinity_expression = "625!";
        Expression exp = new Parser().parse(infinity_expression);
        double infinity = exp.evaluate();

        // Concatenate that number with it to the power of 3.
        String exception_expression = infinity+"^3";
        exp = new Parser().parse(exception_expression);
        double exception = exp.evaluate();
    }

    /**
     * Tests whether the RandomNumberExpression truly generates random numbers.
     *
     * @author Samuel Brookes (u5380100)
     */
    @Test
    public void testRandomNumber()
    {
        try
        {
            /*
             * Test whether random numbers are generated
             */
            ArrayList<Expression> randomNumbers = new ArrayList<>(0);
            for(int i=0; i<1000; i++)
            {
                randomNumbers.add(new Parser().parse("rand"));
            }

            for(int i=0; i<1000; i++)
            {
                for(int j=i+1; j<1000; j++)
                {
                        assertNotEquals(randomNumbers.get(i).evaluate(),
                                randomNumbers.get(j).evaluate());

                }
            }
        }
        catch(ParserException e)
        {
            fail();
        }
    }

    /**
     * Tests whether the precision functionality is working as intended.
     *
     * @author Samuel Brookes (u5380100)
     */
    @Test
    public void testPrecision()
    {
        try
        {
            double[] values = new double[15];
            for(int i=0; i<15; i++)
            {
                Stack<Expression> history = new Stack<>();
                values[i] = new Parser().parse("π", false, i, history).evaluate();
            }

            for(int i=0; i<values.length; i++)
            {
                String value = String.valueOf(values[i]);
                assertEquals(value.substring(value.indexOf('.') + 1).length(), i, 1d); //delta of one required for rounding
            }
        }
        catch(ParserException e)
        {
            fail("ParserException: " + e.getErrorMessage());
        }
    }

    /**
     * Tests whether all unknown variables are correctly identified by the parser.
     *
     * @author Samuel Brookes
     */
    @Test
    public void testAllUnknownVariables()
    {
        String[] zorbaChars = {"ɑ", "β", "ɣ", "Δ"};
        Expression actual;
        try
        {
            //first, test every letter of the alphabet
            for(char variable = 'a'; variable <= 'z'; variable++)
            {
                actual = new Parser().parse("" + variable, false, 20);
                if(variable != 'e')
                {
                    assertEquals(UnknownVariableExpression.class, actual.getClass());
                }
                else
                {
                    assertEquals(EExpression.class, actual.getClass());
                }
            }

            //then test all the greek characters
            for(int i=0; i<zorbaChars.length; i++)
            {
                actual = new Parser().parse("" + zorbaChars[i], false, 20);
                assertEquals(UnknownVariableExpression.class, actual.getClass());
            }

        }
        catch(ParserException e)
        {
            fail();
        }
    }

    /**
     * Tests whether the parser is correctly inserting a multiplication symbol
     * when parsing expressions that use unknown variables with shorthand multiplication
     *
     * @author Samuel Brookes
     */
    @Test
    public void testShorthandMultiplicationWithUnknowns()
    {
        try
        {
            for(char variable = 'a'; variable <= 'z'; variable++) {
                //testCases
                assertEquals(new Parser().parse("2" + variable + "+15(30)", false, 20).show(),
                        "((2.0×" + variable + ")+(15.0×30.0))");
                assertEquals(new Parser().parse("14+3" + variable + "²-100", false, 20).show(),
                        "((14.0+(3.0×(" + variable + "^2.0)))-100.0)");
            }
        }
        catch(ParserException e)
        {
            fail();
        }
    }
}


