//package com.example.quickcashgroup5.UserManagment;
//
//import static androidx.test.espresso.Espresso.closeSoftKeyboard;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
//import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//
//import junit.framework.TestCase;
//
//import org.junit.Test;
//
//public class PasswordResetCodeActivityTest extends TestCase {
//
//    //NOT SURE HOW TO CREATE RULE
////    @Rule
////    public ActivityTestRule<LogInActivity> logInActivityTestRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);
//
//    //For Screen 4 Reset Password Code
//
//    //for code input box 1
//    @Test
//    public void box1FieldIsDisplayed() {
//        onView(withId(R.id.box1)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void box1FieldIsCompletelyDisplayed() {
//        onView(withId(R.id.box1)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void box1FieldIsClickable() {
//        onView(withId(R.id.box1).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputBox1(){
//        onView(withId(R.id.textToEnter).perform(typeText("1")));
//        closeSoftKeyboard();
//    }
//
//    //for code input box 2
//    @Test
//    public void box2FieldIsDisplayed() {
//        onView(withId(R.id.box2)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void box2FieldIsCompletelyDisplayed() {
//        onView(withId(R.id.box2)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void box2FieldIsClickable() {
//        onView(withId(R.id.box2).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputBox2(){
//        onView(withId(R.id.textToEnter).perform(typeText("2")));
//        closeSoftKeyboard();
//    }
//
//    //for code input box 3
//    @Test
//    public void box3FieldIsDisplayed() {
//        onView(withId(R.id.box3)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void box3FieldIsCompletelyDisplayed() {
//        onView(withId(R.id.box3)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void box3FieldIsClickable() {
//        onView(withId(R.id.box2).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputBox3(){
//        onView(withId(R.id.textToEnter).perform(typeText("3")));
//        closeSoftKeyboard();
//    }
//
//    //for code input box 4
//    @Test
//    public void box4FieldIsDisplayed() {
//        onView(withId(R.id.box4)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void box4FieldIsCompletelyDisplayed() {
//        onView(withId(R.id.box4)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void box4FieldIsClickable() {
//        onView(withId(R.id.box4).check(matches(isClickable())));
//    }
//
//    @Test
//    public void testUserInputBox4(){
//        onView(withId(R.id.textToEnter).perform(typeText("4")));
//        closeSoftKeyboard();
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
//    //For verify Button
//    @Test
//    public void verifyButtonIsDisplayed() {
//        onView(withId(R.id.verifytButton)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void verifyButtonIsCompletelyDisplayed() {
//        onView(withId(R.id.resetButton)).check(matches(isCompletelyDisplayed()));
//    }
//
//    @Test
//    public void verifyButtonIsClickable() {
//        onView(withId(R.id.verifyButton).check(matches(isClickable())));
//    }
//
//
//
//}