package com.example.quickcashgroup5.UserManagment;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import junit.framework.TestCase;

import org.junit.Test;

public class RecoveryAccountActivityTest extends TestCase {

    //NOT SURE HOW TO CREATE RULE
//    @Rule
//    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    //For Screen 6 Account Recovery
    //For email input
    @Test
    public void emailFieldIsDisplayed() {
    onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    @Test
    public void emailFieldIsCompletelyDisplayed() {
        onView(withId(R.id.email)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void emailFieldIsClickable() {
        onView(withId(R.id.logButton).check(matches(isClickable())));
    }

    @Test
    public void testUserInputEmail(){
        onView(withId(R.id.textToEnter).perform(typeText("sample@gmail.com")));
        closeSoftKeyboard();
    }

    //for "Back to Login" button
    @Test
    public void oldUserButtonIsDisplayed() {
        onView(withId(R.id.oldUserButton)).check(matches(isDisplayed()));
    }

    @Test
    public void oldUserButtonIsCompletelyDisplayed() {
        onView(withId(R.id.oldUserButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void oldUserButtonIsClickable() {
        onView(withId(R.id.oldUserButton).check(matches(isClickable())));
    }

    //For Send Button
    @Test
    public void sendButtonIsDisplayed() {
        onView(withId(R.id.sendButton)).check(matches(isDisplayed()));
    }

    @Test
    public void registerButtonIsCompletelyDisplayed() {
        onView(withId(R.id.sendButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void registerButtonIsClickable() {
        onView(withId(R.id.sendButton).check(matches(isClickable())));
    }


}