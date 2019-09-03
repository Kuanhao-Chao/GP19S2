package com.anu.calculator;

import org.junit.Test;

import com.anu.calculator.expressionparser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    // Tokenizer test class, each test case will instantiate this class.
    private class TestCase {
        private String testcase;
        private List<Token.Type> tokens;

        TestCase(String testcase, List<Token.Type> tokens) {
            this.testcase = testcase;
            this.tokens = tokens;
        }
    }

    // Instantiate a tokenizer to use in the tests.
    private static Tokenizer tokenizer;

    @Test
    public void testAddition() {
        // Declare each of the test cases
        ArrayList<TestCase> testCases = new ArrayList<>(0);
        testCases.add(new TestCase("1+1",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD, Token.Type.DOUBLE)));
        testCases.add(new TestCase("5+",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD)));
        testCases.add(new TestCase("1000+1+1+",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD, Token.Type.DOUBLE, Token.Type.ADD, Token.Type.DOUBLE, Token.Type.ADD)));

        // Run each test case programmatically by looping over the cases
        for (int i = 0; i < testCases.size(); i++) {
            tokenizer = new Tokenizer(testCases.get(i).testcase);
            for (Token.Type token : testCases.get(i).tokens) {
                assertEquals("wrong token type", token, tokenizer.current().type());
                tokenizer.next();
            }
        }
    }

    @Test
    public void testSubtraction() {
        // Declare each of the test cases
        ArrayList<TestCase> testCases = new ArrayList<>(0);
        testCases.add(new TestCase("8-1",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.SUBTRACT, Token.Type.DOUBLE)));
        testCases.add(new TestCase("5--",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.SUBTRACT, Token.Type.SUBTRACT)));
        testCases.add(new TestCase("1124-124-1",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.SUBTRACT, Token.Type.DOUBLE, Token.Type.SUBTRACT, Token.Type.DOUBLE)));

        // Run each test case programmatically by looping over the cases
        for (int i = 0; i < testCases.size(); i++) {
            tokenizer = new Tokenizer(testCases.get(i).testcase);
            for (Token.Type token : testCases.get(i).tokens) {
                assertEquals("wrong token type", token, tokenizer.current().type());
                tokenizer.next();
            }
        }
    }

    @Test
    public void testSimpleOperations()
    {
        ArrayList<TestCase> testCases = new ArrayList<>(0);
        testCases.add(new TestCase("(0.5)%",
                Arrays.asList(Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS, Token.Type.PERCENT)));
        testCases.add(new TestCase("10.859/4",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.DIVIDE, Token.Type.DOUBLE)));
        testCases.add(new TestCase("2.45ˣ5",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.MULTIPLY, Token.Type.DOUBLE)));
        testCases.add(new TestCase("ɑ+β+ɣ+Δ",
                Arrays.asList(Token.Type.UNKNOWN_VARIABLE, Token.Type.ADD, Token.Type.UNKNOWN_VARIABLE, Token.Type.ADD,
                        Token.Type.UNKNOWN_VARIABLE, Token.Type.ADD, Token.Type.UNKNOWN_VARIABLE)));
        testCases.add(new TestCase("w-x-y-z",
                Arrays.asList(Token.Type.UNKNOWN_VARIABLE, Token.Type.SUBTRACT, Token.Type.UNKNOWN_VARIABLE, Token.Type.SUBTRACT,
                        Token.Type.UNKNOWN_VARIABLE, Token.Type.SUBTRACT, Token.Type.UNKNOWN_VARIABLE)));
        testCases.add(new TestCase("sin(45)",
                Arrays.asList(Token.Type.SINE, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("cos(90)",
                Arrays.asList(Token.Type.COSINE, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("tan(18)",
                Arrays.asList(Token.Type.TANGENT, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("ln(45)",
                Arrays.asList(Token.Type.LOG_NATURAL, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("log₁₀(576)",
                Arrays.asList(Token.Type.LOG_TEN, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("(25.78)²",
                Arrays.asList(Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS, Token.Type.EXPONENT)));
        testCases.add(new TestCase("(135.5)⁽⁶⁶⁶⁾",
                Arrays.asList(Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS, Token.Type.EXPONENT)));
        testCases.add(new TestCase("(57)³",
                Arrays.asList(Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS, Token.Type.EXPONENT)));
        testCases.add(new TestCase("nPr(57,5)",
                Arrays.asList(Token.Type.PERMUTATION, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.COMMA, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("nCr(100,4)",
                Arrays.asList(Token.Type.COMBINATION, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.COMMA, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("25.12ˣπ",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.MULTIPLY, Token.Type.PI)));
        testCases.add(new TestCase("47ˣe",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.MULTIPLY, Token.Type.E)));
        testCases.add(new TestCase("24+√(45)",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD, Token.Type.SQUARE_ROOT, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("sin⁻¹(97.4)",
                Arrays.asList(Token.Type.ARC_SINE, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("cos⁻¹(24)",
                Arrays.asList(Token.Type.ARC_COSINE, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("tan⁻¹(105.3)",
                Arrays.asList(Token.Type.ARC_TANGENT, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("24.5+∛(475)",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD, Token.Type.CUBED_ROOT, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        testCases.add(new TestCase("24.5+randˣ100",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.ADD, Token.Type.RANDOM_NUMBER, Token.Type.MULTIPLY, Token.Type.DOUBLE)));
        testCases.add(new TestCase("24-!(72.45)",
                Arrays.asList(Token.Type.DOUBLE, Token.Type.SUBTRACT, Token.Type.FACTORIAL, Token.Type.LEFT_PARENTHESIS, Token.Type.DOUBLE, Token.Type.RIGHT_PARENTHESIS)));
        /*
        testCases.add(new TestCase(,
               Arrays.asList()));
        * */
        int i = 0;
        int tokenCount;
        try
        {
            for (i = 0; i < testCases.size(); i++) {
                tokenizer = new Tokenizer(testCases.get(i).testcase);
                tokenCount = 1;
                for (Token.Type token : testCases.get(i).tokens) {
                    assertEquals("Wrong type at token " + tokenCount + " in test case \"" + testCases.get(i).testcase + "\"", token, tokenizer.current().type());
                    tokenizer.next();
                    tokenCount++;
                }
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("NullPointException @ test case \"" + testCases.get(i).testcase + "\"");
            fail();
        }
    }
}
