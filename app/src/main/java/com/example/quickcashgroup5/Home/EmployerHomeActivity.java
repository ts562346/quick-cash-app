package com.example.quickcashgroup5.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

public class EmployerHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SessionManagement session;
    Button logout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeehome);
        session = new SessionManagement(this);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(this);

    }


    public void onClick(View view) {
        session.logout();
    }
}
