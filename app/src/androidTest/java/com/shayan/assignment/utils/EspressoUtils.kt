package com.shayan.assignment.utils

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun waitTest(delay: Long) {
    val waitAction: ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isRoot()
        }

        override fun getDescription(): String {
            return "wait for " + delay + "milliseconds"
        }

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadForAtLeast(delay)
        }
    }
    Espresso.onView(ViewMatchers.isRoot()).perform(waitAction)
}
