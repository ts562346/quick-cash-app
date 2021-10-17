package com.example.quickcashgroup5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;

import com.example.quickcashgroup5.UserManagement.LogInActivity;
//import com.example.quickcashgroup5.UserManagement.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setTheme(R.style.Theme_AppCompat_Light);
        setContentView(R.layout.activity_main);
        Button switchButton = findViewById(R.id.button);

        switchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
//        alertBuilder.setMessage("Factorial: ");
//        alertBuilder.setPositiveButton("OK", null);
//        alertBuilder.create();
//        alertBuilder.show();
        Intent i = new Intent(getApplicationContext(), LogInActivity.class);
        startActivity(i);

        Button signupButton = findViewById(R.id.signup);
        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

    }
}
