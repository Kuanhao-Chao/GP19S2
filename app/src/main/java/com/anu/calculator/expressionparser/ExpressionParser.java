package com.anu.calculator.expressionparser;

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

public class ExpressionParser
{

    Tokenizer _tokenizer;

    public ExpressionParser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parseExp() {
        Exp term = parseTerm();
        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.ADD)
        {
            _tokenizer.next();
            Exp exp = parseExp();
            return new AddExp(term, exp);
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SUBTRACT)
        {
            _tokenizer.next();
            Exp exp = parseExp();
            return new SubtractExp(term, exp);
        }
        else return term;
    }

    private Exp parseTerm()
    {
        Exp operation = parseOperation();
        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.DIVIDE)
        {
            _tokenizer.next();
            Exp exp = parseTerm();
            return new DivideExp(operation, exp);
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.MULTIPLY)
        {
            _tokenizer.next();
            Exp exp = parseTerm();
            return new MultiplyExp(operation, exp);
        }
        else return operation;
    }

    private Exp parseOperation()
    {
        Exp literal;
        Token holdToken;

        //if it's a leading token - save the token, get the applicable exp and return
        if(_tokenizer.current().type().isLeading())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            literal = parseLiteral();
            switch(holdToken.type())
            {
                case SINE: return new SineExp(literal);
                case ARC_SINE: return new ArcSineExp(literal);
                case COSINE: return new CosineExp(literal);
                case ARC_COSINE: return new ArcCosineExp(literal);
                case TANGENT: return new TangentExp(literal);
                case ARC_TANGENT: return new ArcTangentExp(literal);
                case LOG_NATURAL: return new LogNaturalExp(literal);
                case LOG_TEN: return new LogTenExp(literal);
                case SQUARE_ROOT: return new SquareRootExp(literal);
                case CUBED_ROOT: return new CubedRootExp(literal);
                case NEGATIVE: return new NegativeExp(literal);
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
               case PERCENT: return new PercentExp(literal);
               case FACTORIAL: return new FactorialExp(literal);
               case SQUARE: return new PowerExp(literal, new DoubleExp(2));
               case CUBE: return new PowerExp(literal, new DoubleExp(3));
           }
        }
        else if(_tokenizer.hasNext() && _tokenizer.current().type().isLeadingAndTrailing())
        {
            holdToken = _tokenizer.current();
            _tokenizer.next();
            Exp exp = parseLiteral();
            switch(holdToken.type())
            {
                case POWER: return new PowerExp(literal, exp);
                case PERMUTATION: return new PermutationExp(literal, exp);
                case COMBINATION: return new CombinationExp(literal, exp);
            }
        }

        return literal;
    }

    private Exp parseLiteral()
    {
        Exp literal = null;

        if(_tokenizer.current().type() == Token.Type.RANDOM_NUMBER)
            literal = new RandomNumberExp();
        else if(_tokenizer.current().type() == Token.Type.PI)
            literal = new PiExp();
        else if(_tokenizer.current().type() == Token.Type.E)
            literal =  new EExp();
        else if(_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE)
            literal = new UnknownVariableExp(_tokenizer.current().token().charAt(0));
        else if(_tokenizer.current().type() == Token.Type.DOUBLE)
            literal = new DoubleExp(Double.parseDouble(_tokenizer.current().token()));
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
