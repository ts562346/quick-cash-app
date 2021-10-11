package com.example.quickcashgroup5.UserManagment;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;

public class LogInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    protected String getEmail() {
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        return email.getText().toString().trim();
    }

    protected String getPassword() {
        EditText password = findViewById(R.id.editTextTextPassword);
        return password.getText().toString().trim();
    }

    protected String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    protected boolean emailVerification(String email) {
        String sanitized = sanitize(email);
        if (!sanitized.isEmpty()){
            //The first part of the email can only contain letters, digits, and periods
            //The second and third part can only contain letters
            //The third part can only be between 2 - 3 characters long
            return sanitized.matches("^[a-zA-Z\\d\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
        } else {
            return false;
        }
    }

    protected boolean passwordVerification(String password) {
        String sanitized = sanitize(password);
        if (!sanitized.isEmpty()){
            //Password should have at least 1 number, 1 uppercase, 1 lowercase, and 1 special character
            //The password should be at least 8 characters long
            return sanitized.matches("^(?=.*?\\d+)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\\W).{8,}$");
        } else {
            return false;
        }
    }

}
