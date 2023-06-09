//Code adapted from https://www.tutorialspoint.com/how-to-send-email-on-android-using-javamail-api

package com.example.quickcashgroup5.passwordmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.datavalidation.Validation;
import com.example.quickcashgroup5.emailmanagement.SendOTP;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;

import java.util.Random;

/**
 * RecoveryAccountActivity
 */
public class RecoveryAccountActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private Button buttonSend;
    private Button buttonBackToLogin;
    SessionManagement sessionManagement;

    /**
     * Runs when created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recoveryaccount);
        getSupportActionBar().hide();
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        buttonSend = findViewById(R.id.sendButton);
        buttonBackToLogin = findViewById((R.id.backToLogin));

        buttonBackToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RecoveryAccountActivity.this, LogInActivity.class));
            RecoveryAccountActivity.this.finish();
        });
        buttonSend.setOnClickListener(this);
    }

    /**
     * OnClick Method
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Random rand = new Random();
        final int resetCode = (rand.nextInt(8888) + 1111);
        sessionManagement.setOTP(resetCode);
        String email = Validation.sanitize(editTextEmail.getText().toString());
        if (Validation.emailValidation(email)) {
            String subject = "QuickCash password reset code";
            String message = "You password reset code is " + resetCode;
            SendOTP sm = new SendOTP(this, email, subject, message);
            sm.execute();
        } else {
            Toast.makeText(getApplicationContext(), "Your email is invalid", Toast.LENGTH_LONG).show();
        }
    }

}

