package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.quickcashgroup5.FragmentDashboard;
import com.example.quickcashgroup5.FragmentSelectEmployee;
import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.CreateJob;
import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;
import com.example.quickcashgroup5.UserManagement.ViewFeedbacksActivity;
import com.google.android.material.navigation.NavigationView;

/**
 * Home page for Employer
 */

public class EmployerHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManagement sessionManagement;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;
    TextView title;

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
        title = findViewById(R.id.title);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sidebar.setNavigationItemSelectedListener(this);
        title.setText(sessionManagement.getName());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FragmentDashboard f = new FragmentDashboard();
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainContainer, f, "employer dashboard");
        ft.commit();

        Button refresh = findViewById(R.id.refresh_button);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                FragmentDashboard f = new FragmentDashboard();
                FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.mainContainer, f, "employer dashboard");
                ft.commit();
            }
        });
    }

    // To open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, EmployerHomeActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_createJob: {
                Intent intent = new Intent(this, CreateJob.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_preferences: {
                Intent intent = new Intent(this, JobPreferenceActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_feedback: {
                Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_logout: {
                sessionManagement.clearSession();
                Intent intent = new Intent(this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            }
        }

        return true;
    }
}
