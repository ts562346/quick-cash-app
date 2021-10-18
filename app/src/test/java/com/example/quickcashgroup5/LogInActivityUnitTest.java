package com.example.quickcashgroup5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.UserManagement.LogInActivity;

import org.junit.Test;

public class LogInActivityUnitTest {

    @Test
    public void checkIfPasswordisEmpty() {
        assertTrue(LogInActivity.passwordValidation(""));
    }
    @Test
    public void checkIfEmailIsValid() {
        assertTrue(LogInActivity.emailValidation("abc123@dal.ca"));
    }
    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(LogInActivity.emailValidation("abc.123dal.ca"));
    }

}
