package com.anu.calculator.parsers;

import com.anu.calculator.Expression;
import com.anu.calculator.Parser;
import com.anu.calculator.ParserException;
import com.anu.calculator.expressions.*;
import com.anu.calculator.utilities.ExpressionChecker;
import com.anu.calculator.utilities.History;
import com.anu.calculator.utilities.HistoryItem;
import com.anu.calculator.utilities.Token;
import com.anu.calculator.utilities.Tokenizer;

import java.util.HashMap;


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
 * <literal> ::= π | e | rand | double | -double | <unknown variable>
 * <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
 *
 * @author: Samuel Brookes (u5380100)
 * @modified: Michael Betterton (u6797866)
 *  - 05/09/2019: Refactored class to implement ExpressionParser interface
 * 	- 05/09/2019: Refactored name from ExpressionParser to Parser
 */

public class ExpressionParser implements Parser
{
    private static final String TAG = "EXPRESSION_PARSER";
    private Tokenizer _tokenizer;
    private Boolean degrees;
    private History history;
    private HashMap<Character, HistoryItem> rawHistory;

    /**
     * Parse method for functions.
     *
     * @param expression
     * @param degrees
     * @param precision
     * @param history
     * @return Expression (an EqualityExpression)
     * @throws ParserException
     */
    @Override
    public Expression parse(String expression, Boolean degrees, Integer precision, History history) throws ParserException
    {
        //Check that the expression is valid
        new ExpressionChecker(expression).checkExpression();

        //Initiate global variables
        this.history = history;
        this.degrees = degrees;
        _tokenizer = new Tokenizer(expression);

        //Evaluate everything on the right-hand side of the equation (if there is an equals sign)
        return checkForEqualityExpression(parseExp(), precision);
    }

    /**
     * Parse method for the history class.
     *
     * @param expression
     * @param rawHistory
     * @return Expression
     */
    public Expression parseHistory(String expression, Boolean degrees, HashMap<Character, HistoryItem> rawHistory) throws ParserException
    {
        //Check that the expression is valid
        new ExpressionChecker(expression).checkExpression();

        //Save the raw history
        this.degrees = degrees;
        this.rawHistory = rawHistory;
        _tokenizer = new Tokenizer(expression);

        //Parse the expression
        return checkForEqualityExpression(parseExp(), null);
    }

    /**
     * This method checks for whether there is a hanging equals sign once
     * the parseExp() method is completed. If there is a hanging equals sign,
     * an EqualityExpression is returned, otherwise the parsed expression
     * is returned unchanged.
     *
     * @param exp
     * @param precision
     * @return Expression
     */
    private Expression checkForEqualityExpression(Expression exp, Integer precision)
    {
        EqualityExpression equality = null;
        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.EQUALS)
        {
            //If the current token is EQUALS, this is an equality expression
            _tokenizer.next();
            equality = new EqualityExpression(_tokenizer.current().token().charAt(0), exp);
            if(precision != null) equality.updatePrecision(precision);
        }
        else
        {
            //Otherwise it is another kind of expression
            if(precision != null) exp.updatePrecision(precision);
        }

