package com.example.quickcashgroup5;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.JobCreation.CreateJobActivity;
import com.example.quickcashgroup5.R;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class EspressoJobPostingActivityTest {

    @Rule
    public ActivityScenarioRule<CreateJobActivity> activityTestRule = new ActivityScenarioRule<>(CreateJobActivity.class);

    private final String jobTitle= "Sample Test";
    private final String location= "Halifax, Nova Scotia";
    private final String payment= "15";
    private final String duration = "12";

    @Test
    public void inputVisibilityTest(){
        onView(withId(R.id.editTextJobTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextLocation)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextPayment)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextDuration)).check(matches(isDisplayed()));
        onView(withId(R.id.submit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        onView(withId(R.id.editTextJobTitle)).perform(ViewActions.typeText(jobTitle));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextLocation)).perform(ViewActions.typeText(location));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextPayment)).perform(ViewActions.typeText(payment));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.editTextDuration)).perform(ViewActions.typeText(duration));
        Espresso.closeSoftKeyboard();
    }
}