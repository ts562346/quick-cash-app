package com.example.quickcashgroup5.UserManagment;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;

import static org.hamcrest.CoreMatchers.anything;

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
        onView(withId(R.id.editTextTextJobDescription)).check(ViewAssertions.matches(isDisplayed()));
        //checking if location is displayed
        onView(withId(R.id.editTextTextEmailAddress)).check(ViewAssertions.matches(isDisplayed()));
        //checking if payment is displayed
        onView(withId(R.id.editTextTextMinimumPayment)).check(ViewAssertions.matches(isDisplayed()));
        //checking if duration is displayed
        onView(withId(R.id.editTextTextMinimumHours)).check(ViewAssertions.matches(isDisplayed()));
        //checking if category is displayed
        onView(withId(R.id.spin)).check(ViewAssertions.matches(isDisplayed()));
        // checking if submit button is visible
        onView(withId(R.id.submit)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        // input some text in the job title and close keyboard
        onView(withId(R.id.editTextTextJobDescription)).perform(ViewActions.typeText(jobTitle));Espresso.closeSoftKeyboard();
        // input some text in the edit location and close keyboard
        onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.typeText(location)); Espresso.closeSoftKeyboard();
        //Espresso.onView(withText(location)).inRoot(withDecorView(not(mActivityTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed())) .perform(click());
        //input some text in the edit payment Text and close keyboard
        onView(withId(R.id.editTextTextMinimumPayment)).perform(ViewActions.typeText(payment)); Espresso.closeSoftKeyboard();
        //input some text in the edit duration Text and close keyboard
        onView(withId(R.id.editTextTextMinimumHours)).perform(ViewActions.typeText(duration)); Espresso.closeSoftKeyboard();
        //input some text in the category and close keyboard
        onView(withId(R.id.spin)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        //click the submit button
        onView(withId(R.id.submit)).perform(click());
    }
}