        return (equality == null)? exp : equality;
    }

    /**
     * parseExp: The top level parsing method, ensuring that addition and subtraction are done
     * last (i.e. BODM[AS]).
     *
     * Uses grammar: <exp> ::= <term> | <exp> + <term> | <exp> − <term>
     *
     * @return type: Expression
     */
    private Expression parseExp()
    {
        Expression term = parseTerm();
        if(_tokenizer.hasNext() && (_tokenizer.current().type() == Token.Type.ADD ||
                _tokenizer.current().type() == Token.Type.SUBTRACT))
        {
            Token holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseExp();
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
    private Expression parseTerm()
    {
        Expression operation = parseOperation();
        if(_tokenizer.hasNext() && (_tokenizer.current().type() == Token.Type.DIVIDE ||
                _tokenizer.current().type() == Token.Type.MULTIPLY))
        {
            Token holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseTerm();
            return (holdToken.type() == Token.Type.DIVIDE) ?
                    new DivideExpression(expression, operation) :
                    new MultiplyExpression(expression, operation);
        }
        else return operation;
    }

    /**
     * parseOperation: The third level parsing method, ensuring that defined operations are
     * evaluated second (i.e. B[O]DMAS). This method handles operators differently depending
     * upon whether they are leading, trailing, or both (see Token class for more information).
     *
     * Uses grammar: <operation> ::= sin<exp> | sin⁻¹<exp> | cos<exp> | cos⁻¹<exp> |
     *                  tan<exp> | tan⁻¹<exp> | log₁₀<exp> | ln<exp> | <exp>! | √<exp> |
     *                  ∛<exp> | <exp>nPr<exp> | <exp>nCr<exp> | <exp>^<exp> | <exp>² |
     *                  <exp>³ | -<exp> | <exp>% | (<exp>) | <literal>
     *
     * @return type: Expression
     */
    private Expression parseOperation()
    {
        Expression literal;
        Token holdToken;

        //if it's a trailing token - save the token, get the applicable exp and return
        if(_tokenizer.current().type().isTrailing())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            literal = parseLiteral();
            switch(holdToken.type())
            {
                case PERCENT: return new PercentExpression(literal);
                case FACTORIAL: return new FactorialExpression(literal);
                case SQUARE: return new PowerExpression(literal, new DoubleExpression(2));
                case CUBE: return new PowerExpression(literal, new DoubleExpression(3));
            }
        }

        //get the next exp
        literal = parseLiteral();

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
            Expression expression = parseLiteral();
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
     * are evaluated first (i.e. [B]ODMAS). This method handles shorthand multiplication (e.g.
     * 2x or 2(10)) by appending the buffer in the tokenizer with a multiplication symbol to
     * ensure that it is processed correctly.
     *
     * Uses grammar: <literal> ::= π | e | rand | double | -double | <unknown variable>
     *               <unknown variable> ::= a-d, f-z | ɑ | β | ɣ | Δ
     *
     * @return type: Expression
     */
    private Expression parseLiteral()
    {
        Expression literal = null;

        if(_tokenizer.current().type() == Token.Type.RANDOM_NUMBER)
            literal = new RandomNumberExpression();
        else if(_tokenizer.current().type() == Token.Type.PI)
        {
            literal = new PiExpression();

            //check for the use of shorthand multiplication
            Token next = _tokenizer.checkAhead(1);
            if(next != null && next.type() == Token.Type.DOUBLE)
                _tokenizer.appendMultiply();
        }
        else if(_tokenizer.current().type() == Token.Type.E)
        {
            literal =  new EExpression();

            //check for the use of shorthand multiplication
            Token next = _tokenizer.checkAhead(1);
            if(next != null && next.type() == Token.Type.DOUBLE)
                _tokenizer.appendMultiply();
        }
        else if(_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE)
        {
            char variable = _tokenizer.current().token().charAt(0);

            if(history != null)
            {
                if(history.hasVariable(variable))
                    literal = new UnknownVariableExpression(variable, history.getExpression(variable));
            }
            else if(rawHistory != null)
            {
                literal = new UnknownVariableExpression(variable, rawHistory.get(variable).getExpression());
            }

            if(literal == null)
                literal = new UnknownVariableExpression(variable);

            //check for the use of shorthand multiplication
            Token next = _tokenizer.checkAhead(1);
            if(next != null && next.type() == Token.Type.DOUBLE)
                _tokenizer.appendMultiply();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_PARENTHESIS ||
                _tokenizer.current().type() == Token.Type.RIGHT_BRACE ||
                _tokenizer.current().type() == Token.Type.RIGHT_BRACKET)
        {
            _tokenizer.next();
            literal = parseExp();

            //check for the use of shorthand multiplication
            Token next = _tokenizer.checkAhead(1);
            if(next != null && next.type() == Token.Type.DOUBLE)
                _tokenizer.appendMultiply();
        }
        else if(_tokenizer.current().type() == Token.Type.DOUBLE)
        {
            //returns either a 'negative double' or double
            DoubleExpression doubleValue = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
            boolean negative = false;

            //check whether the double is a negative number
            Token next = _tokenizer.checkAhead(1);
            Token afterNext = _tokenizer.checkAhead(2);
            if(next != null && next.type() == Token.Type.SUBTRACT)
            {
                if( afterNext == null ||
                        afterNext.type() == Token.Type.SUBTRACT ||
                        afterNext.type() == Token.Type.ADD ||
                        afterNext.type() == Token.Type.MULTIPLY ||
                        afterNext.type() == Token.Type.DIVIDE ||
                        afterNext.type() == Token.Type.LEFT_PARENTHESIS ||
                        afterNext.type() == Token.Type.LEFT_BRACE ||
                        afterNext.type() == Token.Type.LEFT_BRACKET ||
                        afterNext.type() == Token.Type.EQUALS)
                {
                    //if it's negative, return the number subtracted from zero
                    literal = new SubtractExpression(new DoubleExpression(0d), doubleValue);
                    negative = true;
                    _tokenizer.next();
                }
            }

            //if it's not negative, return the double
            if(!negative)
                literal = doubleValue;
        }

        _tokenizer.next();
        return literal;
    }
}
