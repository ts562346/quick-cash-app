package com.example.quickcashgroup5.UserManagment;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.R;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class LogInActivityTest{

    @Rule
public ActivityScenarioRule<LogInActivity> activityTestRule = new ActivityScenarioRule<>(LogInActivity.class);

    private String emailText = "sampletest@gmail.com";
    private String passwordText = "Sample@123";


    @Test
    public void visibilityScenarioTest(){
        //write visibility for texts
        //checking if "Sign to you account" text is displayed
        onView(withId(R.id.title)).check(matches(isCompletelyDisplayed()));
        //checking if emailText is displayed
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isCompletelyDisplayed()));
        //checking if passwordText is displayed
        onView(withId(R.id.editTextTextPassword)).check(matches(isCompletelyDisplayed()));
        //checking if hide/show button is displayed
        //onView(withId(R.id.btnHide)).check(matches(isDisplayed()));
        //checking if forgot password button is displayed
        onView(withId(R.id.registeredUserLabel)).check(matches(isCompletelyDisplayed()));
        //checking if "I dont have an account" button is displayed
        //onView(withId(R.id.btnNoAccount)).check(matches(isDisplayed()));
        //checking if login button is displayed
        onView(withId(R.id.registerButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void userInputScenarioTest(){
        // input some text in the edit email Text and close keyboard
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText(emailText)); closeSoftKeyboard();
        //input some text in the edit password Text and close keyboard
        onView(withId(R.id.editTextTextPassword)).perform(typeText(passwordText)); closeSoftKeyboard();
        //click for "Forgot Password"
        onView(withId(R.id.registeredUserLabel)).perform(click());
        // click for hide/show password
        //onView(withId(R.id.btnHide)).perform(click());
        // perform Register button Click
        onView(withId(R.id.registerButton)).perform(click());
    }



}
