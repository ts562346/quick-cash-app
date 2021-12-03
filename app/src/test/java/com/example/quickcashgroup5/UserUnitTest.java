package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.User;

import org.junit.Test;

public class UserUnitTest {
    private final String name = "Unit";
    private final String email = "unit@test.com";
    private final String password = "Test@1234";
    private final String isEmployee = "y";
    private final String preferredLocation = "Halifax, Canada";
    private final String preferredCategory = "Computer Repair";
    private final String preferredPayment = "20";
    private final String preferredHours = "30";

    @Test
    public void userConstructor1() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void userConstructor2() {
        User user2 = new User(name, email, password, isEmployee);
        assertEquals(user2.getName(), name);
        assertEquals(user2.getEmail(), email);
        assertEquals(user2.getPassword(), password);
        assertEquals(user2.getIsEmployee(), isEmployee);
    }

    @Test
    public void userGetSet() {
        User user = new User();

        user.setName(name);
        assertEquals(user.getName(), name);

        user.setEmail(email);
        assertEquals(user.getEmail(), email);

        user.setPassword(password);
        assertEquals(user.getPassword(), password);

        user.setIsEmployee(isEmployee);
        assertEquals(user.getIsEmployee(), isEmployee);

        user.setPreferredCategory(preferredCategory);
        assertEquals(user.getPreferredCategory(), preferredCategory);

        user.setPreferredHours(preferredHours);
        assertEquals(user.getPreferredHours(), preferredHours);

        user.setPreferredLocation(preferredLocation);
        assertEquals(user.getPreferredLocation(), preferredLocation);

        user.setPreferredPayment(preferredPayment);
        assertEquals(user.getPreferredPayment(), preferredPayment);
    }

    @Test
    public void user2GetSet() {
        User user2 = new User(name, email, password, isEmployee);

        user2.setName(name);
        assertEquals(user2.getName(), name);

        user2.setEmail(email);
        assertEquals(user2.getEmail(), email);

        user2.setPassword(password);
        assertEquals(user2.getPassword(), password);

        user2.setIsEmployee(isEmployee);
        assertEquals(user2.getIsEmployee(), isEmployee);

        user2.setPreferredCategory(preferredCategory);
        assertEquals(user2.getPreferredCategory(), preferredCategory);

        user2.setPreferredHours(preferredHours);
        assertEquals(user2.getPreferredHours(), preferredHours);

        user2.setPreferredLocation(preferredLocation);
        assertEquals(user2.getPreferredLocation(), preferredLocation);

        user2.setPreferredPayment(preferredPayment);
        assertEquals(user2.getPreferredPayment(), preferredPayment);
    }

}