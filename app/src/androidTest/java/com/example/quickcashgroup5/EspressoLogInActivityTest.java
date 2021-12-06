package com.example.quickcashgroup5;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertTrue;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.usermanagement.LogInActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoLogInActivityTest {

    @Rule
    public ActivityScenarioRule<LogInActivity> activityTestRule = new ActivityScenarioRule<>(LogInActivity.class);

    private final String emailText = "sampletest@gmail.com";
    private final String passwordText = "Sample@123";


    @Test
    public void inputVisibilityTest(){
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.editTextTextPassword)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.forgotPass)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.notRegisteredUserLabel)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Espresso.onView(withId(R.id.loginButton)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText(emailText)); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText(passwordText)); Espresso.closeSoftKeyboard();
        assertTrue(true);
    }

}
