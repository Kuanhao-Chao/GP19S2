package com.anu.calculator.utilities;

import com.anu.calculator.Expression;
import com.anu.calculator.ParserException;
import com.anu.calculator.exceptions.FunctionLoopException;
import com.anu.calculator.expressions.DoubleExpression;
import com.anu.calculator.parsers.ExpressionParser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * This class takes a history (Stack<Expression>) and processes it
 * so that all of the expressions within it are assigned the correct
 * values.
 *
 * @author Samuel Brookes (u5380100)
 * @modified Michael Betterton (u6797866)
 * - added load() and save() methods
 */

public class History implements Serializable {
    private static final long serialVersionUID = 21071992L;
    private static final String fileName = "history.dat";

    private final String EQUALS = "=";
    private final String TAG = "HISTORY";

    private Stack<HistoryItem> savedHistory;
    private ArrayList<String> strippedHistory;
    private LinkedList<String> orderedHistory;
    private HashMap<Character, HistoryItem> processedHistory;

    private History(Stack<HistoryItem> history) {
        this.savedHistory = history;
    }

    /**
     * Returns an empty instance of a History object, can be used for testing or in the load method
     * when no saved history file exists.
     *
     * @return History (empty)
     * @author: Sam Brookes
     */
    public static History getInstance() {
        return new History(new Stack<HistoryItem>());
    }

    /**
     * Loads a history class from the file system and returns it to the application. The file is
     * saved as a serialized version of this class and decoded as such.
     * <p>
     * If the file doesn't exist in the file system, a empty history object is returned.
     *
     * @return A History class loaded from file or empty if none exist.
     * @author: Michael Betterton (u6797866)
     */
    public static History load() {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            History history = (History) objectInputStream.readObject();
            objectInputStream.close();
            return history;
        } catch (IOException | ClassNotFoundException e) {
            return getInstance();
        }
    }

    /**
     * Takes this (class) and saves it to the file system under 'fileName'.dat in the internal
     * storage of the device. The file format is a serialized version of this class and therefore it
     * should not be interrogated outside of the save and load methods.
     *
     * @author: Michael Betterton (u6797866)
     */
    public void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes an expression and appends it to the end of
     *
     * @param expression
     * @throws ParserException
     */
    public void put(Expression expression, Boolean degrees) throws ParserException {
        savedHistory.push(new HistoryItem(false, expression));
        stripHistory();
        orderHistory();
        processHistory(degrees);
        saveHistory();
    }

    /**
     * This method gets the first instance of each unique variable in the
     * history Stack and puts it unordered into strippedHistory.
     */
    private void stripHistory() {
        strippedHistory = new ArrayList<>(0);

        String raw;
        HashSet<String> storedVariables = new HashSet<>(0);
        while(!savedHistory.empty())
        {
            raw = savedHistory.pop().getExpression().show();
            if (raw.contains(EQUALS)) {
                String variable = raw.split(EQUALS)[0].trim();
                if (!storedVariables.contains(variable)) {
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
     * @throws ParserException A exception was encountered when parsing the history.
     */
    private void orderHistory() throws ParserException {
        orderedHistory = new LinkedList<>();
        HashSet<String> definedVariables = new HashSet<>(0);
        Tokenizer tokenizer;

        //Do a quick sweep to search for DoubleExpressions as these immediately define a var
        String variable, expression;
        for (String raw : strippedHistory) {
            variable = raw.split(EQUALS)[0].trim();
            expression = raw.split(EQUALS)[1].trim();
            if (new ExpressionParser().parseHistory(expression, true, null) instanceof DoubleExpression) {
                definedVariables.add(variable);
                orderedHistory.add(raw);
            }
        }

        //Do another sweep to add more complex expressions to orderedHistory
        boolean allDefined = false;
        int prevSize = orderedHistory.size();
        while (!allDefined) {
            allDefined = true;
            for (String raw : strippedHistory) {
                variable = raw.split(EQUALS)[0].trim();
                expression = raw.split(EQUALS)[1].trim();
                if (!definedVariables.contains(variable)) { //this variable has not yet been 'defined'
                    //check the expression for unknown variables and see if they have been 'defined'
                    tokenizer = new Tokenizer(expression);
                    boolean expressionDefined = true;
                    while (tokenizer.hasNext()) {
                        if (tokenizer.current().type() == Token.Type.UNKNOWN_VARIABLE) {
                            if (!definedVariables.contains(tokenizer.current().token())) {
                                expressionDefined = false;
                                break;
                            }
                        }
                        tokenizer.next();
                    }

                    if (expressionDefined) { //all of the variables in the expression are 'defined'
                        definedVariables.add(variable);
                        orderedHistory.add(raw);
                    } else { //there are still undefined variables in the expression - continue loop
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
            if (!allDefined && prevSize == orderedHistory.size())
                throw new FunctionLoopException(TAG, "An error occurred.");
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
    private void processHistory(Boolean degrees) throws ParserException {
        processedHistory = new HashMap<>(0);

        String raw;
        while (!orderedHistory.isEmpty()) {
            raw = orderedHistory.remove(0);
            Character variable = raw.split(EQUALS)[0].trim().charAt(0);
            Expression exp = new ExpressionParser().parseHistory(raw, degrees, processedHistory);
            processedHistory.put(variable, new HistoryItem(isGraphable(exp), exp));
        }
    }

    private void saveHistory()
    {
        savedHistory = new Stack<>();
        for(Map.Entry<Character, HistoryItem> mapEntry : processedHistory.entrySet())
        {
            savedHistory.push(mapEntry.getValue());
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
    private boolean isGraphable(Expression expression) {
        String expStr = expression.show();
        Tokenizer checkExp = new Tokenizer(expStr);
        int unkVarCount = 0;
        while (checkExp.hasNext()) {
            if (checkExp.current().type() == Token.Type.UNKNOWN_VARIABLE) {
                unkVarCount++;
            }
            checkExp.next();
        }

        return unkVarCount < 2;
    }

    public boolean hasVariable(Character variable) {
        return processedHistory != null && processedHistory.containsKey(variable);
    }

    public Expression getExpression(Character variable){
        return processedHistory.get(variable).getExpression();
    }
}
