package uwanttolearn.dagger2.java.login


import android.support.annotation.IdRes
import android.support.annotation.IntDef
import android.support.annotation.StringRes
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
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.matcher.IntentMatchers
import org.hamcrest.Matchers.*
import uwanttolearn.dagger2.java.home.HomeActivity


//@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun whenLoginClickWithEmptyUsernameShouldShowUsernameEmptyToast(){
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(click())
        toastIsDisplayed(R.string.enter_username)
    }

    @Test
    fun whenLoginClickWithEmptyPasswordShouldShowPasswordEmptyToast(){
        Espresso.onView(ViewMatchers.withId(R.id.LoginActivity_username_edit_text))
                .perform(ViewActions.typeText("Hafiz"))
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(click())
        toastIsDisplayed(R.string.enter_password)
    }

    @Test
    fun whenLoginClickWithValidCredentialsShouldOpenHomeScreen() {
        fun input(text:String,@IdRes id: Int){
            Espresso.onView(ViewMatchers.withId(id))
                    .perform(ViewActions.typeText(text))
        }
        input("Hafiz", R.id.LoginActivity_username_edit_text)
        input("123456", R.id.LoginActivity_password_edit_text)
        Espresso.onView(ViewMatchers.withId(R.id.button)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.MainActivity_recycler_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun toastIsDisplayed(@StringRes stringId: Int){
        Thread.sleep(1000)
        onView(withText(stringId)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

}
