package com.anu.calculator.expressionparser;

import android.util.Log;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParser;
import com.anu.calculator.ParserException;

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

    Tokenizer _tokenizer;

    @Override
    public Expression parse(String expression, Stack<Expression> history)
    {
        _tokenizer = new Tokenizer(expression);
        return parseFun(expression, history);
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
     * Uses grammar: <func> ::= <unknown variable> | <unknown variable> = <exp> | <exp>
     *
     * @return type: Expression
     */
    private Expression parseFun(String expression, Stack<Expression> history) {
        Character firstChar = _tokenizer.current().token().charAt(0);
        if ( definedParameter.contains(firstChar)) {
            _tokenizer.next();
            if (!_tokenizer.hasNext()) {
                /**
                 * <unknown variable> case !
                 */
                UnknownVariableExpression unknownVariableExpression = new UnknownVariableExpression(firstChar);
                for (int i = 0; i < history.size(); i++) {
                    String outputString = history.elementAt(i).show();
                    if (outputString.contains(firstChar + "=")) {
                        unknownVariableExpression.assignValue(history.elementAt(i));
                    }
                }
                return unknownVariableExpression;
            } else {
                /**
                 * <unknown variable> = <exp> or <exp> case & the last element is an
                 * unknown variable.
                 * Restart the _tokenizer
                 */
                _tokenizer = new Tokenizer(expression);
            }
        }
        Expression exp = parseExp(history);
        /**
         * Evaluate expression to make sure that it has all variables assigned. Here, the expression
         * must be assigned.
         */
        try {
            exp.evaluate();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.EQUAL) {
            /**
             * <unknown variable> = <exp>  case
             */
            _tokenizer.next();
            UnknownVariableExpression unknownVariableExpressionEqua = new UnknownVariableExpression(_tokenizer.current().token().charAt(0), exp);
            /**
             * Check the history to update the unknown variable's value in the history stack.
             */
            for (int i = 0; i < history.size(); i++) {
                String outputString = history.elementAt(i).show();
                if (outputString.contains(_tokenizer.current().token().charAt(0) + "=")) {
                    history.set(i, exp);
                }
            }
            EqualityExpression equalityExpression = new EqualityExpression(unknownVariableExpressionEqua, exp);
            return equalityExpression;
        }
        /**
         * <exp>  case
         */
        return exp;
    }


    /**
     * parseExp: The second level parsing method, ensuring that addition and subtraction are done
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
                case SINE: return new SineExpression(literal);
                case ARC_SINE: return new ArcSineExpression(literal);
                case COSINE: return new CosineExpression(literal);
                case ARC_COSINE: return new ArcCosineExpression(literal);
                case TANGENT: return new TangentExpression(literal);
                case ARC_TANGENT: return new ArcTangentExpression(literal);
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
     * Uses grammar: <literal> ::= π | e | rand | double | -double | <unknown variable>
     *               <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
     *
     * @return type: Expression
     */
    private Expression parseLiteral(Stack<Expression> history)
    {
        Expression literal = null;

        if(_tokenizer.current().type() == Token.Type.RANDOM_NUMBER)
            literal = new RandomNumberExpression();
        else if(_tokenizer.current().type() == Token.Type.PI)
            literal = new PiExpression();
        else if(_tokenizer.current().type() == Token.Type.E)
            literal =  new EExpression();
        else if(_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
            literal = new UnknownVariableExpression(_tokenizer.current().token().charAt(0));










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
        }
        else if(_tokenizer.current().type() == Token.Type.DOUBLE)
        { //returns either a 'negative double' or double
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
                    literal = new SubtractExpression(new DoubleExpression(0d),
                            new DoubleExpression(Double.parseDouble(_tokenizer.current().token())));
                    negative = true;
                    _tokenizer.next();
                }
            }
            if(!negative)
                literal = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_PARENTHESIS)
        {
            _tokenizer.next();
            literal = parseExp( history);
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACE)
        {
            _tokenizer.next();
            literal = parseExp( history);
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACKET)
        {
            _tokenizer.next();
            literal = parseExp( history);
        }

        _tokenizer.next();
        return literal;
    }
}
