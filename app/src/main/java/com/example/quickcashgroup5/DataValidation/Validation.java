package com.example.quickcashgroup5.DataValidation;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Validation {
    /**
     * Sanitizes string inputs
     *
     * @param value
     * @return
     */
    protected String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    /**
     * Validate username
     *
     * @param name
     * @return
     */
    public boolean fullNameValidation(String name) {
        if (!name.isEmpty()) {
            //Full Name can only contain name/names divided by space characters
            //Only the first letter of a name should be capital
            return name.matches("^(?>[A-Z][a-z]+\\s?)+$");
        } else {
            return false;
        }
    }

    /**
     * Validates email
     *
     * @param email
     * @return
     */
    public boolean emailValidation(String email) {
        if (!email.isEmpty()) {
            //The first part of the email can only contain letters, digits, and periods
            //The second and third part can only contain letters
            //The third part can only be between 2 - 3 characters long
            return email.matches("^[a-zA-Z\\d\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
        } else {
            return false;
        }
    }

    /**
     * Validates password
     *
     * @param password
     * @return
     */
    public boolean passwordValidation(String password) {
        if (!password.isEmpty()) {
            //Password should have at least 1 number, 1 uppercase, 1 lowercase, and 1 special character
            //The password should be at least 8 characters long
            return password.matches("^(?=.*?\\d+)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\\W).{8,}$");
        } else {
            return false;
        }
    }

    /**
     * Validates confirm password
     *
     * @param password
     * @param confirmPassword
     * @return
     */
    public boolean confirmPasswordValidation(String password, String confirmPassword) {
        if (!password.isEmpty() && !confirmPassword.isEmpty()) {
            //Password should be equal to confirmPassword
            return password.equals(confirmPassword);
        } else {
            return false;
        }
    }

    public boolean otpValidation(String otp){
        if (!otp.isEmpty()) {
            //The otp should be numeric and 4 characters long
            return otp.matches("^\\d{4}$");
        } else {
            return false;
        }
    }

    public boolean jobTitleValidation(String jobTitle){
        return !jobTitle.isEmpty();
    }

    public boolean locationValidation(String location, Context context){
        if (!location.isEmpty()) {
            Geocoder geocoder = new Geocoder(context);
            List<Address> addressLists = new ArrayList<>();
            try {
                addressLists = geocoder.getFromLocationName("location", 1);
            }catch (IOException ex){
                Log.d(TAG, "GeoLocate: exception " + ex.getMessage());
                return false;
            }
            return addressLists.size() > 0;
        } else {
            return false;
        }
    }

    public boolean wageValidation(String wage){
        if (!wage.isEmpty()) {
            //The wage can be decimal but up to 2 places
            return wage.matches("^\\d+\\.?\\d{1,2}$");
        } else {
            return false;
        }
    }

    public boolean hoursValidation(String hours){
        if (!hours.isEmpty()) {
            //The hours can be decimal but up to 2 places
            return hours.matches("^\\d+\\.?\\d{1,2}$");
        } else {
            return false;
        }
    }
}
