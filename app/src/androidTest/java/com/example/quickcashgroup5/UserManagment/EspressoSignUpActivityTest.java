package com.example.quickcashgroup5.UserManagment;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoSignUpActivityTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityTestRule = new ActivityScenarioRule<>(SignUpActivity.class);

    private String nameText = "Sample Test";
    private String emailText = "sampletest@gmail.com";
    private String passwordText = "Sample@123";


    @Test
    public void inputVisibilityTest(){
        //write visibility for texts
        //checking if emailText is displayed
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isDisplayed()));
        //checking if passwordText is displayed
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
        //checking if confirm passwordText is displayed
        onView(withId(R.id.editTextTextConfirmPassword)).check(matches(isDisplayed()));
        // checking if spin is visible
        onView(withId(R.id.spin)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        // checking if "I have an account" button is visible
        onView(withId(R.id.registeredUserLabel)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        // checking if register button is visible
        onView(withId(R.id.registerButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        // input some text in the edit full name Text and close keyboard
        onView(withId(R.id.editTextUsername)).perform(typeText(nameText)); closeSoftKeyboard();
        // input some text in the edit email Text and close keyboard
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText(emailText)); closeSoftKeyboard();
        //input some text in the edit password Text and close keyboard
        onView(withId(R.id.editTextTextPassword)).perform(typeText(passwordText)); closeSoftKeyboard();
        //input some text in the edit confirm password Text and close keyboard
        onView(withId(R.id.editTextTextConfirmPassword)).perform(typeText(passwordText)); closeSoftKeyboard();
    }
}
