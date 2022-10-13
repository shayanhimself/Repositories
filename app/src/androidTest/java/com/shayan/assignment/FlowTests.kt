package com.shayan.assignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shayan.assignment.utils.waitTest
import com.shayan.assignment.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlowTests {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun `shouldNavigateFromListToTheCorrectDetailFragment`() {
        waitTest(200)
        onView(withText("Repo 1")).perform(click())
        onView(withText("abn/Repo1")).check(matches(isDisplayed()))
    }

}
