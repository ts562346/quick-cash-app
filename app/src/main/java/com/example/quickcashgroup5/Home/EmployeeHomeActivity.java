package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.FragmentDashboard;
import com.example.quickcashgroup5.JobSearchActivity;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;


/**
 * Home page for Employee
 */

public class EmployeeHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout, jobPreference, jobSearch;

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

        jobSearch = (Button) findViewById(R.id.jobSearch);
        jobSearch.setOnClickListener(this);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainerEmployee, new FragmentDashboard(sessionManagement)).commit();

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
                startActivity(new Intent(getApplicationContext(), JobPreferenceActivity.class));
                break;
            case R.id.jobSearch:
                startActivity(new Intent(getApplicationContext(), JobSearchActivity.class));

                break;
        }
    }
}
