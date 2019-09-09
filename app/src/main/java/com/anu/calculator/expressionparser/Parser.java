package com.anu.calculator.expressionparser;

import android.util.Log;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParser;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.exceptions.NothingEnteredException;


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
 *  - 05/09/2019: Refactored class to implement ExpressionParser interface
 * 	- 05/09/2019: Refactored name from ExpressionParser to Parser
 * 	- 05/09/2019: Altered constructor
 */

public class Parser implements ExpressionParser
{

    private static final String TAG = "EXPRESSION_PARSER";

    private Tokenizer _tokenizer;
    private Boolean degrees;
    private Integer precision;

    @Override
    public Expression parse(String expression) throws ParserException
    {
        if(checkRawExpression(expression))
        {
            _tokenizer = new Tokenizer(expression);
            Expression parsedExpression = parseExp();
            if(precision != null) parsedExpression.updatePrecision(precision);
            return parsedExpression;
        }
        else throw new MathematicalSyntaxException(TAG, "Syntax error");
    }

    @Override
    public Expression parse(String expression, Boolean degrees, Integer precision) throws ParserException {

        //Interferes with JUnit tests - uncomment if you need to (Sam)
        //Log.d(TAG,"Degrees: "+degrees);
        //Log.d(TAG,"Precision: "+precision);

        _tokenizer = new Tokenizer(expression);
        this.degrees = degrees;
        this.precision = precision;
        return parse(expression);
    }

    /**
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
     * upon whether they are leading, trailing, or both.
     *
     * Uses grammar: <operation> ::= sin<exp> | sin⁻¹<exp> | cos<exp> | cos⁻¹<exp> |
     *                  tan<exp> | tan⁻¹<exp> | log₁₀<exp> | ln<exp> | !<exp> | √<exp> |
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
     * are evaluated first (i.e. [B]ODMAS).
     *
     * Uses grammar: <literal> ::= π | e | rand | double | -double | <unknown variable>
     *               <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
     *
     * @return type: Expression
     */
    private Expression parseLiteral()
    {
        Expression literal = null;

        if(_tokenizer.current().type() == Token.Type.RANDOM_NUMBER)
            literal = new RandomNumberExpression();
        else if(_tokenizer.current().type() == Token.Type.PI)
            literal = new PiExpression();
        else if(_tokenizer.current().type() == Token.Type.E)
            literal =  new EExpression();
        else if(_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE)
            literal = new UnknownVariableExpression(_tokenizer.current().token().charAt(0));
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
                literal = doubleValue;
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_PARENTHESIS)
        {
            _tokenizer.next();
            literal = parseExp();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACE)
        {
            _tokenizer.next();
            literal = parseExp();
        }
        else if(_tokenizer.current().type() == Token.Type.RIGHT_BRACKET)
        {
            _tokenizer.next();
            literal = parseExp();
        }

        _tokenizer.next();
        return literal;
    }
}
