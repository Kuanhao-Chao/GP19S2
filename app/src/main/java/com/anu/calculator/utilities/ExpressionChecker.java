package com.anu.calculator.utilities;

import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.MathematicalSyntaxException;
import com.anu.calculator.exceptions.NothingEnteredException;

import java.util.Stack;

public class ExpressionChecker {

    private static final String TAG = "EXPRESSION_CHECKER";
    private String expression;

    public ExpressionChecker(String expression)
    {
        this.expression = expression;
    }

    /**
     * checkExpression: conducts a preliminary check of the expression string entered by the user
     * prior to being passed to Parser.parse(). This is to catch any easy-to-detect errors to save
     * time - rather than trying to detect them within a parsed expression.
     */
    public void checkExpression() throws ParserException
    {

        //check whether the user has entered something
        if(expression.equals(""))
            throw new NothingEnteredException(TAG, "");

        //check for correct bracket nesting
        checkBrackets();

        //if this is a function, check that the function syntax is correct
        if(expression.contains("=")) checkFunctions();
    }

    private void checkBrackets() throws ParserException
    {
        Stack<Token.Type> brackStack = new Stack<>();
        Tokenizer tokenizer = new Tokenizer(expression);
        while(tokenizer.hasNext())
        {//Remember: tokenizer moves right to left
            if(isBracket(tokenizer.current().type()))
            {//the current token is a bracket
                if(isLeft(tokenizer.current().type()))
                {
                    if(brackStack.empty() ||
                            !isPairTo(brackStack.pop(), tokenizer.current().type()))
                    {
                        throw new MathematicalSyntaxException(TAG, "Syntax error: incorrect " +
                                "nesting of brackets");
                    }
                }
                else if(isRight(tokenizer.current().type()))
                {
                    brackStack.push(tokenizer.current().type());
                }
            }
            tokenizer.next();
        }
        if(!brackStack.empty())
            throw new MathematicalSyntaxException(TAG, "Syntax error: incorrect nesting of brackets");
    }

    private boolean isBracket(Token.Type token)
    {
        return isLeft(token) || isRight(token);
    }

    private boolean isLeft(Token.Type bracket)
    {
        return bracket == Token.Type.LEFT_BRACKET ||
                bracket == Token.Type.LEFT_BRACE ||
                bracket == Token.Type.LEFT_PARENTHESIS;
    }

    private boolean isRight(Token.Type bracket)
    {
        return bracket == Token.Type.RIGHT_BRACKET ||
                bracket == Token.Type.RIGHT_BRACE ||
                bracket == Token.Type.RIGHT_PARENTHESIS;
    }

    private boolean isPairTo(Token.Type right, Token.Type left)
    {
        switch(right)
        {
            case RIGHT_BRACE:
                return left == Token.Type.LEFT_BRACE;
            case RIGHT_BRACKET:
                return left == Token.Type.LEFT_BRACKET;
            case RIGHT_PARENTHESIS:
                return left == Token.Type.LEFT_PARENTHESIS;
            default:
                return false;
        }
    }

    private void checkFunctions() throws ParserException
    {
        //test that there is only one equals sign
        if(expression.indexOf('=') != expression.lastIndexOf('='))
            throw new MathematicalSyntaxException(TAG, "Syntax error: More than one equals sign.");

        //test that the left-hand side has only one variable and that it is an unknown var
        Token leftHandSide = new Tokenizer(expression.split("=")[0].trim()).current();
        if(leftHandSide == null)
            throw new MathematicalSyntaxException(TAG, "Syntax error: There must be a variable on the left side of the equation.");
        else if(leftHandSide.type() != Token.Type.UNKNOWN_VARIABLE)
            throw new MathematicalSyntaxException(TAG, "The calculator is unable to solve this type of equation.");

        //test that an unknown variable does not occur on either side of the equals sign
        char variable = expression.split("=")[0].trim().charAt(0);
        Tokenizer varCheck = new Tokenizer(expression.split("=")[1].trim());
        while(varCheck.hasNext())
        {
            if(varCheck.current().type() == Token.Type.UNKNOWN_VARIABLE && varCheck.current().token().charAt(0) == variable)
                throw new MathematicalSyntaxException(TAG, "The calculator is unable to solve this type of equation.");
            varCheck.next();
        }
    }
}
