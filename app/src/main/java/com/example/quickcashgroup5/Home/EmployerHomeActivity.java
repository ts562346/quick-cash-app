package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.FragmentDashboard;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.CreateJob;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

/**
 * Home page for Employer
 */

public class EmployerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
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
        getSupportActionBar().hide();
        setContentView(R.layout.activity_employerhome);
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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, new FragmentDashboard(sessionManagement)).commit();
    }



    /**
     * OnClick method for logging outs
     * @param view
     */
    public void onClick(View view) {
        sessionManagement.logout();
    }
}
