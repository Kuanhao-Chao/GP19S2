package com.anu.calculator.expressionparser;

/**
 * Grammar:
 * <exp> ::= <term> | <exp> + <term> | <exp> - <term>
 * <term> ::= <operation> | <term> × <operation> | <term> / <operation>
 * <operation> ::= sin(<exp>) | sin⁻¹(<exp>) | cos(<exp>) | cos⁻¹(<exp>) | tan(<exp>) |
 *      tan⁻¹(<exp>) | log₁₀(<exp>) | ln(<exp>) | !(<exp>) | √(<exp>) | ∛(<exp>) |
 *      nPr(<exp>,<exp>) | nCr(<exp>,<exp>) | power(<exp>,<exp>) | %() | π | e | rand |
 *      w | x | y | z | ɑ | β | ɣ | Δ | DOUBLE | (<exp>)
 */

public class ExpressionParser
{

    Tokenizer _tokenizer;

    public ExpressionParser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    public Exp parseExp()
    {
        Exp term = parseTerm();
        if (_tokenizer.hasNext())
        {
            if (_tokenizer.current().type() == Token.Type.SUBTRACT)
            {
                _tokenizer.next();
                Exp exp = parseExp();
                return new SubtractExp(term, exp);
            }
            if (_tokenizer.current().type() == Token.Type.ADD)
            {
                _tokenizer.next();
                Exp exp = parseExp();
                return new AddExp(term, exp);
            }
        }
        return term;
    }

    public Exp parseTerm()
    {
        Exp oper = parseOperation();
        if (_tokenizer.hasNext())
        {
            if (_tokenizer.current().type() == Token.Type.MULTIPLY)
            {
                _tokenizer.next();
                Exp term = parseTerm();
                return new MultiplyExp(oper, term);
            }
            if (_tokenizer.current().type() == Token.Type.DIVIDE)
            {
                _tokenizer.next();
                Exp term = parseTerm();
                return new DivideExp(oper, term);
            }
        }
        return oper;
    }

    public Exp parseOperation()
    {
        if(_tokenizer.current().type() != Token.Type.DOUBLE)
        {
            if(_tokenizer.hasNext())
            {
                switch(_tokenizer.current().type().args())
                {
                    case 0: return parseNoArgOperation(_tokenizer.current().token());
                    case 1: return parseSingleArgOperation(_tokenizer.current().token());
                    default: return parseDoubleArgOperation(_tokenizer.current().token());
                }
            }
        }
        DoubleExp literal = new DoubleExp(Double.parseDouble(_tokenizer.current().token()));
        _tokenizer.next();
        return literal;
    }

    public Exp parseNoArgOperation(String token)
    {
        _tokenizer.next();
        switch(token)
        {
            case "#R": return new RandomNumberExp();
            case "#PI": return new PiExp();
            case "#E": return new EExp();
        }
        return null;
    }

    public Exp parseSingleArgOperation(String token)
    {
        _tokenizer.next();
        _tokenizer.next();
        Exp exp = parseExp();
        _tokenizer.next();
        switch(token)
        {
            case "sin": return new SineExp(exp);
            case "sin-1": return new ArcSineExp(exp);
            case "cos": return new CosineExp(exp);
            case "cos-1": return new ArcCosineExp(exp);
            case "tan": return new TangentExp(exp);
            case "tan-1": return new ArcTangentExp(exp);
            case "log": return new LogNaturalExp(exp);
            case "log10": return new LogTenExp(exp);
            case "fac": return new FactorialExp(exp);
            case "sqrt": return new SquareRootExp(exp);
            default: return new UnknownVariableExp(exp.show().charAt(0)); //Unknown variable is returned as default as you can't guess which character will be used for the variable
        }
    }

    public Exp parseDoubleArgOperation(String token)
    {
        _tokenizer.next();
        _tokenizer.next();
        Exp expOne = parseExp();
        _tokenizer.next();
        Exp expTwo = parseExp();
        _tokenizer.next();
        switch(token)
        {
            case "pwr": return new PowerExp(expOne, expTwo);
            case "perm": return new PermutationExp(expOne, expTwo);
            case "comb": return new CombinationExp(expOne, expTwo);
        }
        return null;
    }
}
