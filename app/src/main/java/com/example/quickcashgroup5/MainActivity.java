package com.example.quickcashgroup5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.example.quickcashgroup5.usermanagement.SignUpActivity;

/**
 * The starting page from where the user can select whether to login or sign up
 */

public class MainActivity extends AppCompatActivity {
    SessionManagement sessionManagement;

    /**
     * Method that runs when activity is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        sessionManagement.accessControl();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setTheme(R.style.Theme_AppCompat_Light);
        setContentView(R.layout.activity_main);

        Button signupButton = findViewById(R.id.signup);
        signupButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignUpActivity.class)));

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LogInActivity.class)));
    }
}
