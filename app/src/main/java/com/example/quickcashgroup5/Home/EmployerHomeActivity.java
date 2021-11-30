package com.example.quickcashgroup5.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.CreateJob;
import com.example.quickcashgroup5.UserManagement.SendFeedbackActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

/**
 * Home page for Employer
 */

public class EmployerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout;
    Button buttonCreateJob;
    Button buttonFeedback;
    TextView title;

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
        title = (TextView)findViewById(R.id.title);
        buttonCreateJob = (Button)findViewById(R.id.createJob);
        buttonFeedback = (Button)findViewById(R.id.feedback);
        logout = (Button)findViewById(R.id.logout);

        title.setText(sessionManagement.getName());

        buttonCreateJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EmployerHomeActivity.this, CreateJob.class);
                startActivity(intent);
                ((Activity) EmployerHomeActivity.this).finish();
            }
        });

        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(EmployerHomeActivity.this, ViewFeedbacks.class);
                Intent intent = new Intent(EmployerHomeActivity.this, SendFeedbackActivity.class);
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
}
