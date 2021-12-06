package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.DataValidation.Validation;
import com.example.quickcashgroup5.DatabaseManagement.Database;
import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.PasswordManagement.AESCrypt;
import com.example.quickcashgroup5.PasswordManagement.RecoveryAccountActivity;
import com.example.quickcashgroup5.R;

/**
 * Activity for logging in user
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    //Initializing Variables
    Button loginButton, forgotPassword, notRegisteredUserLabel;
    EditText emailEditText, passwordEditText;
    Database database;
    ISessionManagement sessionManagement;

    /**
     * Method that runs when activity created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IUserManagementAbstractFactory userManagementAbstractFactory =
                UserManagementInjector.getInstance().getUserAbstractFactory();
        sessionManagement = userManagementAbstractFactory.
                getSessionManagementInstance(this);

        initializeActivity(userManagementAbstractFactory);
    }

    private void initializeActivity(IUserManagementAbstractFactory
                                            userManagementAbstractFactory) {
        getSupportActionBar().hide();
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        notRegisteredUserLabel = findViewById(R.id.notRegisteredUserLabel);
        forgotPassword = findViewById(R.id.forgotPass);
        loginButton = findViewById(R.id.loginButton);

        database = new Database();

        forgotPassword.setOnClickListener(v -> startActivity(userManagementAbstractFactory.
                getIntentInstance(LogInActivity.this,
                        RecoveryAccountActivity.class)));
        notRegisteredUserLabel.setOnClickListener(v -> {
            startActivity(userManagementAbstractFactory.
                    getIntentInstance(LogInActivity.this,
                            SignUpActivity.class));
            LogInActivity.this.finish();
        });
        loginButton.setOnClickListener(this);
    }


    public void authenticateUser(String email, String password) {
        User u = database.findUser(email);
        if (u == null) {
            Toast.makeText(getApplicationContext(), "Unsuccessful Login", Toast.LENGTH_SHORT).show();
        } else {
            try {
                if (u.getPassword().equals(AESCrypt.encrypt(password))) {
                    Intent i;
                    if (u.getIsEmployee().equals("yes")) {
                        sessionManagement.createSession(u.getName(), u.getEmail(), "Employee");
                        i = new Intent(this, EmployeeHomeActivity.class);
                    } else {
                        sessionManagement.createSession(u.getName(), u.getEmail(), "Employer");
                        i = new Intent(this, EmployerHomeActivity.class);
                    }
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    //Unsuccessful Login
                    Toast.makeText(getApplicationContext(), "Your email or password don't match", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * onClick method for Login button
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        String email = Validation.sanitize(emailEditText.getText().toString());
        String password = Validation.sanitize(passwordEditText.getText().toString());
        if (Validation.emailValidation(email)) {
            if (Validation.passwordValidation(password)) {
                authenticateUser(email, password);
            } else {
                Toast.makeText(getApplicationContext(), "Password should have at least 1 number, 1 uppercase, 1 lowercase, 1 special character, and must be atleast 8 characters.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        }
        authenticateUser(email, password);
    }


}
