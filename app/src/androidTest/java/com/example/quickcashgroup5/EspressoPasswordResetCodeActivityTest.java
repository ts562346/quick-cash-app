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

import com.example.quickcashgroup5.passwordmanagement.PasswordResetCodeActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoPasswordResetCodeActivityTest {

    @Rule
    public ActivityScenarioRule<PasswordResetCodeActivity> activityTestRule = new ActivityScenarioRule<>(PasswordResetCodeActivity.class);

    private final String otp = "1234";


    @Test
    public void inputVisibilityTest(){
        Espresso.onView(withId(R.id.code)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.codeNotReceived)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.sendButton)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        Espresso.onView(withId(R.id.backToLogin)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        Espresso.onView(withId(R.id.code)).perform(ViewActions.typeText(otp)); Espresso.closeSoftKeyboard();
        assertTrue(true);
    }

}
