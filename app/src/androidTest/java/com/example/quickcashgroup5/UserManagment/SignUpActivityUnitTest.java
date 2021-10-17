//package com.example.quickcashgroup5.UserManagement;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import android.widget.EditText;
//
//import com.example.quickcashgroup5.R;
//import com.example.quickcashgroup5.UserManagment.SignUpActivity;
//
//import org.junit.Test;
//
//public class SignUpActivityUnitTest {
//    @Test
//    public void checkIfEmailIsEmpty() {
//        assertTrue(SignUpActivity.isEmptyEmail(""));
//        assertFalse(SignUpActivity.isEmptyEmail("xyz$56"));
//    }
//
//    @Test
//    public void checkIfNameisEmpty(){
//        assertTrue(SignUpActivity.isEmptyName(""));
//        assertFalse(SignUpActivity.isEmptyName("xyz$56"));
//    }
//    @Test
//    public void checkIfPasswordisEmpty(){
//        assertTrue(SignUpActivity.isEmptyPassword(""));
//        assertFalse(SignUpActivity.isEmptyPassword("xyz$56"));
//    }
//    @Test
//    public void checkIfConfirmPasswordisEmpty(){
//        assertTrue(SignUpActivity.isEmptyConfirmPassword(""));
//        assertFalse(SignUpActivity.isEmptyConfirmPassword("xyz$56"));
//    }
//    @Test
//    public void checkIfEmailIsValid() {
//        assertTrue(SignUpActivity.isValidEmailAddress("abc123@dal.ca"));
//    }
//
//    @Test
//    public void checkIfEmailIsInvalid() {
//        assertFalse(SignUpActivity.isValidEmailAddress("abc.123dal.ca"));
//    }
//
//}
