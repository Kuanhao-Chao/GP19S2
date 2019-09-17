package com.anu.calculator.expressionparser;

import android.util.Log;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParser;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.exceptions.NothingEnteredException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


/**
 * Parser: The primary parser for evaluating the mathematical statements entered by the user.
 * The parser parses mathematical expressions in reverse to ensure that equivalent operators
 * (e.g. ADD and SUBTRACT, or MULTIPLY and DIVIDE) are evaluated left to right.
 * The parser uses the following grammar.
 *
 * <exp> ::= <term> | <exp> + <term> | <exp> − <term>
 * <term> ::= <operation> | <term> × <operation> | <term> ÷ <operation>
 * <operation> ::= sin<exp> | sin⁻¹<exp> | cos<exp> | cos⁻¹<exp> |
 *      tan<exp> | tan⁻¹<exp> | log₁₀<exp> | ln<exp> | !<exp> | √<exp> |
 *      ∛<exp> | <exp>nPr<exp> | <exp>nCr<exp> | <exp>^<exp> | <exp>² |
 *      <exp>³ | -<exp> | <exp>% | (<exp>) | <literal>
 * <literal> ::= π | e | rand | double | <unknown variable>
 * <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 * @modofoed: Howard Chao (u7022787)
 *  - 05/09/2019: Refactored class to implement ExpressionParser interface
 * 	- 05/09/2019: Refactored name from ExpressionParser to Parser
 * 	- 05/09/2019: Altered constructor
 */

public class Parser implements ExpressionParser
{

    private static final String TAG = "EXPRESSION_PARSER";
    List<Character> definedParameter = Arrays.asList('w', 'x', 'y', 'z', 'ɑ', 'β', 'ɣ', 'Δ');

    private Tokenizer _tokenizer;
    private Boolean degrees;
    private Integer precision;

    @Override
    public Expression parse(String expression, Stack<Expression> history)
    {
        _tokenizer = new Tokenizer(expression);
        return parseFun(history);
    }

    @Override
    public Expression parse(String expression, Boolean degrees, Integer precision, Stack<Expression> history) {
        Log.d(TAG,"Degrees: "+degrees);
        Log.d(TAG,"Precision: "+precision);
        return parse(expression, history);
    }

