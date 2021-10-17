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
//
//import static org.hamcrest.core.IsNot.not;
//
//import androidx.test.ext.junit.rules.ActivityScenarioRule;
//
//import com.example.quickcashgroup5.R;
//
//import junit.framework.TestCase;
//
//import org.junit.Rule;
//import org.junit.Test;
//
//public class SignUpActivityTest extends TestCase {
//
//    //NOT SURE HOW TO CREATE RULE
//    @Rule
//    public ActivityScenarioRule<SignUpActivity> SignUpActivityTestRule = new ActivityScenarioRule<SignUpActivity>(SignUpActivity.class);
//
//
//    //For user name input
//    @Test
//    public void nameFieldIsDisplayed() {
//        onView(withId(R.id.name)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void nameFieldIsCompletelyDisplayed() {
//        onView(withId(R.id.name)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void nameFieldIsClickable() {
//        onView(withId(R.id.name)).check(matches(isClickable()));
//    }
//
//    @Test
//    public void testUserInputName(){
//        onView(withId(R.id.name)).perform(typeText("Sample Test"));
//        closeSoftKeyboard();
//    }
//
//    //For email input
//    @Test
//    public void emailFieldIsDisplayed() {
//        onView(withId(R.id.email)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void emailFieldIsCompletelyDisplayed() {
//        onView(withId(R.id.email)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void emailFieldIsClickable() {
//        onView(withId(R.id.email)).check(matches(isClickable()));
//    }
//
//    @Test
//    public void testUserInputEmail(){
//        onView(withId(R.id.email)).perform(typeText("sample@gmail.com"));
//        closeSoftKeyboard();
//    }
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
//        onView(withId(R.id.password)).check(matches(isClickable()));
//    }
//
//    @Test
//    public void testUserInputPassword(){
//        onView(withId(R.id.password)).perform(typeText("SamplePassword123"));
//        closeSoftKeyboard();
//    }
//
//    //for confirm password input
//    @Test
//    public void confirmPasswordFieldIsDisplayed() {
//        onView(withId(R.id.confirmPassword)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void confirmPasswordFieldIsCompletelyDisplayed() {
//        onView(withId(R.id.confirmPassword)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void confirmPasswordFieldIsClickable() {
//        onView(withId(R.id.confirmPassword)).check(matches(isClickable()));
//    }
//
//    @Test
//    public void testUserInputConfirmPassword(){
//        onView(withId(R.id.confirmPassword)).perform(typeText("SamplePassword123"));
//        closeSoftKeyboard();
//    }
//
////    //for hide/show password button
////    @Test
////    public void showbuttonIsDisplayed() {
////        onView(withId(R.id.showButton)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void showbuttonIsCompletelyDisplayed() {
////        onView(withId(R.id.showButton)).check(matches(isCompletelyDisplayed()));
////    }
////
////    @Test
////    public void showbuttonIsClickable() {
////        onView(withId(R.id.showButton)).check(matches(isClickable()));
////    }
////
////    @Test
////    public void showbuttonIsSelectable() {
////        onView(withId(R.id.showButton)).check(matches(isSelected()));
////    }
////
////    @Test
////    public void showbuttonIsNotSelectable() {
////        onView(withId(R.id.showButton)).check(matches(not(isSelected())));
////    }
////
////    //for Employee button
////    @Test
////    public void employeeButtonIsDisplayed() {
////        onView(withId(R.id.employeeButton)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void employeeButtonIsCompletelyDisplayed() {
////        onView(withId(R.id.employeeButton)).check(matches(isCompletelyDisplayed()));
////    }
////
////    @Test
////    public void employeeButtonIsClickable() {
////        onView(withId(R.id.employeeButton)).check(matches(isClickable()));
////    }
////
////    //for Employer button
////    @Test
////    public void employerButtonIsDisplayed() {
////        onView(withId(R.id.employerButton)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void employerButtonIsCompletelyDisplayed() {
////        onView(withId(R.id.employerButton)).check(matches(isCompletelyDisplayed()));
////    }
////
////    @Test
////    public void employerButtonIsClickable() {
////        onView(withId(R.id.employerButton)).check(matches(isClickable()));
////    }
////
////    //for "I have an account" button
////    @Test
////    public void oldUserButtonIsDisplayed() {
////        onView(withId(R.id.oldUserButton)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void oldUserButtonIsCompletelyDisplayed() {
////        onView(withId(R.id.oldUserButton)).check(matches(isCompletelyDisplayed()));
////    }
////
////    @Test
////    public void oldUserButtonIsClickable() {
////        onView(withId(R.id.oldUserButton)).check(matches(isClickable()));
////    }
////
////    //For Register Button
////    @Test
////    public void registerButtonIsDisplayed() {
////        onView(withId(R.id.registerButton)).check(matches(isDisplayed()));
////    }
////
////    @Test
////    public void registerButtonIsCompletelyDisplayed() {
////        onView(withId(R.id.registerButton)).check(matches(isCompletelyDisplayed()));
////    }
////
////    @Test
////    public void registerButtonIsClickable() {
////        onView(withId(R.id.registerButton)).check(matches(isClickable()));
////    }
//
//
//}