package com.example.quickcashgroup5.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

public class EmployeeHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement sessionManagement;
    Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_employeehome);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);

    }

    public void onClick(View view) {
        sessionManagement.logout();
    }
}
