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

        public TestCase(String testcase, List<Token.Type> tokens) {
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
                Arrays.asList(Token.Type.INT, Token.Type.ADD, Token.Type.INT)));
        testCases.add(new TestCase("5+",
                Arrays.asList(Token.Type.INT, Token.Type.ADD)));
        testCases.add(new TestCase("1000+1+1+",
                Arrays.asList(Token.Type.INT, Token.Type.ADD, Token.Type.INT, Token.Type.ADD, Token.Type.INT, Token.Type.ADD)));

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
                Arrays.asList(Token.Type.INT, Token.Type.SUB, Token.Type.INT)));
        testCases.add(new TestCase("5--",
                Arrays.asList(Token.Type.INT, Token.Type.SUB, Token.Type.SUB)));
        testCases.add(new TestCase("1124-124-1",
                Arrays.asList(Token.Type.INT, Token.Type.SUB, Token.Type.INT, Token.Type.SUB, Token.Type.INT)));

        // Run each test case programmatically by looping over the cases
        for (int i = 0; i < testCases.size(); i++) {
            tokenizer = new Tokenizer(testCases.get(i).testcase);
            for (Token.Type token : testCases.get(i).tokens) {
                assertEquals("wrong token type", token, tokenizer.current().type());
                tokenizer.next();
            }
        }
    }
}
