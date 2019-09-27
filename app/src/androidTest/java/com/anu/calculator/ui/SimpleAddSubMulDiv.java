package com.anu.calculator.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.anu.calculator.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SimpleAddSubMulDiv {
    /**
     * Tests are first generated through `android expresso` and then do further modification.
     *
     * @author: Howard Chao (u7022787)
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simpleAddition() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.addition), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction editText = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText.check(matches(withText("2")));


        ViewInteraction AC1 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        AC1.perform(scrollTo(), click());

        ViewInteraction afterACEditext = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        afterACEditext.check(matches(withText("")));







        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.subtraction), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                3)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.dgt_3), withText("3"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                0)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton8.perform(scrollTo(), click());


        ViewInteraction editText2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText2.check(matches(withText("-2")));


        ViewInteraction AC2 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        AC2.perform(scrollTo(), click());

        ViewInteraction afterACEditext2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        afterACEditext2.check(matches(withText("")));




        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.subtraction), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.dgt_4), withText("4"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.multiply), withText("×"),
                        childAtPosition(
                                allOf(withId(R.id.row_2),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.subtraction), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                3)));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.dgt_8), withText("8"),
                        childAtPosition(
                                allOf(withId(R.id.row_2),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton14.perform(scrollTo(), click());


        ViewInteraction editText3 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText3.check(matches(withText("32")));


        ViewInteraction AC3 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        AC3.perform(scrollTo(), click());

        ViewInteraction afterACEditext3 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        afterACEditext3.check(matches(withText("")));





        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.dgt_5), withText("5"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1)));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.divide), withText("÷"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton17.perform(scrollTo(), click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton18.perform(scrollTo(), click());


        ViewInteraction editText4 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText4.check(matches(withText("2.5")));

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.divide), withText("÷"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3)));
        appCompatButton19.perform(scrollTo(), click());

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatButton20.perform(scrollTo(), click());

        ViewInteraction appCompatButton21 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton21.perform(scrollTo(), click());

        ViewInteraction editText5 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText5.check(matches(withText("Cannot divide by zero")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
