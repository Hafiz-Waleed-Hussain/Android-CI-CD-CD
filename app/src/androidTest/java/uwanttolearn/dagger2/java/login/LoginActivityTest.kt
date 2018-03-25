package uwanttolearn.dagger2.java.login


import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import uwanttolearn.dagger2.R

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView
import org.hamcrest.Matchers.*


@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun `when login click with empty username should  show username empty Toast`(){
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(click())

        onView(withText(R.string.enter_username)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    //    @Test
    //    public void loginActivityTest() {
    //        ViewInteraction appCompatButton = onView(
    //                allOf(withId(R.id.button), withText("Login"),
    //                        childAtPosition(
    //                                childAtPosition(
    //                                        withId(android.R.id.content),
    //                                        0),
    //                                2),
    //                        isDisplayed()));
    //        appCompatButton.perform(click());
    //
    //        ViewInteraction viewGroup = onView(
    //                allOf(childAtPosition(
    //                        allOf(withId(android.R.id.content),
    //                                childAtPosition(
    //                                        withId(R.id.decor_content_parent),
    //                                        1)),
    //                        0),
    //                        isDisplayed()));
    //        viewGroup.check(matches(isDisplayed()));
    //
    //    }


}
