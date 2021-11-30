package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationUnitTest {

    private Validation validation = new Validation();
    private String dangerousInput = "   \\Select * from database   ";
    private String safeInput = "Safe Input";
    private String validFullName = "Bruce Wayne";
    private String invalidFullName = "R2D2";
    private String validEmail = "bruce@wayne.com";
    private String invalidEmail = "r2d2@1234.coma";
    private String validPassword = "Test@1234";
    private String invalidPassword = "test";

    @Test
    public void sanitizationDangerousInput() {
        assertNotEquals(validation.sanitize(dangerousInput), dangerousInput);
    }

    @Test
    public void sanitizationSafeInput() {
        assertEquals(validation.sanitize(safeInput), safeInput);
    }

    @Test
    public void checkIfFullNameIsEmpty() {
        assertFalse(validation.fullNameValidation(""));
    }

    @Test
    public void checkIfFullNameIsValid() {
        assertTrue(validation.fullNameValidation(validFullName));
    }

    @Test
    public void checkIfFullNameIsInvalid() {
        assertFalse(validation.fullNameValidation(invalidFullName));
    }

    @Test
    public void checkIfEmailIsEmpty() {
        assertFalse(validation.emailValidation(""));
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(validation.emailValidation(validEmail));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(validation.emailValidation(invalidEmail));
    }


    @Test
    public void checkIfPasswordIsEmpty() {
        assertFalse(validation.passwordValidation(""));
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertTrue(validation.passwordValidation(validPassword));
    }

    @Test
    public void checkIfPasswordIsInvalid() {
        assertFalse(validation.passwordValidation(invalidPassword));
    }

}
