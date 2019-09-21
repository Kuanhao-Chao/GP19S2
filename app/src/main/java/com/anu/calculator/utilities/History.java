package com.anu.calculator.utilities;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.FunctionLoopException;
import com.anu.calculator.expressions.DoubleExpression;
import com.anu.calculator.parsers.ExpressionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class takes a history (Stack<Expression>) and processes it
 * so that all of the expressions within it are assigned the correct
 * values.
 *
 * @author Samuel Brookes (u5380100)
 * @modified Michael Betterton (u6797866)
 *  - added load() and save() methods
 */

public class History {

    private final String EQUALS = "=";
    private final String TAG = "HISTORY";
    private Boolean degrees;

    private HashMap<Character, HistoryItem> history;
    private ArrayList<String> strippedHistory;
    private LinkedList<String> orderedHistory;

    private History(HashMap<Character, HistoryItem> history, Boolean degrees)
    {
        this.degrees = degrees;
        this.history = history;
    }

    /**
     * Returns an empty instance of a History object
     * Primarily used for testing.
     *
     * @return History (empty)
     */
    public static History getInstance(Boolean degrees)
    {
        return new History(new HashMap<Character, HistoryItem>(0), degrees);
    }

    //FIXME: load() method
    public static History load(Boolean degrees)
    {
        //FIXME: Need to assign degrees
        return new History(new HashMap<Character, HistoryItem>(0), degrees);
    }

    //FIXME: save() method
    public void save()
    {

    }

    /**
     * Takes an expression and appends it to the end of
     * @param expression
     * @throws ParserException
     */
    public void put(Expression expression) throws ParserException
    {
        history.put('-', new HistoryItem(false, expression));
        stripHistory();
        orderHistory();
        processHistory();
    }

    /**
     * This method gets the first instance of each unique variable in the
     * history Stack and puts it unordered into strippedHistory.
     */
    private void stripHistory()
    {
        strippedHistory = new ArrayList<>(0);

        String raw;
        HashSet<String> storedVariables = new HashSet<>(0);
        for(Map.Entry<Character, HistoryItem> mapEntry : history.entrySet())
        {
            raw = mapEntry.getValue().getExpression().show();
            if(raw.contains(EQUALS))
            {
                String variable = raw.split(EQUALS)[0].trim();
                if(!storedVariables.contains(variable))
                {
                    storedVariables.add(variable);
                    strippedHistory.add(raw);
                }
            }
        }
    }

    /**
     * This method reorders the history stack into orderedHistory to ensure
     * that when processedHistory is built, there is no instance in which
     * a variable has not already been assigned a value when it is being parsed.
     * It checks for the occurrence of loops and throws an Exception if they are
     * detected.
     *
     * @throws ParserException
     */
    private void orderHistory() throws ParserException
    {
        orderedHistory = new LinkedList<>();
        HashSet<String> definedVariables = new HashSet<>(0);
        Tokenizer tokenizer;

        //Do a quick sweep to search for DoubleExpressions as these immediately define a var
        String variable, expression;
        for(String raw : strippedHistory)
        {
            variable = raw.split(EQUALS)[0].trim();
            expression = raw.split(EQUALS)[1].trim();
            if(new ExpressionParser().parseHistory(expression, true,null) instanceof DoubleExpression)
            {
                definedVariables.add(variable);
                orderedHistory.add(raw);
            }
        }

        //Do another sweep to add more complex expressions to orderedHistory
        boolean allDefined = false;
        int prevSize = orderedHistory.size();
        while(!allDefined)
        {
            allDefined = true;
            for(String raw : strippedHistory)
            {
                variable = raw.split(EQUALS)[0].trim();
                expression = raw.split(EQUALS)[1].trim();
                if(!definedVariables.contains(variable))
                { //this variable has not yet been 'defined'
                    //check the expression for unknown variables and see if they have been 'defined'
                    tokenizer = new Tokenizer(expression);
                    boolean expressionDefined = true;
                    while(tokenizer.hasNext())
                    {
                        if(tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE)
                        {
                            if(!definedVariables.contains(tokenizer.current().token()))
                            {
                                expressionDefined = false;
                            }
                        }
                        tokenizer.next();
                    }

                    if(expressionDefined)
                    { //all of the variables in the expression are 'defined'
                        definedVariables.add(variable);
                        orderedHistory.add(raw);
                    }
                    else
                    { //there are still undefined variables in the expression - continue loop
                        allDefined = false;
                    }
                }
            }

            /*
                if there are still undefined expression in strippedHistory and
                no expression was added to orderedHistory after this iteration,
                it means that there is an expression which cannot be defined
                (i.e. a loop).
             */
            if(!allDefined && prevSize == orderedHistory.size())
                throw new FunctionLoopException(TAG, "Calculator cannot solve this function.");
            else
                prevSize = orderedHistory.size();
        }
    }

    /**
     * If the reordering method is successful, this method should be able to go through
     * orderedHistory (from end to beginning) and successfully parse each expression
     * as each variable should have been previously given a value.
     * Once it has parsed each value - it stores them into processedHistory.
     */
    private void processHistory() throws ParserException
    {
        history = new HashMap<>(0);

        String raw, variable, expression;
        while(!orderedHistory.isEmpty())
        {
            raw = orderedHistory.remove(0);
            variable = raw.split(EQUALS)[0].trim();
            expression = raw.split(EQUALS)[1].trim();

            Expression exp = new ExpressionParser().parseHistory(expression, degrees, history);
            history.put(variable.charAt(0), new HistoryItem(isGraphable(exp), exp));
        }
    }

    /**
     * Checks whether an expression is able to be graphed.
     * An expression is able to be graphed if it has less than
     * one unknown variable.
     *
     * @param expression
     * @return
     */
    private boolean isGraphable(Expression expression)
    {
        String expStr = expression.show();
        Tokenizer checkExp = new Tokenizer(expStr);
        int unkVarCount = 0;
        while(checkExp.hasNext())
        {
            if(checkExp.current().type() == Token.Type.UNKNOWN_VARIABLE)
            {
                unkVarCount++;
            }
        }

        return unkVarCount < 2;
    }

    public boolean hasVariable(Character variable)
    {
        return history.containsKey(variable);
    }

    public Expression getExpression(Character variable)
    {
        return history.get(variable).getExpression();
    }
}
