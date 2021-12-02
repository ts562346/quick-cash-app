package com.example.quickcashgroup5;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.UserManagement.SessionManagement;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class SessionManagementTest {
    static SessionManagement sessionManagement;
    static SessionManagement sessionManagement2;
    static String name = "JohnDoe";
    static String email = "john.doe@gmail.com";
    static String role = "Employee";

    //Initialize classes
    @BeforeClass
    public static void setup() {
        sessionManagement = Mockito.mock(SessionManagement.class);
        Mockito.when(sessionManagement.getName()).thenReturn(name);
        Mockito.when(sessionManagement.getEmail()).thenReturn(email);
        Mockito.when(sessionManagement.getRole()).thenReturn(role);
        Mockito.when(sessionManagement.isLoggedIn()).thenReturn(true);

        sessionManagement2 = Mockito.mock(SessionManagement.class);
        Mockito.when(sessionManagement2.getName()).thenReturn(null);
        Mockito.when(sessionManagement2.getEmail()).thenReturn(null);
        Mockito.when(sessionManagement2.getRole()).thenReturn(null);
        Mockito.when(sessionManagement2.isLoggedIn()).thenReturn(false);
    }

    //Session creation with valid inputs
    @Test
    public void sessionCreation_valid() {
        sessionManagement2.createSession(name, email, role);

        assertNotNull(sessionManagement.getName());
        assertNotNull(sessionManagement.getEmail());
        assertNotNull(sessionManagement.getRole());
        assertTrue(sessionManagement.isLoggedIn());

        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getName();
        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getEmail();
        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getRole();
        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).isLoggedIn();
    }

    //Session creation with invalid inputs
    @Test
    public void sessionCreation_inValid() {
        assertNull(sessionManagement2.getName());
        assertNull(sessionManagement2.getEmail());
        assertNull(sessionManagement2.getRole());
        assertFalse(sessionManagement2.isLoggedIn());

        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getName();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getEmail();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getRole();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).isLoggedIn();
    }

    //Session end with valid inputs
    @Test
    public void sessionEnd_valid() {
        sessionManagement.createSession(name, email, role);
        sessionManagement.logout();

        assertNull(sessionManagement2.getName());
        assertNull(sessionManagement2.getEmail());
        assertNull(sessionManagement2.getRole());
        assertFalse(sessionManagement2.isLoggedIn());

        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getName();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getEmail();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).getRole();
        Mockito.verify(sessionManagement2, Mockito.atLeastOnce()).isLoggedIn();
    }

}
