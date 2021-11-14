package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.RecoveryAccountActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;


/**
 * Home page for Employee
 */

public class EmployeeHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout, jobPreference;

    /**
     * Method that runs when activity is created
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_employeehome);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);

        jobPreference = (Button) findViewById(R.id.jobPreference);
        jobPreference.setOnClickListener(this);

    }

    /**
     * Onclick method for logging out
     *
     * @param view
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                sessionManagement.logout();
                break;
            case R.id.jobPreference:
                Log.i("Hello", "hi");
                startActivity(new Intent(getApplicationContext(), JobPreferenceActivity.class));
        }
    }
}
