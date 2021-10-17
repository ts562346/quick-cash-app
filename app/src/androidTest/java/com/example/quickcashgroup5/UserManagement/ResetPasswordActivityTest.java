//package com.example.quickcashgroup5.UserManagment;
//
//import static androidx.test.espresso.Espresso.closeSoftKeyboard;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
//import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static org.hamcrest.core.IsNot.not;
//
//import junit.framework.TestCase;
//
//import org.junit.Test;
//
//public class ResetPasswordActivityTest extends TestCase {
//
//    //NOT SURE HOW TO CREATE RULE
////    @Rule
////    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);
//
//    //For Screen 7 Reset Password
//
//    //for password input
//    @Test
//    public void passwordFieldIsDisplayed() {
//        onView(withId(R.id.password)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void passwordFieldIsCompletelyDisplayed() {
//        onView(withId(R.id.password)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void passwordFieldIsClickable() {
//        onView(withId(R.id.password).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputPassword(){
//        onView(withId(R.id.textToEnter).perform(typeText("SamplePassword123")));
//        closeSoftKeyboard();
//    }
//
//    //for confirm password input
//    @Test
//    public void confirmPasswordFieldIsDisplayed() {
//        onView(withId(R.id.password)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void confirmPasswordFieldIsCompletelyDisplayed() {
//        onView(withId(R.id.password)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void confirmPasswordFieldIsClickable() {
//        onView(withId(R.id.password).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputConfirmPassword(){
//        onView(withId(R.id.textToEnter).perform(typeText("SamplePassword123")));
//        closeSoftKeyboard();
//    }
//
//    //for hide/show password button
//    @Test
//    public void showbuttonIsDisplayed() {
//        onView(withId(R.id.showButton)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void showbuttonIsCompletelyDisplayed() {
//        onView(withId(R.id.showButton)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void showbuttonIsClickable() {
//        onView(withId(R.id.showButton).check(matches(isClickable())));
//    }
//
//    @Test
//    public void showbuttonIsSelectable() {
//        onView(withId(R.id. showButton)).check(matches(isSelected()));
//    }
//
//    @Test
//    public void showbuttonIsNotSelectable() {
//        onView(withId(R.id. showButton)).check(matches(not(isSelected())));
//    }
//
//    //for "Back to Login" button
//    @Test
//    public void oldUserButtonIsDisplayed() {
//        onView(withId(R.id.oldUserButton)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void oldUserButtonIsCompletelyDisplayed() {
//        onView(withId(R.id.oldUserButton)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void oldUserButtonIsClickable() {
//        onView(withId(R.id.oldUserButton).check(matches(isClickable())));
//    }
//
//    //For Rest Button
//    @Test
//    public void resetButtonIsDisplayed() {
//        onView(withId(R.id.resetButton)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void resetButtonIsCompletelyDisplayed() {
//        onView(withId(R.id.resetButton)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void resetButtonIsClickable() {
//        onView(withId(R.id.resetButton).check(matches(isClickable())));
//    }
//
//}