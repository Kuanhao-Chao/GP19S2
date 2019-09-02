package com.anu.calculator;

import com.anu.calculator.expressionparser.Exp;
import com.anu.calculator.expressionparser.ExpressionParser;
import com.anu.calculator.expressionparser.Tokenizer;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionParserTest {
    /**
     *  JUint Test for *Exp && Parser  -- 2019.8
     */

    private static Tokenizer tokenizer;
    
    private static final String SIMPLECASE = "1+2";
    private static final String MIDCASE = "12 * 5 - 3";
    private static final String COMPLEXCASE = "(10 - 2) * (10 / 2) + 1";

    private static final String[] testExample = new String[]{"2+1", "2-1", "2*1", "2/1"};
    
    
    @Test
    public void testSimpleAdd() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[0]);
        Exp t1 = new ExpressionParser(mathTokenizer).parseExp();
        assertEquals(3, t1.evaluate());            
    }
    
    @Test
    public void testSimleSub() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[1]);
        Exp t1 = new ExpressionParser(mathTokenizer).parseExp();
        assertEquals(1, t1.evaluate());            
    }
    
    @Test
    public void testSimleMul() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[2]);
        Exp t1 = new ExpressionParser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }
    
    @Test
    public void testSimleDiv() {
        Tokenizer mathTokenizer = new Tokenizer(testExample[3]);
        Exp t1 = new ExpressionParser(mathTokenizer).parseExp();
        assertEquals(2, t1.evaluate());            
    }

    @Test
    public void testSimpleCase(){
        tokenizer = new Tokenizer(SIMPLECASE);
        try{
            Exp exp = new ExpressionParser(tokenizer).parseExp();
            assertEquals("incorrect display format", "(1 + 2)", exp.show());
            assertEquals("incorrect evaluate value", 3, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testMidCase(){
        tokenizer = new Tokenizer(MIDCASE);
        try{
            Exp exp = new ExpressionParser(tokenizer).parseExp();
            assertEquals("incorrect display format", "((12 * 5) - 3)", exp.show());
            assertEquals("incorrect evaluate value", 57, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testComplexCase(){
        tokenizer = new Tokenizer(COMPLEXCASE);
        try{
            Exp exp = new ExpressionParser(tokenizer).parseExp();
            assertEquals("incorrect display format","(((10 - 2) * (10 / 2)) + 1)", exp.show());
            assertEquals("incorrect evaluate value", 41, exp.evaluate());
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

}


