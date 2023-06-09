//Code adapted from:
//https://www.tutorialspoint.com/android/android_shared_preferences.htmpackage com.example.quickcashgroup5.UserManagement;

package com.example.quickcashgroup5.usermanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.quickcashgroup5.home.EmployeeHomeActivity;
import com.example.quickcashgroup5.home.EmployerHomeActivity;

/**
 * Class used to manage sessions
 */

//Referred to https://www.tutorialspoint.com/android/android_shared_preferences.htm
public class SessionManagement implements ISessionManagement {
    private static final String PREFERENCES_NAME = "Session";
    private final SharedPreferences sharedPreferences;
    private final Context context;
    SharedPreferences.Editor editor;


    /**
     * SessionManagement constructor
     *
     * @param context
     */
    public SessionManagement(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    /**
     * Sets the name value in a Session
     *
     * @param name
     */
    public void setName(String name) {
        editor.putString("Name", name);
        editor.apply();
    }

    /**
     * Sets the email value in a Session
     *
     * @param email
     */
    public void setEmail(String email) {
        editor.putString("Email", email);
        editor.apply();
    }

    /**
     * Sets the role value in a Session
     *
     * @param role
     */
    public void setRole(String role) {
        editor.putString("Role", role);
        editor.apply();
    }

    /**
     * Sets the OTP value in a Session
     *
     * @param otp
     */
    public void setOTP(int otp) {
        editor.putInt("OTP", otp);
        editor.apply();
    }

    /**
     * Sets the email value in a Session
     *
     * @param location
     */
    public void setLocation(String location) {
        editor.putString("Location", location);
        editor.apply();
    }

    /**
     * Gets the name value from a Session
     *
     * @return
     */
    public String getName() {
        return sharedPreferences.getString("Name", null);
    }

    /**
     * Gets the email value from a Session
     *
     * @return
     */
    public String getEmail() {
        return sharedPreferences.getString("Email", null);
    }

    /**
     * Gets the role value from a Session
     *
     * @return
     */
    public String getRole() {
        return sharedPreferences.getString("Role", null);
    }

    /**
     * Gets the OTP value from a Session
     *
     * @return
     */
    public int getOTP() {
        return sharedPreferences.getInt("OTP", -1);
    }

    /**
     * Gets the role value from a Session
     *
     * @return
     */
    public String getLocation() {
        return sharedPreferences.getString("Location", null);
    }

    /**
     * Returns whether the user is logged in based on the session
     *
     * @return
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean("LoggedIn", false);
    }

    /**
     * Creates a session
     *
     * @param name
     * @param email
     * @param role
     */
    public void createSession(String name, String email, String role) {
        editor.putBoolean("LoggedIn", true);
        editor.putString("Name", name);
        editor.putString("Email", email);
        editor.putString("Role", role);
        editor.apply();
    }

    /**
     * If user is logged in then redirects to appropriate page
     */
    public void accessControl() {
        Intent i;
        if (isLoggedIn()) {
            if (getRole().equals("Employee")) {
                i = new Intent(context, EmployeeHomeActivity.class);
            } else {
                i = new Intent(context, EmployerHomeActivity.class);
            }
            context.startActivity(i);
            ((Activity) context).finish();
        }
    }

    /**
     * Logs out the user from the app
     */
    public void clearSession() {
        this.editor.clear();
        this.editor.apply();
    }
}
