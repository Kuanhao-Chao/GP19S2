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

        /**
         * The default constructor for a test case.
         *
         * @param input The input function as a string.
         * @param twoExpression After the function is created, two expressions on each side of '='
         *                      will be stored in twoExpression.
         * @param validityExpected Stored the expected answer whether function is valid.
         */
        String input;
        List<String> twoExpression;
        Boolean validityExpected;

        /**
         * The default constructor for a test case.
         *
         * @param input    The input expression as a string. It should conform to the known tokens of
         *                 the application. Token parsing is tested in a different test suite.
         */
        TestCase(String input, List<String> twoExpression, Boolean expected) {
            this.input = input;
            this.twoExpression = twoExpression;
            this.validityExpected = expected;
        }
    }

    /**
     * First stage checking: Only check '=' in the function.
     * 1. There is not '=' in the first character of input.
     * 2. There is not '=' in the last character of input.
     * 3. There is only one '=' in the input.
     */
    @Test
    public void testFunctionResult() {
        // Declare each of the test cases
        ArrayList<FunctionParserTest.TestCase> testCases = new ArrayList<>(0);
        testCases.add(new FunctionParserTest.TestCase("", null,false));
        testCases.add(new FunctionParserTest.TestCase(" ", null,false));
        testCases.add(new FunctionParserTest.TestCase("   ", null,false));
        testCases.add(new FunctionParserTest.TestCase("=", null,false));
        testCases.add(new FunctionParserTest.TestCase("==", null,false));
        testCases.add(new FunctionParserTest.TestCase("a", null,false));
        testCases.add(new FunctionParserTest.TestCase("a=", null,false));
        testCases.add(new FunctionParserTest.TestCase("a==", null,false));
        testCases.add(new FunctionParserTest.TestCase("=a", null,false));
        testCases.add(new FunctionParserTest.TestCase("==a", null,false));
        testCases.add(new FunctionParserTest.TestCase("x=y=x", null,false));
        testCases.add(new FunctionParserTest.TestCase("x========yz", null,false));
        testCases.add(new FunctionParserTest.TestCase("x====y====z", null,false));
        testCases.add(new FunctionParserTest.TestCase("x=y=z=w=ɑ=β", null,false));
        testCases.add(new FunctionParserTest.TestCase("x==y", null,false));
        testCases.add(new FunctionParserTest.TestCase("=x=y", null,false));
        testCases.add(new FunctionParserTest.TestCase("x=y=", null,false));
        testCases.add(new FunctionParserTest.TestCase("=x=y=", null,false));
        //        w | x | y | z | ɑ | β | ɣ | Δ
        testCases.add(new FunctionParserTest.TestCase("2+1×2÷3×12 = 9124", Arrays.asList("2+1×2÷3×12", "9124"),true));
        testCases.add(new FunctionParserTest.TestCase("2+1×2÷3×w = 9124", Arrays.asList("2+1×2÷3×w", "9124"),true));
        testCases.add(new FunctionParserTest.TestCase("w+1×3÷3×12 = 9124", Arrays.asList("w+1×3÷3×12", "9124"),true));
        testCases.add(new FunctionParserTest.TestCase("w+x×y÷z×ɑ = 9124", Arrays.asList("w+x×y÷z×ɑ", "9124"),true));
        testCases.add(new FunctionParserTest.TestCase("x=y", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("x=y    ", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("    x=y", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("x    =y", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("x=     y", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("x=     y", Arrays.asList("x", "y"),true));
        testCases.add(new FunctionParserTest.TestCase("1×w + 2×x + 3×y + 4×z + 5×ɑ + 6×β + 7×ɣ + 8×Δ =    1÷w + 2÷x + 3÷y + 4÷z + 5÷ɑ + 6÷β + 7÷ɣ + 8÷Δ", Arrays.asList("1×w+2×x+3×y+4×z+5×ɑ+6×β+7×ɣ+8×Δ", "1÷w+2÷x+3÷y+4÷z+5÷ɑ+6÷β+7÷ɣ+8÷Δ"),true));




        // Run each test case programmatically by looping over the cases
        int counter = 0;
        for (TestCase testCase : testCases) {
            Function function = new Function(testCase.input);
            System.out.println("Finish test " + counter + " !\n");
            assertEquals("Invalid function assessment", Arrays.asList(function.getSubStrings(), function.checkValid()),
                    Arrays.asList(testCase.twoExpression, testCase.validityExpected));
            counter ++;
        }
    }
}
