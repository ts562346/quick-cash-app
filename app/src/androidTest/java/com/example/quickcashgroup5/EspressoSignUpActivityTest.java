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

import com.example.quickcashgroup5.usermanagement.SignUpActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoSignUpActivityTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityTestRule = new ActivityScenarioRule<>(SignUpActivity.class);

    private final String nameText = "Sample Test";
    private final String emailText = "sampletest@gmail.com";
    private final String passwordText = "Sample@123";


    @Test
    public void inputVisibilityTest(){
        Espresso.onView(withId(R.id.editTextUsername)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.editTextTextPassword)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.editTextTextConfirmPassword)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.registeredUserLabel)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Espresso.onView(withId(R.id.registerButton)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        Espresso.onView(withId(R.id.editTextUsername)).perform(ViewActions.typeText(nameText)); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText(emailText)); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText(passwordText)); Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.editTextTextConfirmPassword)).perform(ViewActions.typeText(passwordText)); Espresso.closeSoftKeyboard();
        assertTrue(true);
    }
}
