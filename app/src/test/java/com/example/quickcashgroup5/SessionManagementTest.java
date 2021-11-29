package com.example.quickcashgroup5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SessionManagementTest {
    static SessionManagement sessionManagement;
    static SessionManagement sessionManagement2;
    static SignUpActivity signUpActivity;
    static MainActivity mainActivity;
    static String Name = "JohnDoe";
    static String Email = "john.doe@gmail.com";
    static String Role = "Employee";

    //Initialize classes
    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
        signUpActivity = new SignUpActivity();
        sessionManagement = new SessionManagement(signUpActivity);
        sessionManagement2 = new SessionManagement(mainActivity);
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    //Session creation with valid inputs
    @Test
    public void sessionCreation_valid() {
        sessionManagement.createSession(Name, Email, Role);
        assertNotNull(sessionManagement2.getName());
        assertNotNull(sessionManagement2.getEmail());
        assertNotNull(sessionManagement2.getRole());
        assertTrue(sessionManagement2.isLoggedIn());
    }

    //Session creation with invalid inputs
    @Test
    public void sessionCreation_inValid() {
        assertNull(sessionManagement2.getName());
        assertNull(sessionManagement2.getEmail());
        assertNull(sessionManagement2.getRole());
        assertFalse(sessionManagement2.isLoggedIn());
    }

    //Session end with valid inputs
    @Test
    public void sessionEnd_valid() {
        sessionManagement.createSession(Name, Email, Role);
        sessionManagement2.logout();
        assertNull(sessionManagement2.getName());
        assertNull(sessionManagement2.getEmail());
        assertNull(sessionManagement2.getRole());
        assertFalse(sessionManagement2.isLoggedIn());
    }

}
