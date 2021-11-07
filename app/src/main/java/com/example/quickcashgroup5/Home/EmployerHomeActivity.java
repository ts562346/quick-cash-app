package com.example.quickcashgroup5.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

/**
 * Home page for Employer
 */

public class EmployerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout;

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
        logout = (Button)findViewById(R.id.logout);
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
