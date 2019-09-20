package com.anu.calculator.utilities;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.FunctionLoopException;
import com.anu.calculator.expressions.DoubleExpression;
import com.anu.calculator.parsers.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * This class takes a history (Stack<Expression>) and processes it
 * so that all of the expressions within it are assigned the correct
 * values.
 *
 * @author Samuel Brookes (u5380100)
 */

public class History {

    private final String EQUALS = "=";
    private final String TAG = "HISTORY";

    private Stack<Expression> rawHistory;
    private ArrayList<String> strippedHistory;
    private LinkedList<String> orderedHistory;
    private HashMap<Character, Expression> processedHistory;

    public History(Boolean degrees, Integer precision, Stack<Expression> history) throws ParserException
    {
        rawHistory = history;
        stripHistory();
        reorderHistory();
        processHistory();
    }

    /**
     * This method gets the first instance of each unique variable in the
     * history Stack and puts it unordered into strippedHistory.
     */
    @SuppressWarnings("unchecked")
    private void stripHistory()
    {
        strippedHistory = new ArrayList<>(0);

        String raw;
        HashSet<String> storedVariables = new HashSet<>(0);
        Stack<Expression> history = (Stack<Expression>) rawHistory.clone();
        while(!history.empty())
        {
            raw = history.pop().show();
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
    private void reorderHistory() throws ParserException
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
            if(new Parser().parse(expression) instanceof DoubleExpression)
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
    private void processHistory()
    {
        processedHistory = new HashMap<>(0);

        String raw, variable, expression;
        for(int i=0; i<orderedHistory.size(); i++)
        {
            raw = orderedHistory.remove(i);
            variable = raw.split(EQUALS)[0].trim();
            expression = raw.split(EQUALS)[1].trim();

        }
    }

    public boolean hasVariable(Character variable)
    {
        return processedHistory.containsKey(variable);
    }

    public Expression getExpression(Character variable)
    {
        return processedHistory.get(variable);
    }
}
