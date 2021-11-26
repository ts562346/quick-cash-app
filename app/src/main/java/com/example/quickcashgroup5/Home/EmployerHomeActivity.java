package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.CreateJob;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.android.material.navigation.NavigationView;

/**
 * Home page for Employer
 */

public class EmployerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;
    Button logout;
    Button buttonCreateJob;

    /**
     * Mehtod that runs when activity is created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employerhome);
        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        buttonCreateJob = (Button)findViewById(R.id.createJob);
        logout = (Button)findViewById(R.id.logout);

        buttonCreateJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EmployerHomeActivity.this, CreateJob.class);
                startActivity(intent);
                ((Activity) EmployerHomeActivity.this).finish();
            }
        });

        logout.setOnClickListener(this);
    }

    /**
     * OnClick method for logging outs
     * @param view
     */
    public void onClick(View view) {
        sessionManagement.logout();
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
