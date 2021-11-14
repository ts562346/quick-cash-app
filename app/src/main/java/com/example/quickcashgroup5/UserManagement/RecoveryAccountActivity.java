//Code adapted from https://www.tutorialspoint.com/how-to-send-email-on-android-using-javamail-api

package com.example.quickcashgroup5.UserManagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.SendMail;

import java.util.Random;

public class RecoveryAccountActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail;
    private Button buttonSend;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoveryaccount);
        getSupportActionBar().hide();
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        buttonSend = (Button) findViewById(R.id.sendButton);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final int resetCode = (new Random().nextInt(8888) + 1111);
        sessionManagement.setOTP(resetCode);
        String email = editTextEmail.getText().toString().trim();
        String subject = "QuickCash password reset code";
        String message = "You password reset code is " + resetCode;
        SendMail sm = new SendMail(this, email, subject, message);
        sm.execute();
    }

}

