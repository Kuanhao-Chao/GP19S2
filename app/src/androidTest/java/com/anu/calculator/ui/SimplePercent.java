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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SimplePercent {
    /**
     * Tests are first generated through `android expresso` and then do further modification.
     *
     * @author: Howard Chao (u7022787)
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simplePercent() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.percentage), withText("%"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2)));
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
        editText.check(matches(withText("0.1")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton5.perform(scrollTo(), click());


        ViewInteraction editTex2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editTex2.check(matches(withText("")));
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
