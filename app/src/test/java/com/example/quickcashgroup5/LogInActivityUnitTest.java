package com.example.quickcashgroup5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.UserManagement.LogInActivity;

import org.junit.Test;

public class LogInActivityUnitTest {
    static LogInActivity activityA;

    @Test
    public void checkIfPasswordisEmpty() {

    }
    @Test
    public void checkIfEmailIsValid() {
        assertTrue(activityA.emailValidation("abc123@dal.ca"));
    }
    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(activityA.emailValidation("abc.123dal.ca"));
    }

}
