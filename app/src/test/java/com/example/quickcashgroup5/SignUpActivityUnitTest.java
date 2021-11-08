package com.example.quickcashgroup5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.UserManagement.SignUpActivity;

import org.junit.Test;

public class SignUpActivityUnitTest {
    @Test
    public void checkIfEmailIsEmpty() {
        assertTrue(SignUpActivity.emailValidation("test@gmail.com"));
        assertFalse(SignUpActivity.emailValidation(""));
    }

    @Test
    public void checkIfNameisEmpty(){
        assertTrue(SignUpActivity.usernameValidation("test"));
        assertFalse(SignUpActivity.usernameValidation(""));
    }
    @Test
    public void checkIfPasswordIsEmpty(){
        assertTrue(SignUpActivity.passwordValidation("Test@1234"));
        assertFalse(SignUpActivity.passwordValidation(""));
    }
    @Test
    public void checkIfConfirmPasswordValidation(){
        assertTrue(SignUpActivity.confirmPasswordValidation("Test@1234", "Test@1234"));
        assertFalse(SignUpActivity.confirmPasswordValidation("Test@1234", "Test@4321"));
    }
}
