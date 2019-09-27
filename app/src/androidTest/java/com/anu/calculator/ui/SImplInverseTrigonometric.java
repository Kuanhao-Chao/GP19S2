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
public class SImplInverseTrigonometric {
    /**
     * Tests are first generated through `android expresso` and then do further modification.
     *
     * @author: Howard Chao (u7022787)
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sImplInverseTrigonometric() {
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.arc_sin), withText("sin⁻¹"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                0)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction editText = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText.check(matches(withText("1.5707963267948966")));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.arc_cos), withText("cos⁻¹"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction tabView4 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView4.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText2.check(matches(withText("0")));

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction tabView9 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView9.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.arc_tan), withText("tan⁻¹"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                2)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction tabView10 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView10.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.delete), withText("DEL"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1)));
        appCompatButton14.perform(scrollTo(), click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatButton15.perform(scrollTo(), click());

        ViewInteraction tabView11 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView11.perform(click());

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.rbra), withText(")"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatButton16.perform(scrollTo(), click());

        ViewInteraction tabView12 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView12.perform(click());

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton17.perform(scrollTo(), click());

        ViewInteraction editText3 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText3.check(matches(withText("0")));
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
