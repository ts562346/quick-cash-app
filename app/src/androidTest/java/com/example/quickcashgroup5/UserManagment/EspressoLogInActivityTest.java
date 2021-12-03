package com.example.quickcashgroup5.UserManagment;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import org.junit.Rule;
import org.junit.Test;

public class EspressoLogInActivityTest {

    @Rule
    public ActivityScenarioRule<LogInActivity> activityTestRule = new ActivityScenarioRule<>(LogInActivity.class);

    private final String emailText = "sampletest@gmail.com";
    private final String passwordText = "Sample@123";


    @Test
    public void inputVisibilityTest(){
        //write visibility for texts
        //checking if "Sign to you account" text is displayed
        Espresso.onView(withId(R.id.title)).check(ViewAssertions.matches(isDisplayed()));
        //checking if emailText is displayed
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).check(ViewAssertions.matches(isDisplayed()));
        //checking if passwordText is displayed
        Espresso.onView(withId(R.id.editTextTextPassword)).check(ViewAssertions.matches(isDisplayed()));
        //checking if forgot password button is displayed
        Espresso.onView(withId(R.id.forgotPass)).check(ViewAssertions.matches(isDisplayed()));
        //checking if Dont have an account button is displayed
        Espresso.onView(withId(R.id.notRegisteredUserLabel)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        //checking if login button is displayed
        Espresso.onView(withId(R.id.loginButton)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        // input some text in the edit email Text and close keyboard
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText(emailText)); Espresso.closeSoftKeyboard();
        //input some text in the edit password Text and close keyboard
        Espresso.onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText(passwordText)); Espresso.closeSoftKeyboard();
    }

}