    /**
     * parseFunc: The top level parsing method. There are three cases.
     *
     * Uses grammar: <func> ::= <unknown variable> = <exp> | <exp> (unknown variable or normal expression)
     *
     * @return type: Expression
     */
    private Expression parseFun(Stack<Expression> history) {
        Expression exp = parseExp(history);
        String expShowResult = exp.show();
        if (expShowResult.length() == 1 && definedParameter.contains(expShowResult)) {
            /**
             * <unknown variable> case !
             */
            /**
             * Check history stack and assign value into it. Otherwise, error would occur.
             */
            UnknownVariableExpression unknownVariableExpression = new UnknownVariableExpression(expShowResult.charAt(0));
            for (int i = 0; i < history.size(); i++) {
                String outputString = history.elementAt(i).show();
                if (outputString.contains(expShowResult.charAt(0) + "=")) {
                    unknownVariableExpression.assignValue(history.elementAt(i));
                }
            }
            try {
                unknownVariableExpression.evaluate();
            } catch (ParserException e) {
                e.printStackTrace();
            }
            return unknownVariableExpression;
        } else {
            /**
             * <unknown variable> = <exp> or <exp> case & the last element is an
             */
            /**
             * Evaluate expression to make sure that it has all variables assigned. Here, the expression
             * must be assigned.
             */
            if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.EQUAL) {
                /**
                 * <unknown variable> = <exp>  case
                 */
                _tokenizer.next();
                Character unknownChar = _tokenizer.current().token().charAt(0);
                UnknownVariableExpression unknownVariableExpressionEqua = new UnknownVariableExpression(unknownChar, exp);
                /**
                 * Check the history to update the unknown variable's value in the history stack.
                 */
                for (int i = 0; i < history.size(); i++) {
                    /**
                     * Reparse again the expression.
                     */
                    String reParseString = history.get(i).show();
                    Stack<Expression> subStack = new Stack<Expression>();
                    subStack.addAll(history.subList(0,i));
                    ExpressionParser reParser = new Parser();
                    Expression reParsFunResult = reParser.parse(reParseString, subStack);
                    try {
                        reParsFunResult.evaluate();
                    } catch (ParserException e) {
                        e.printStackTrace();
                    }
                    history.set(i, reParsFunResult);
                    /**
                     * Set the changed variable history.
                     */
                    String outputString = history.elementAt(i).show();
                    if (outputString.contains(unknownChar + "=")) {
                        UnknownVariableExpression unknownVariableExpressionIns = new UnknownVariableExpression(unknownChar, exp);
                        history.set(i, new EqualityExpression(unknownVariableExpressionIns, exp));
                        try {
                            exp.evaluate();
                        } catch (ParserException e) {
                            e.printStackTrace();
                        }
                    }
                }
                EqualityExpression equalityExpression = new EqualityExpression(unknownVariableExpressionEqua, exp);
                try {
                    equalityExpression.evaluate();
                } catch (ParserException e) {
                    e.printStackTrace();
                }
                return equalityExpression;
            }
            /**
             * <exp>  case
             */
            return exp;
        }
    }


    /**
<<<<<<< HEAD
     * checkRawExpression: conducts a preliminary check of the expression string entered by the user
     * prior to being passed to Parser.parse(). This is to catch any easy-to-detect errors to save
     * time.
     *
     * @param expression
     * @return type: boolean
     */
    private boolean checkRawExpression(String expression) throws ParserException
    {
        //check whether the user has entered something
        if(expression.equals("")) throw new NothingEnteredException(TAG, "");

        //simple check for whether there are matching numbers of brackets, braces and parentheses
        //it DOES NOT check whether they are nested correctly
        int braceCnt, parenCnt, brackCnt;
        braceCnt = parenCnt = brackCnt = 0;
        for(int i = 0; i<expression.length(); i++)
        {
            if(expression.charAt(i) == '(') parenCnt++;
            else if(expression.charAt(i) == '{') braceCnt++;
            else if(expression.charAt(i) == '[') brackCnt++;
            else if(expression.charAt(i) == ')') parenCnt--;
            else if(expression.charAt(i) == '}') braceCnt--;
            else if(expression.charAt(i) == ']') brackCnt--;
        }
        if(braceCnt != 0 || parenCnt != 0 || brackCnt != 0) return false;

        //more tests here

        return true;
    }

    /**
     * parseExp: The top level parsing method, ensuring that addition and subtraction are done
=======
     * parseExp: The second level parsing method, ensuring that addition and subtraction are done
>>>>>>> functionParser
     * last (i.e. BODM[AS]).
     *
     * Uses grammar: <exp> ::= <term> | <exp> + <term> | <exp> − <term>
     *
     * @return type: Expression
     */
    private Expression parseExp(Stack<Expression> history)
    {
        Expression term = parseTerm(history);
        if(_tokenizer.hasNext() && (_tokenizer.current().type() == Token.Type.ADD ||
                _tokenizer.current().type() == Token.Type.SUBTRACT))
        {
            Token holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseExp( history);
            return (holdToken.type() == Token.Type.ADD) ?
                    new AddExpression(expression, term) :
                    new SubtractExpression(expression, term);
        }
        else return term;
    }

    /**
     * parseTerm: The second level parsing method, ensuring that division and multiplication
     * are done second last (i.e. BO[DM]AS).
     *
     * Uses grammar: <term> ::= <operation> | <term> × <operation> | <term> ÷ <operation>
     *
     * @return type: Expression
     */
    private Expression parseTerm(Stack<Expression> history)
    {
        Expression operation = parseOperation( history);
        if(_tokenizer.hasNext() && (_tokenizer.current().type() == Token.Type.DIVIDE ||
                _tokenizer.current().type() == Token.Type.MULTIPLY))
        {
            Token holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseTerm( history);
            return (holdToken.type() == Token.Type.DIVIDE) ?
                    new DivideExpression(expression, operation) :
                    new MultiplyExpression(expression, operation);
        }
        else return operation;
    }

    /**
     * parseOperation: The third level parsing method, ensuring that defined operations are
     * evaluated second (i.e. B[O]DMAS). This method handles operators differently depending
     * upon whether they are leading, trailing, or both.
     *
     * Uses grammar: <operation> ::= sin<exp> | sin⁻¹<exp> | cos<exp> | cos⁻¹<exp> |
     *                  tan<exp> | tan⁻¹<exp> | log₁₀<exp> | ln<exp> | !<exp> | √<exp> |
     *                  ∛<exp> | <exp>nPr<exp> | <exp>nCr<exp> | <exp>^<exp> | <exp>² |
     *                  <exp>³ | -<exp> | <exp>% | (<exp>) | <literal>
     *
     * @return type: Expression
     */
    private Expression parseOperation(Stack<Expression> history)
    {
        Expression literal;
        Token holdToken;

        //if it's a trailing token - save the token, get the applicable exp and return
        if(_tokenizer.current().type().isTrailing())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            literal = parseLiteral(history);
            switch(holdToken.type())
            {
                case PERCENT: return new PercentExpression(literal);
                case FACTORIAL: return new FactorialExpression(literal);
                case SQUARE: return new PowerExpression(literal, new DoubleExpression(2));
                case CUBE: return new PowerExpression(literal, new DoubleExpression(3));
            }
        }

        //get the next exp
        literal = parseLiteral(history);

        //check if the current token is leading or both
        if(_tokenizer.hasNext() && _tokenizer.current().type().isLeading())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            switch(holdToken.type())
            {
                case SINE: return new SineExpression(literal, degrees);
                case ARC_SINE: return new ArcSineExpression(literal, degrees);
                case COSINE: return new CosineExpression(literal, degrees);
                case ARC_COSINE: return new ArcCosineExpression(literal, degrees);
                case TANGENT: return new TangentExpression(literal, degrees);
                case ARC_TANGENT: return new ArcTangentExpression(literal, degrees);
                case LOG_NATURAL: return new LogNaturalExpression(literal);
                case LOG_TEN: return new LogTenExpression(literal);
                case SQUARE_ROOT: return new SquareRootExpression(literal);
                case CUBED_ROOT: return new CubedRootExpression(literal);
            }
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type().isLeadingAndTrailing())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseLiteral( history);
            switch(holdToken.type())
            {
                case POWER: return new PowerExpression(expression, literal);
                case PERMUTATION: return new PermutationExpression(expression, literal);
                case COMBINATION: return new CombinationExpression(expression, literal);
            }
        }

        return literal;
    }

    /**
     * parseLiteral: The lowest level parsing method, returns literals and ensures that parentheses
     * are evaluated first (i.e. [B]ODMAS).
     *
     * Uses grammar: <literal> ::= π | e | rand | double | -double | <unknown variable> | double<unknown variable>
     *               <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
     *
     * @return type: Expression
     */
    /**
     * Modified: Howard Chao (u7022787)
     *      Move `_tokenizer.next();` into each condition.
     *      Now can parse both (2×x) & (2x)
     */
    private Expression parseLiteral(Stack<Expression> history)
    {
        Expression literal = null;

        if(_tokenizer.current().type() == Token.Type.RANDOM_NUMBER) {
            literal = new RandomNumberExpression();
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.PI) {
            literal = new PiExpression();
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.E) {
            literal =  new EExpression();
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
            literal = new UnknownVariableExpression(_tokenizer.current().token().charAt(0));
            /**
             * Modified: Howard Chao (u7022787)
             *      Update history stack.
             */
            for (int i = 0; i < history.size(); i++) {
                String outputString = history.elementAt(i).show();
                if (outputString.contains(_tokenizer.current().token().charAt(0) + "=")) {
                    literal = new UnknownVariableExpression(_tokenizer.current().token().charAt(0), history.elementAt(i));
                    try {
                        literal.evaluate();
                    } catch (ParserException e) {
                        e.printStackTrace();
                    }
                }
            }
            /**
             * Modified: Howard Chao (u7022787)
             *      Slightly change the parsing rule. Now can parse both (2×x) & (2x)
             */
            _tokenizer.next();
            if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.DOUBLE)
            { //returns either a 'negative double' or double
                boolean negative = false;
                Token next = _tokenizer.checkAhead(1);
                Token afterNext = _tokenizer.checkAhead(2);
                Expression doubleExpressionCoef = null;
                if(next != null && next.type() == Token.Type.SUBTRACT)
                {
                    if( afterNext == null ||
                            afterNext.type() == Token.Type.SUBTRACT ||
                            afterNext.type() == Token.Type.ADD ||
                            afterNext.type() == Token.Type.MULTIPLY ||
                            afterNext.type() == Token.Type.DIVIDE ||
                            afterNext.type() == Token.Type.LEFT_PARENTHESIS)
                    {
                        doubleExpressionCoef = new SubtractExpression(new DoubleExpression(0d),
                                new DoubleExpression(Double.parseDouble(_tokenizer.current().token())));
                        negative = true;
                        _tokenizer.next();
                    }
                }
                if(!negative)
                    doubleExpressionCoef = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
                MultiplyExpression multiplyExpression = new MultiplyExpression(literal, doubleExpressionCoef);
                literal = multiplyExpression;
                _tokenizer.next();
            }
        }
        else if(_tokenizer.current().type() == Token.Type.DOUBLE)
        { //returns either a 'negative double' or double
            DoubleExpression doubleValue = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
            boolean negative = false;
            Token next = _tokenizer.checkAhead(1);
            Token afterNext = _tokenizer.checkAhead(2);
            if(next != null && next.type() == Token.Type.SUBTRACT)
            {
                if( afterNext == null ||
                        afterNext.type() == Token.Type.SUBTRACT ||
                        afterNext.type() == Token.Type.ADD ||
                        afterNext.type() == Token.Type.MULTIPLY ||
                        afterNext.type() == Token.Type.DIVIDE ||
                        afterNext.type() == Token.Type.LEFT_PARENTHESIS)
                {
                    literal = new SubtractExpression(new DoubleExpression(0d), doubleValue);
                    negative = true;
                    _tokenizer.next();
                }
            }
            if(!negative)
                literal = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_PARENTHESIS)
        {
            _tokenizer.next();
            literal = parseExp( history);
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACE)
        {
            _tokenizer.next();
            literal = parseExp( history);
            _tokenizer.next();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACKET)
        {
            _tokenizer.next();
            literal = parseExp( history);
            _tokenizer.next();
        }

        return literal;
    }
}
