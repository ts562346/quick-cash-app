package com.example.quickcashgroup5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quickcashgroup5.UserManagement.LogInActivity;
import com.example.quickcashgroup5.UserManagement.SignUpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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


    }
}
