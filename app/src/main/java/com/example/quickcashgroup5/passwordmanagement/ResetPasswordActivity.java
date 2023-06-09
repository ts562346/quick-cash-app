package com.example.quickcashgroup5.passwordmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.databasemanagement.Database;
import com.example.quickcashgroup5.datavalidation.Validation;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;

import java.util.HashMap;
import java.util.Map;

/**
 * ResetPasswordActivity
 */
public class ResetPasswordActivity extends Activity implements View.OnClickListener {
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonReset;
    private Button buttonBackToLogin;
    SessionManagement sessionManagement;
    String email;
    Database database;

    /**
     * Runs when created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirmPassword);
        buttonReset = findViewById(R.id.resetButton);
        buttonReset.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        buttonBackToLogin = findViewById((R.id.backToLogin));

        buttonBackToLogin.setOnClickListener(v -> {
            startActivity(new Intent(ResetPasswordActivity.this, LogInActivity.class));
            ResetPasswordActivity.this.finish();
        });
    }

    /**
     * OnClick Method
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        String password = Validation.sanitize(editTextPassword.getText().toString());
        String confirmPassword = Validation.sanitize(editTextConfirmPassword.getText().toString());

        if (Validation.confirmPasswordValidation(password, confirmPassword)) {
            if (Validation.passwordValidation(password)) {
                changePassword(password);
                Toast.makeText(this, "Your password has successfully been reset", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LogInActivity.class));
                this.finish();
            } else {
                Toast.makeText(this, "Password should have at least 1 number, 1 uppercase, 1 lowercase, 1 special character, and must be atleast 8 characters.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Your password should match the confirm password", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Changes Password
     *
     * @param newPassword
     */
    public void changePassword(String newPassword) {
        AESCrypt aes = new AESCrypt();
        try {
            Map<String, Object> updates = new HashMap<>();
            updates.put("email", aes.encrypt(newPassword));
            updates.put("password", aes.encrypt(newPassword));
            database.updateUser(updates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
