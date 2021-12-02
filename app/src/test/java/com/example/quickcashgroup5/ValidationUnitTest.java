package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;

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

    @Test
    public void checkIfConfirmPasswordIsEmpty() {
        assertFalse(validation.confirmPasswordValidation("", ""));
        assertFalse(validation.confirmPasswordValidation("", validPassword));
        assertFalse(validation.confirmPasswordValidation("", invalidPassword));
    }

    @Test
    public void checkIfConfirmPasswordIsValid() {
        assertTrue(validation.confirmPasswordValidation(validPassword, validPassword));
    }

    @Test
    public void checkIfConfirmPasswordIsInvalid() {
        assertFalse(validation.confirmPasswordValidation(validPassword, invalidPassword));
    }

    @Test
    public void checkIfOTPIsEmpty() {
        assertFalse(validation.otpValidation(""));
    }

    @Test
    public void checkIfOTPIsValid() {
        assertTrue(validation.otpValidation("1234"));
    }

    @Test
    public void checkIfOTPIsInvalidLess() {
        assertFalse(validation.otpValidation("1"));
    }

    @Test
    public void checkIfOTPIsInvalidMore() {
        assertFalse(validation.otpValidation("12345"));
    }

    @Test
    public void checkIfJobTitleIsEmpty() {
        assertFalse(validation.jobTitleValidation(""));
    }

    @Test
    public void checkIfJobTitleIsNotEmpty() {
        assertTrue(validation.jobTitleValidation("Hey there"));
    }

    @Test
    public void checkIfWageIsEmpty() {
        assertFalse(validation.wageValidation(""));
    }

    @Test
    public void checkIfWageIsValidDecimal() {
        assertTrue(validation.wageValidation("12.34"));
    }

    @Test
    public void checkIfWageIsValidNumeric() {
        assertTrue(validation.wageValidation("12"));
    }

    @Test
    public void checkIfWageIsInvalidLess() {
        assertFalse(validation.wageValidation("12."));
    }

    @Test
    public void checkIfWageIsInvalidMore() {
        assertFalse(validation.wageValidation("12.345"));
    }

    @Test
    public void checkIfHoursIsEmpty() {
        assertFalse(validation.hoursValidation(""));
    }

    @Test
    public void checkIfHoursIsValidDecimal() {
        assertTrue(validation.hoursValidation("12.34"));
    }

    @Test
    public void checkIfHoursIsValidNumeric() {
        assertTrue(validation.hoursValidation("12"));
    }

    @Test
    public void checkIfHoursIsInvalidLess() {
        assertFalse(validation.hoursValidation("12."));
    }

    @Test
    public void checkIfHoursIsInvalidMore() {
        assertFalse(validation.hoursValidation("12.345"));
    }
}
