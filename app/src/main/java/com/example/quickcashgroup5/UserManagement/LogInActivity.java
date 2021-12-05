package com.example.quickcashgroup5.UserManagement;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.PasswordManagement.AESCrypt;
import com.example.quickcashgroup5.PasswordManagement.RecoveryAccountActivity;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Activity for logging in user
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    //Initializing Variables
    Button loginButton, forgotPassword, notRegisteredUserLabel;
    EditText emailEditText, passwordEditText;
    FirebaseDatabase database;
    DatabaseReference users;
    SessionManagement sessionManagement;

    /**
     * Method that runs when activity created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("LoginActivitySessionManagment");
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        System.out.println("LoginActivity");
        System.out.println(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        notRegisteredUserLabel = findViewById(R.id.notRegisteredUserLabel);
        forgotPassword = findViewById(R.id.forgotPass);
        loginButton = findViewById(R.id.loginButton);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, RecoveryAccountActivity.class));
            }
        });

        notRegisteredUserLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
                LogInActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(this);

        initializeDatabase();
    }

    /**
     * Sanitizes string input
     *
     * @param value
     * @return
     */
    public String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    /**
     * Validates email
     *
     * @param email
     * @return
     */
    public boolean emailValidation(String email) {
        return !email.isEmpty();
    }

    /**
     * Validates password
     *
     * @param password
     * @return
     */
    public static boolean passwordValidation(String password) {
        return !password.isEmpty();
    }

    /**
     * Initializes Database
     */
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        users = database.getReference();
    }

    /**
     * Logs in the user if he/she has an account
     *
     * @param email
     * @param password
     */
    public void authenticateUser(String email, String password) {
        AESCrypt aes = new AESCrypt();
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if (u.getEmail().equals(email)) {
                        try {
                            if (u.getPassword().equals(AESCrypt.encrypt(password))) {
                                if (u.getIsEmployee().equals("yes")) {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employee");
                                } else {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employer");
                                }
                            } else {
                                //Unsuccessful Login
                                Toast.makeText(getApplicationContext(), "Unsuccessful Login", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                if (!sessionManagement.isLoggedIn()) {
                    //Unsuccessful Login
                    Toast.makeText(getApplicationContext(), "Unsuccessful Login", Toast.LENGTH_SHORT).show();
                } else {
                    sessionManagement.accessControl();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Email does not exist", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onCancelled: Something went wrong! Error:" + databaseError.getMessage());
            }
        });
    }

    /**
     * onClick method for Login button
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        String email = sanitize(emailEditText.getText().toString());
        String password = sanitize(passwordEditText.getText().toString());
        if (emailValidation(email)) {
            if (passwordValidation(password)) {
                authenticateUser(email, password);
            } else {
                Toast.makeText(getApplicationContext(), "Password field is empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Email field is empty", Toast.LENGTH_SHORT).show();
        }

    }


}
