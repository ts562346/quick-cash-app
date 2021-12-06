package com.example.quickcashgroup5.passwordmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickcashgroup5.datavalidation.Validation;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;

public class PasswordResetCodeActivity extends Activity implements View.OnClickListener{
    private EditText editTextCode;
    private Button buttonSend;
    private Button buttonBackToLogin;
    private Button buttonCodeNotReceived;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordresetcode);
        editTextCode = findViewById(R.id.code);
        buttonSend = findViewById(R.id.sendButton);
        buttonBackToLogin = findViewById((R.id.backToLogin));
        buttonCodeNotReceived = findViewById((R.id.codeNotReceived));

        buttonCodeNotReceived.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PasswordResetCodeActivity.this, RecoveryAccountActivity.class));
                PasswordResetCodeActivity.this.finish();
            }
        });
        buttonBackToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(PasswordResetCodeActivity.this, LogInActivity.class));
                PasswordResetCodeActivity.this.finish();
            }
        });
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String input = Validation.sanitize(editTextCode.getText().toString());
        if(Validation.otpValidation(input)) {
            int inputCode = Integer.parseInt(input);
            int resetCode = sessionManagement.getOTP();
            if (inputCode == resetCode) {
                Bundle bundle = getIntent().getExtras();
                String email = bundle.getString("email");
                Intent intent = new Intent(PasswordResetCodeActivity.this, ResetPasswordActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                this.finish();
            } else {
                Toast.makeText(this, "The OTP you have entered is not valid", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "The OTP should be exact 4 numbers", Toast.LENGTH_LONG);
        }
    }
}
