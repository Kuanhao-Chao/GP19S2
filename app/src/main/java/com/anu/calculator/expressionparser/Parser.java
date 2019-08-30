package com.anu.calculator.expressionparser;

/**
 * Name: Parser.java
 * <p>
 * The main objective of this class is to implement a simple parser.
 * It should be able to parser the following grammar rule:
 * <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <factor> ::= <unsigned integer> | ( <exp> )
 *
 * @author: Michael Betterton
 * @Uid: u6797866
 * @Date: 15 Aug 2019
 */

public class Parser {

    Tokenizer _tokenizer;

    public Parser(Tokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    /*
    <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
     */
    public Exp parseExp() {
        Exp term = parseTerm();
        if (_tokenizer.hasNext()) {
            if (_tokenizer.current().type() == Token.Type.SUB) {
                _tokenizer.next();
                Exp exp = parseExp();
                return new SubExp(term, exp);
            }
            if (_tokenizer.current().type() == Token.Type.ADD) {
                _tokenizer.next();
                Exp exp = parseExp();
                return new AddExp(term, exp);
            }
        }
        return term;
    }


    /*
    <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
     */
    public Exp parseTerm() {
        Exp factor = parseFactor();
        if (_tokenizer.hasNext()) {
            if (_tokenizer.current().type() == Token.Type.MUL) {
                _tokenizer.next();
                Exp term = parseTerm();
                return new MultExp(factor, term);
            }
            if (_tokenizer.current().type() == Token.Type.DIV) {
                _tokenizer.next();
                Exp term = parseTerm();
                return new DivExp(factor, term);
            }
        }
        return factor;
        }

    /*
    <factor> ::= <unsigned integer> | ( <exp> )
     */
    public Exp parseFactor() {
        Exp expr = null;
        if (_tokenizer.current().type() == Token.Type.INT) {
            Exp factor = new IntExp(Integer.parseInt(_tokenizer.current().token()));
            if (_tokenizer.hasNext()){
                _tokenizer.next();
            }
            return factor;
        }
        if (_tokenizer.current().type() == Token.Type.LBRA){
            _tokenizer.next(); // Iterate over the bracket
            expr = parseExp();
        }
        if (_tokenizer.current().type() == Token.Type.RBRA){
            _tokenizer.next(); // Iterate over the bracket
            return expr;
        }
        else {
            expr = parseExp();
            return expr;
        }
    }
}
