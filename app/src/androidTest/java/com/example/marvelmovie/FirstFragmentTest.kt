package com.example.marvelmovie

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.testing.launchFragment
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FirstFragmentTest {


    private  val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp(){
        launchFragmentInHiltContainer<FirstFragment>(Bundle(),R.style.Theme_MarvelMovie) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

    }

    @Test
    fun checkIfImageDisplaying(){
      onView(withId(R.id.imageView))
          .check(matches(isDisplayed()))
    }

    @Test
    fun buttonClick_ShouldShow(){

        onView(withId(R.id.button_First))
            .check(matches(isDisplayed()))
            .perform(click())
            assert(navController.currentDestination?.id == R.id.SecondFragment)
    }


}