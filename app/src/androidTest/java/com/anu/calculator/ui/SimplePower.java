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
public class SimplePower {
    /**
     * Tests are first generated through `android expresso` and then do further modification.
     *
     * @author: Howard Chao (u7022787)
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void simplePower() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
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
                allOf(withId(R.id.power), withText("power"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2)));
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


        ViewInteraction editTex = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editTex.check(matches(withText("4")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton5.perform(scrollTo(), click());


        ViewInteraction editText2 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText2.check(matches(withText("")));

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
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
                allOf(withId(R.id.power), withText("power"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2)));
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
                allOf(withId(R.id.dgt_4), withText("4"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                2)));
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


        ViewInteraction editText3 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText3.check(matches(withText("0")));

        ViewInteraction tabView5 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView5.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton10.perform(scrollTo(), click());


        ViewInteraction editText4 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText4.check(matches(withText("")));


        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.subtraction), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                3)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton12.perform(scrollTo(), click());

        ViewInteraction tabView6 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView6.perform(click());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.power), withText("power"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2)));
        appCompatButton13.perform(scrollTo(), click());

        ViewInteraction tabView7 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView7.perform(click());

        ViewInteraction appCompatButton14 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton14.perform(scrollTo(), click());

        ViewInteraction appCompatButton15 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton15.perform(scrollTo(), click());


        ViewInteraction editText5 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText5.check(matches(withText("1")));

        ViewInteraction appCompatButton16 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton16.perform(scrollTo(), click());


        ViewInteraction editText6 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText6.check(matches(withText("")));

        ViewInteraction appCompatButton17 = onView(
                allOf(withId(R.id.subtraction), withText("-"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                3)));
        appCompatButton17.perform(scrollTo(), click());

        ViewInteraction appCompatButton18 = onView(
                allOf(withId(R.id.dgt_1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                2)));
        appCompatButton18.perform(scrollTo(), click());

        ViewInteraction tabView8 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView8.perform(click());

        ViewInteraction appCompatButton19 = onView(
                allOf(withId(R.id.power), withText("power"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2)));
        appCompatButton19.perform(scrollTo(), click());

        ViewInteraction appCompatButton20 = onView(
                allOf(withId(R.id.lbra), withText("("),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                0)));
        appCompatButton20.perform(scrollTo(), click());

        ViewInteraction tabView9 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView9.perform(click());

        ViewInteraction appCompatButton21 = onView(
                allOf(withId(R.id.dgt_0), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                1)));
        appCompatButton21.perform(scrollTo(), click());

        ViewInteraction appCompatButton22 = onView(
                allOf(withId(R.id.decimal), withText("."),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                2)));
        appCompatButton22.perform(scrollTo(), click());

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.dgt_5), withText("5"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1)));
        appCompatButton23.perform(scrollTo(), click());

        ViewInteraction tabView10 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView10.perform(click());

        ViewInteraction appCompatButton24 = onView(
                allOf(withId(R.id.rbra), withText(")"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatButton24.perform(scrollTo(), click());

        ViewInteraction tabView11 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView11.perform(click());

        ViewInteraction appCompatButton25 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton25.perform(scrollTo(), click());

        ViewInteraction editText7 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText7.check(matches(withText("NaN")));

        ViewInteraction appCompatButton26 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton26.perform(scrollTo(), click());


        ViewInteraction editText8 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText8.check(matches(withText("")));

        ViewInteraction appCompatButton27 = onView(
                allOf(withId(R.id.dgt_2), withText("2"),
                        childAtPosition(
                                allOf(withId(R.id.row_4),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                3)),
                                1)));
        appCompatButton27.perform(scrollTo(), click());

        ViewInteraction tabView12 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView12.perform(click());

        ViewInteraction appCompatButton28 = onView(
                allOf(withId(R.id.squared), withText("x²"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                3)));
        appCompatButton28.perform(scrollTo(), click());

        ViewInteraction tabView13 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView13.perform(click());

        ViewInteraction appCompatButton29 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton29.perform(scrollTo(), click());


        ViewInteraction editText = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText.check(matches(withText("4")));

        ViewInteraction appCompatButton30 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton30.perform(scrollTo(), click());

        ViewInteraction tabView14 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView14.perform(click());

        ViewInteraction tabView15 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView15.perform(click());

        ViewInteraction appCompatButton31 = onView(
                allOf(withId(R.id.dgt_5), withText("5"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                1)));
        appCompatButton31.perform(scrollTo(), click());


        ViewInteraction tabView16 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView16.perform(click());

        ViewInteraction appCompatButton32 = onView(
                allOf(withId(R.id.cubed), withText("x³"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3)));
        appCompatButton32.perform(scrollTo(), click());

        ViewInteraction tabView17 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView17.perform(click());

        ViewInteraction appCompatButton33 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton33.perform(scrollTo(), click());


        ViewInteraction editText9 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText9.check(matches(withText("125")));

        ViewInteraction appCompatButton34 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton34.perform(scrollTo(), click());

        ViewInteraction appCompatButton35 = onView(
                allOf(withId(R.id.dgt_6), withText("6"),
                        childAtPosition(
                                allOf(withId(R.id.row_3),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                0)));
        appCompatButton35.perform(scrollTo(), click());

        ViewInteraction tabView18 = onView(
                allOf(withContentDescription("Func"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView18.perform(click());

        ViewInteraction appCompatButton36 = onView(
                allOf(withId(R.id.factorial), withText("!"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                3)));
        appCompatButton36.perform(scrollTo(), click());

        ViewInteraction tabView19 = onView(
                allOf(withContentDescription("Main"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.operations_tabs),
                                        0),
                                0),
                        isDisplayed()));
        tabView19.perform(click());

        ViewInteraction appCompatButton37 = onView(
                allOf(withId(R.id.evaluate), withText("Solve"),
                        childAtPosition(
                                allOf(withId(R.id.row_5),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                4)),
                                3)));
        appCompatButton37.perform(scrollTo(), click());


        ViewInteraction editText10 = onView(allOf(withId(R.id.calculation_textarea),
                isDescendantOfA(withId(R.id.linearLayout)))).check(matches(isDisplayed()));
        editText10.check(matches(withText("720")));

        ViewInteraction appCompatButton38 = onView(
                allOf(withId(R.id.all_clear), withText("AC"),
                        childAtPosition(
                                allOf(withId(R.id.row_1),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0)));
        appCompatButton38.perform(scrollTo(), click());
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
