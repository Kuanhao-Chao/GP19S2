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
//        System.out.println("Inside parseExp");
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
//        System.out.println("Finish!!");
        return term;
    }

    public Exp parseTerm()
    {
//        System.out.println("Inside parseTerm");

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
//        System.out.println("\n\n_tokenizer.hasNext(): " + _tokenizer.hasNext());
//        System.out.println("\n\n_tokenizer.current(): " + _tokenizer.current().type());
//        System.out.println("_tokenizer.current(): " + _tokenizer.current().token());
//        System.out.println("Inside parseOperation");
//        System.out.println("current token: " + _tokenizer.current().token());

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
        } else if (_tokenizer.current().type() == Token.Type.DOUBLE) {

        }
        DoubleExp literal = new DoubleExp(Double.parseDouble(_tokenizer.current().token()));
        _tokenizer.next();
        return literal;
    }

    public Exp parseNoArgOperation(String token)
    {
//        System.out.println("Inside parseNoArgOperation");
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
        /**
         * Modified time: 2019-09-04
         * Change parsing condition for unknown variable
         * Modifier: Howard Chao
         */
        if (_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
            UnknownVariableExp unknownLiteral = new UnknownVariableExp(_tokenizer.current().token().charAt(0));
            _tokenizer.next();
            if (_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
//                System.out.println(_tokenizer.current().token());
                _tokenizer.next();
                if (_tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
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
                        default:
                            UnknownVariableExp unknow_literal_2 = new UnknownVariableExp(exp.show().charAt(0));
                            return unknow_literal_2; //Unknown variable is returned as default as you can't guess which character will be used for the variable
                    }
                }
            } else {
                if (_tokenizer.hasNext() && _tokenizer.current().type() != Token.Type.UNKNOWN_VARIABLE) {
                    return unknownLiteral; //Unknown variable is returned as default as you can't guess which character will be used for the variable
                } else if (!_tokenizer.hasNext()){
                    return unknownLiteral; //Unknown variable is returned as default as you can't guess which character will be used for the variable
                }
            }
        }
        /**
         * End modification
         */
        return null;
    }

    public Exp parseDoubleArgOperation(String token)
    {
//        System.out.println("Inside parseDoubleArgOperation");

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
