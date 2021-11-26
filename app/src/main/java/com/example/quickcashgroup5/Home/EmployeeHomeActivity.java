package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.RecoveryAccountActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;
import com.google.android.material.navigation.NavigationView;


/**
 * Home page for Employee
 */

public class EmployeeHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout, jobPreference;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;

    /**
     * Method that runs when activity is created
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeehome);
        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    // To open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
