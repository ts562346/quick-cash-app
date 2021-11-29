package com.example.quickcashgroup5.UserManagment;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.CreateJob;
import org.junit.Rule;
import org.junit.Test;

public class EspressoJobPostingActivityTest {

    @Rule
    public ActivityScenarioRule<CreateJob> activityTestRule = new ActivityScenarioRule<>(CreateJob.class);

    private String jobTitle= "Sample Test";
    private String location= "Halifax, Nova Scotia";
    private String payment= "15";
    private String duration = "12";
    private String category = "Electronics Repair";

    @Test
    public void inputVisibilityTest(){
        //write visibility for texts
        //checking if job title is displayed
        onView(withId(R.id.editTextJobTitle)).check(matches(isDisplayed()));
        //checking if location is displayed
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isDisplayed()));
        //checking if payment is displayed
        onView(withId(R.id.editTextPayment)).check(matches(isDisplayed()));
        //checking if duration is displayed
        onView(withId(R.id.editTextDuration)).check(matches(isDisplayed()));
        //checking if category is displayed
        onView(withId(R.id.spin)).check(matches(isDisplayed()));
        // checking if submit button is visible
        onView(withId(R.id.submit)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        onView(withId(R.id.editTextJobTitle)).perform(ViewActions.typeText(jobTitle));
        onView(withId(R.id.editTextLocation)).perform(ViewActions.typeText(location));
        onView(withId(R.id.editTextPayment)).perform(ViewActions.typeText(payment));
        onView(withId(R.id.editTextDuration)).perform(ViewActions.typeText(duration));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCategory)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(category))).perform(click());
        onView(withId(R.id.spinnerCategory)).check(matches(withSpinnerText(containsString(category))));

        onView(withId(R.id.submit)).perform(click());
    }
}