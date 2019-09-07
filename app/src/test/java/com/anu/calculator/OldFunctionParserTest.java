package com.anu.calculator;

import com.anu.calculator.expressionparser.Token;
import com.anu.calculator.expressionparser.Tokenizer;
import com.anu.calculator.functionExpression.Function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OldFunctionParserTest {
    /**
     * This class encapsulates test cases for the function parser within the test suite. It takes
     * a string as the input expression,
     *
     * @author: Howard Chao (u7022787)
     */
    private class TestCase {

        String input;
        List<String> subStrings;
        List<String> assignedSubStrings;
        List<Character> parameters;
        Boolean validityExpected;

        /**
         * The default constructor for a test case.
         *expected
         * @param input The input function as a string.
         * @param subStrings After the function is created, two expressions on each side of '='
         *                      will be stored in twoExpression.
         * @param assignedSubStrings 
         * @param parameters
         * @param expected Stored the expected answer whether function is valid.
         */
        TestCase(String input, List<String> subStrings, List<String> assignedSubStrings,
                 List<Character> parameters, Boolean expected) {
            this.input = input;
            this.subStrings = subStrings;
            this.assignedSubStrings = assignedSubStrings;
            this.parameters = parameters;
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
        List<Character> parameters = new ArrayList<>(0);
        ArrayList<TestCase> testCases = new ArrayList<>(0);
        testCases.add(new TestCase("", null, null, parameters,false));
        testCases.add(new TestCase(" ", null,null, parameters,false));
        testCases.add(new TestCase("   ", null,null, parameters,false));
        testCases.add(new TestCase("=", null,null, parameters,false));
        testCases.add(new TestCase("==", null,null, parameters,false));
        testCases.add(new TestCase("a", null,null, parameters,false));
        testCases.add(new TestCase("a=", null,null, parameters,false));
        testCases.add(new TestCase("a==", null,null, parameters,false));
        testCases.add(new TestCase("=a", null,null, parameters,false));
        testCases.add(new TestCase("==a", null,null, parameters,false));
        testCases.add(new TestCase("x=y=x", null,null, parameters,false));
        testCases.add(new TestCase("x========yz", null,null, parameters,false));
        testCases.add(new TestCase("x====y====z", null,null, parameters,false));
        testCases.add(new TestCase("x=y=z=w=ɑ=β", null,null, parameters,false));
        testCases.add(new TestCase("x==y", null,null, parameters,false));
        testCases.add(new TestCase("=x=y", null,null, parameters,false));
        testCases.add(new TestCase("x=y=", null,null, parameters,false));
        testCases.add(new TestCase("=x=y=", null,null, parameters,false));
        //        w | x | y | z | ɑ | β | ɣ | Δ
        testCases.add(new TestCase("2+1×2÷3×12 = 9124", Arrays.asList("2+1×2÷3×12", "9124"), Arrays.asList("2+1×2÷3×12", "9124"), parameters,true));
        testCases.add(new TestCase("2+1×2÷3×w = 9124", Arrays.asList("2+1×2÷3×w", "9124"), Arrays.asList("2+1×2÷3×10.0", "9124"), Arrays.asList('w'), true));
        testCases.add(new TestCase("w+1×3÷3×12 = 9124", Arrays.asList("w+1×3÷3×12", "9124"),Arrays.asList("10.0+1×3÷3×12", "9124"),Arrays.asList('w'),true));
        testCases.add(new TestCase("w+x×y÷z×ɑ = 9124", Arrays.asList("w+x×y÷z×ɑ", "9124"),Arrays.asList("10.0+20.0×30.0÷40.0×50.0", "9124"),Arrays.asList('w', 'x', 'y', 'z', 'ɑ'), true));
        testCases.add(new TestCase("x=y", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("x=y    ", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("    x=y", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("x    =y", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("x=     y", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("x=     y", Arrays.asList("x", "y"),Arrays.asList("20.0", "30.0"),Arrays.asList('x', 'y'), true));
        testCases.add(new TestCase("1×w + 2×x + 3×y + 4×z + 5×ɑ + 6×β + 7×ɣ + 8×Δ =    1÷w + 2÷x + 3÷y + 4÷z + 5÷ɑ + 6÷β + 7÷ɣ + 8÷Δ",
                Arrays.asList("1×w+2×x+3×y+4×z+5×ɑ+6×β+7×ɣ+8×Δ", "1÷w+2÷x+3÷y+4÷z+5÷ɑ+6÷β+7÷ɣ+8÷Δ"),
                Arrays.asList("1×10.0+2×20.0+3×30.0+4×40.0+5×50.0+6×60.0+7×70.0+8×80.0", "1÷10.0+2÷20.0+3÷30.0+4÷40.0+5÷50.0+6÷60.0+7÷70.0+8÷80.0"),
                Arrays.asList('w', 'x', 'y', 'z', 'ɑ', 'β', 'ɣ', 'Δ'),true));
        testCases.add(new TestCase("1×w + 2×sinx + 3×cosy + 4×tanz + 5×cos⁻¹ɑ + 6×sin⁻¹β + 7×tan⁻¹ɣ + 8×10nPrΔ =    1÷15nCrw + 2÷lnx + 3÷log₁₀y + 4÷z^2 + 5÷ɑ! + 6÷√β + 7÷∛ɣ + 8÷Δ%",
                Arrays.asList("1×w+2×sinx+3×cosy+4×tanz+5×cos⁻¹ɑ+6×sin⁻¹β+7×tan⁻¹ɣ+8×10nPrΔ", "1÷15nCrw+2÷lnx+3÷log₁₀y+4÷z^2+5÷ɑ!+6÷√β+7÷∛ɣ+8÷Δ%"),
                Arrays.asList("1×10.0+2×sin20.0+3×cos30.0+4×tan40.0+5×cos⁻¹50.0+6×sin⁻¹60.0+7×tan⁻¹70.0+8×10nPr80.0", "1÷15nCr10.0+2÷ln20.0+3÷log₁₀30.0+4÷40.0^2+5÷50.0!+6÷√60.0+7÷∛70.0+8÷80.0%"),
                Arrays.asList('w', 'x', 'y', 'z', 'ɑ', 'β', 'ɣ', 'Δ'),true));

        // Run each test case programmatically by looping over the cases
        int counter = 0;
        for (TestCase testCase : testCases) {
            Function function = new Function(testCase.input);
            /**
             * Assigning parameters into function
             */
            HashMap<Character,Double> userAssigned = new HashMap<>();
            userAssigned.put('w', 10.0);
            userAssigned.put('x', 20.0);
            userAssigned.put('y', 30.0);
            userAssigned.put('z', 40.0);
            userAssigned.put('ɑ', 50.0);
            userAssigned.put('β', 60.0);
            userAssigned.put('ɣ', 70.0);
            userAssigned.put('Δ', 80.0);
            function.assignParameters(userAssigned);

            System.out.println("Finish test " + counter + " !\n");
            assertEquals("Invalid function assessment", Arrays.asList(function.getSubStrings(), function.getAssignedSubStrings(), function.parametersInFunction(), function.checkValid()),
                    Arrays.asList(testCase.subStrings, testCase.assignedSubStrings, testCase.parameters, testCase.validityExpected));
            function.functionSummary();
            counter ++;
        }
    }
}
