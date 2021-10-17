package com.example.quickcashgroup5.UserManagment;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import org.junit.Rule;
import org.junit.Test;

public class EspressoLogInActivityTest {

    @Rule
    public ActivityScenarioRule<LogInActivity> activityTestRule = new ActivityScenarioRule<>(LogInActivity.class);

    private String emailText = "sampletest@gmail.com";
    private String passwordText = "Sample@123";


    @Test
    public void inputVisibilityTest(){
        //write visibility for texts
        //checking if "Sign to you account" text is displayed
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        //checking if emailText is displayed
        onView(withId(R.id.editTextTextEmailAddress)).check(matches(isDisplayed()));
        //checking if passwordText is displayed
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
        //checking if forgot password button is displayed
        onView(withId(R.id.forgotPass)).check(matches(isDisplayed()));
        //checking if Dont have an account button is displayed
        onView(withId(R.id.notRegisteredUserLabel)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        //checking if login button is displayed
        onView(withId(R.id.registerButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void userInputTest(){
        // input some text in the edit email Text and close keyboard
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText(emailText)); closeSoftKeyboard();
        //input some text in the edit password Text and close keyboard
        onView(withId(R.id.editTextTextPassword)).perform(typeText(passwordText)); closeSoftKeyboard();
    }

}
