package com.example.quickcashgroup5;

//import com.example.quickcashgroup5.UserManagement.LogInActivity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;

@RunWith(JUnit4:class)
public class LogInActivityUnitTest {

    static LogInActivity logInActivity;
    static MainActivity mainActivity;
    static SignUpActivity signUpActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
        logInActivity = new LogInActivity();
        signUpActivity = new SignUpActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertFalse(LogInActivity.passwordValidation(""));
        assertTrue(LogInActivity.passwordValidation("#mirza123"));
    }
    @Test
    public void checkIfEmailIsValid() {
        assertFalse(logInActivity.emailValidation(""));
        assertTrue(logInActivity.emailValidation("abc123@dal.ca"));
    }
    @Test
    public void checkIfSanitized() {
        String fake_string = "hello";
        assertEquals(fake_string, logInActivity.sanitize("hello  "));
    }
    

}
