package com.example.quickcashgroup5.UserManagment;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.quickcashgroup5.MainActivity;
import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

public class LogInActivityTest extends TestCase {

    //NOT SURE HOW TO CREATE RULE
//    @Rule
//    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    //For Screen 2 Login Page

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

    //for password input
    @Test
    public void passwordFieldIsDisplayed() {
        onView(withId(R.id.password)).check(matches(isDisplayed()));
    }

    @Test
    public void passwordFieldIsCompletelyDisplayed() {
        onView(withId(R.id.password)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void passwordFieldIsClickable() {
        onView(withId(R.id.password).check(matches(isClickable())));
    }

    @Test
    public void testUserInputPassword(){
        onView(withId(R.id.textToEnter).perform(typeText("SamplePassword123")));
        closeSoftKeyboard();
    }

    //for hide/show password button
    @Test
    public void showbuttonIsDisplayed() {
        onView(withId(R.id.showButton)).check(matches(isDisplayed()));
    }

    @Test
    public void showbuttonIsCompletelyDisplayed() {
        onView(withId(R.id.showButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void showbuttonIsClickable() {
        onView(withId(R.id.showButton).check(matches(isClickable())));
    }

    @Test
    public void showbuttonIsSelectable() {
        onView(withId(R.id. showButton)).check(matches(isSelected()));
    }

    @Test
    public void showbuttonIsNotSelectable() {
        onView(withId(R.id. showButton)).check(matches(not(isSelected())));
    }

    //for "forgot password?" button
    @Test
    public void forgotbuttonIsDisplayed() {
        onView(withId(R.id.forgotButton)).check(matches(isDisplayed()));
    }

    @Test
    public void forgotbuttonIsCompletelyDisplayed() {
        onView(withId(R.id.forgotButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void forgotbuttonIsClickable() {
        onView(withId(R.id.forgotButton).check(matches(isClickable())));
    }

    //for "I dont have account" button
    @Test
    public void newUserButtonIsDisplayed() {
        onView(withId(R.id.newUserButton)).check(matches(isDisplayed()));
    }

    @Test
    public void newUserButtonIsCompletelyDisplayed() {
        onView(withId(R.id.newUserButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void newUserButtonIsClickable() {
        onView(withId(R.id.newUserButton).check(matches(isClickable())));
    }


    //For Login Button
    @Test
    public void buttonIsDisplayed() {
        onView(withId(R.id.logButton)).check(matches(isDisplayed()));
    }

    @Test
    public void buttonIsCompletelyDisplayed() {
        onView(withId(R.id.logButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void buttonIsClickable() {
        onView(withId(R.id.logButton).check(matches(isClickable())));
    }

}
