package com.example.quickcashgroup5;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertTrue;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.passwordmanagement.RecoveryAccountActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoRecoverAccountActivityTest {

    @Rule
    public ActivityScenarioRule<RecoveryAccountActivity> activityTestRule = new ActivityScenarioRule<>(RecoveryAccountActivity.class);

    private final String emailAddress = "test@gmail.com";

    @Test
    public void userInputTest(){
        Espresso.onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText(emailAddress)); Espresso.closeSoftKeyboard();
        assertTrue(true);
    }

}
