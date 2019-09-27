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
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SimplePerCom {
    /**
     * Tests are first generated through `android expresso` and then do further modification.
     *
     * @author: Howard Chao (u7022787)
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simplePerCom() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dgt_4), withText("4"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.permutation), withText("nPr"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView2.perform(click());

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
        editText.check(matches(withText("12")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.dgt_4), withText("4"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.combinations), withText("nCr"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText2.check(matches(withText("6")));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction tabView5 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView5.perform(click());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.sqrt), withText("√"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction tabView6 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView6.perform(click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.dgt_9), withText("9"),
                        childAtPosition(
                                allOf(withId(R.id.row_2),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0)));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction editText3 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText3.check(matches(withText("3")));

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton14.perform(scrollTo(), click());

        ViewInteraction tabView7 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView7.perform(click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.cubed_root), withText("∛"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                3)));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction tabView8 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView8.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.dgt_7), withText("7"),
                        childAtPosition(
                                allOf(withId(R.id.row_2),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                2)));
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
        editText4.check(matches(withText("3")));

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton19.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.calculation_textarea),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.calculation_textarea),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.calculation_textarea),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.calculation_textarea),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(click());
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
