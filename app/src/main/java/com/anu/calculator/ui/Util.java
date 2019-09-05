package com.anu.calculator.ui;

import android.widget.EditText;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * This class contains common utility functions for the user interface. All methods in this class
 * should be package private & static.
 *
 * @author: Michael Betterton (u6797866)
 */
class Util {
    private static Map<Integer, Integer> FONT_SIZES = new HashMap<Integer, Integer>() {{
        put(-1, 18);
        put(0, 36);
        put(1, 30);
        put(2, 30);
        put(3, 30);
        put(4, 24);
        put(5, 24);
        put(6, 24);
    }};


    /**
     * Returns a double nicely formatted without unnecessary trailing spaces.
     *
     * @author: Michael Betterton (u6797866)
     * @param d The double to return nicely formatted
     * @return The nicely formatted double as a string.
     */
    static String fmt(double d) {
        if (d == (long) d)
            return String.format(Locale.ENGLISH, "%d", (long) d);
        else
            return String.format("%s", d);
    }

    /**
     * Set the text size to a value based on the length of the current inputs. Default it to the
     * -1 value when no font size is set for that length (exceptionally long strings
     *
     * @author: Michael Betterton (u6797866)
     * @param editText The EditText textArea to update the font-size of.
     */
    private static void setFontSize(EditText editText) {
        // Set the text size to a value based on the length of the current inputs. Default it to the
        // -1 value when no font size is set for that length (exceptionally long strings
        int length = editText.length() / 20;
        Integer font_size = FONT_SIZES.get(length);
        if (font_size != null) {
            editText.setTextSize(font_size);
        } else
            editText.setTextSize(18);
    }

    /**
     * Resets the provided EditText TextArea to its default properties. This method should be
     * invoked whenever functions like clear and equals are executed.
     *
     * @author: Michael Betterton (u6797866)
     * @param textArea The textArea to reset properties on.
     */
    static void resetTextArea(EditText textArea) {
        // Set the font-size back to default
        textArea.setTextSize(FONT_SIZES.get(0));
        // Empty out all text
        textArea.setText("");
    }

    /**
     * Inserts the provided textToAdd into the selected region of an editText area. If the user has
     * selected an area of text (multiple characters), that text is replaced with the new text.
     *
     * @author: Michael Betterton (u6797866)
     * @param editText  An Edit Text Area to add text to.
     * @param textToAdd A String to insert into the Edit Text Area.
     */
    static void addText(@org.jetbrains.annotations.NotNull EditText editText, String textToAdd) {
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        editText.getText().replace(Math.min(start, end), Math.max(start, end),
                textToAdd, 0, textToAdd.length());

        // Update the font size
        setFontSize(editText);
    }

}
