package com.example.quickcashgroup5.home;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.feedbackmanagement.ViewFeedbacksActivity;
import com.example.quickcashgroup5.jobsearch.JobSearchActivity;
import com.example.quickcashgroup5.usermanagement.JobPreferenceActivity;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.android.material.navigation.NavigationView;


/**
 * Home page for Employee
 */
public class EmployeeHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManagement sessionManagement;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;
    TextView title;

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
        title = findViewById(R.id.title);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sidebar.setNavigationItemSelectedListener(this);
        title.setText(sessionManagement.getName());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainerEmployee, new FragmentDashboard()).commit();


    }

    /**
     * To open and close the navigation drawer when the icon is clicked
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer

    /**
     * Navigation bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, EmployeeHomeActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_searchJob: {
                Intent intent = new Intent(this, JobSearchActivity.class);
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
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            }
            default: {
                Log.d(TAG, "Asyncronous method canceled");
            }
        }

        return true;
    }
}
