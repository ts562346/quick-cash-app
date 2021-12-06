package com.example.quickcashgroup5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });
    }
}
