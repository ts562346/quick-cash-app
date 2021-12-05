package com.example.quickcashgroup5;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.FeedbackManagement.SendFeedbackActivity;

import org.junit.Rule;
import org.junit.Test;

public class EspressoSendFeedbackActivity {
    @Rule
    public ActivityScenarioRule<SendFeedbackActivity> activityTestRule = new ActivityScenarioRule<>(SendFeedbackActivity.class);

    private final String feedback = "Great app";

    @Test
    public void inputVisibilityTest(){
        onView(withId(R.id.editTextName)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextUserType)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextFeedback)).check(matches(isDisplayed()));
        onView(withId(R.id.submit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        onView(withId(R.id.editTextFeedback)).perform(ViewActions.typeText(feedback));
        Espresso.closeSoftKeyboard();
    }
}
