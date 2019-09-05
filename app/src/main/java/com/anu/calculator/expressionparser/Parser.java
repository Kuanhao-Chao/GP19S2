package com.anu.calculator.expressionparser;

import com.anu.calculator.Expression;
import com.anu.calculator.ExpressionParser;

/**
 * Grammar:
 * <exp> ::= <term> | <exp> + <term> | <exp> − <term>
 *
 * <term> ::= <operation> | <term> × <operation> | <term> ÷ <operation>
 *
 * <operation> ::= sin<exp> | sin⁻¹<exp> | cos<exp> | cos⁻¹<exp> |
 *      tan<exp> | tan⁻¹<exp> | log₁₀<exp> | ln<exp> | !<exp> | √<exp> |
 *      ∛<exp> | <exp>nPr<exp> | <exp>nCr<exp> | <exp>^<exp> | <exp>² |
 *      <exp>³ | -<exp> | <exp>% | (<exp>) | <literal>
 *
 * <literal> ::= π | e | rand | double | <unknown variable>
 *
 * <unknown variable> ::= w | x | y | z | ɑ | β | ɣ | Δ
 */

public class Parser implements ExpressionParser
{

    Tokenizer _tokenizer;

    @Override
    public Expression parse(String expression) {
        _tokenizer = new Tokenizer(expression);
        return parseExp();
    }


    public Expression parseExp() {
        Expression term = parseTerm();
        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.ADD)
        {
            _tokenizer.next();
            Expression expression = parseExp();
            return new AddExpression(term, expression);
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SUBTRACT)
        {
            _tokenizer.next();
            Expression expression = parseExp();
            return new SubtractExpression(term, expression);
        }
        else return term;
    }

    private Expression parseTerm()
    {
        Expression operation = parseOperation();
        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.DIVIDE)
        {
            _tokenizer.next();
            Expression expression = parseTerm();
            return new DivideExpression(operation, expression);
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.MULTIPLY)
        {
            _tokenizer.next();
            Expression expression = parseTerm();
            return new MultiplyExpression(operation, expression);
        }
        else return operation;
    }

    private Expression parseOperation()
    {
        Expression literal;
        Token holdToken;

        //if it's a leading token - save the token, get the applicable exp and return
        if(_tokenizer.current().type().isLeading())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            literal = parseLiteral();
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
                case NEGATIVE: return new NegativeExpression(literal);
            }
        }

        //get the next exp
        literal = parseLiteral();

        //check if the current token is trailing or both
        if(_tokenizer.hasNext() && _tokenizer.current().type().isTrailing())
        {
           holdToken = _tokenizer.current();
           _tokenizer.next();
           switch(holdToken.type())
           {
               case PERCENT: return new PercentExpression(literal);
               case FACTORIAL: return new FactorialExpression(literal);
               case SQUARE: return new PowerExpression(literal, new DoubleExpression(2));
               case CUBE: return new PowerExpression(literal, new DoubleExpression(3));
           }
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type().isLeadingAndTrailing())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            Expression expression = parseLiteral();
            switch(holdToken.type())
            {
                case POWER: return new PowerExpression(literal, expression);
                case PERMUTATION: return new PermutationExpression(literal, expression);
                case COMBINATION: return new CombinationExpression(literal, expression);
            }
        }

        return literal;
    }

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
            literal = new DoubleExpression(Double.parseDouble(_tokenizer.current().token()));
        else if(_tokenizer.current().type() == Token.Type.LEFT_PARENTHESIS)
        {
            _tokenizer.next();
            literal = parseExp();
            _tokenizer.next();
        }

        _tokenizer.next();
        return literal;
    }


}
