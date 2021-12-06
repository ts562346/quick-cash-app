package com.example.quickcashgroup5.UserManagement;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.DataValidation.Validation;
import com.example.quickcashgroup5.DatabaseManagement.Database;
import com.example.quickcashgroup5.PasswordManagement.AESCrypt;
import com.example.quickcashgroup5.PasswordManagement.IAESCrypt;
import com.example.quickcashgroup5.PasswordManagement.IPasswordManagementAbstractFactory;
import com.example.quickcashgroup5.PasswordManagement.PasswordManagementInjector;
import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;

/**
 * SignUpActivity class
 * Used to allow the user to register for the app
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    //Initializes the variables
    Button registerButton, registeredUserLabel;
    EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    Database database;
    Spinner dropDown;
    SessionManagement sessionManagement;

    /**
     * Method runs when activity is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signup);

        registeredUserLabel = findViewById(R.id.registeredUserLabel);
        registerButton = findViewById(R.id.registerButton);
        nameEditText = findViewById(R.id.editTextUsername);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextTextConfirmPassword);
        dropDown = findViewById(R.id.spin);

        database = new Database();

        registeredUserLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                SignUpActivity.this.finish();
            }
        });
        registerButton.setOnClickListener(this);
    }

    /**
     * Registers the user based on the inputs
     *
     * @param user
     * @return
     * @throws Exception
     */
    private Task<Void> registerUser(IUser user) throws Exception {
        IUserManagementAbstractFactory userManagementAbstractFactory = UserManagementInjector.
                getInstance().getUserAbstractFactory();
//        IPasswordManagementAbstractFactory passwordManagementAbstractFactory =
//                PasswordManagementInjector.getInstance().getPasswordManagementAbstractFactory();

        String username = Validation.sanitize(nameEditText.getText().toString());
        String email = Validation.sanitize(emailEditText.getText().toString());
        String password = Validation.sanitize(passwordEditText.getText().toString());
        String confirmPassword = Validation.sanitize(confirmPasswordEditText.getText().toString());

//        IAESCrypt aes = passwordManagementAbstractFactory.getAesCryptInstance();


        //Check if email is already used
        if (database.findUser(email) != null) {
            Toast.makeText(getApplicationContext(), "Email has already been registered.", Toast.LENGTH_SHORT).show();
            return null;
        }

        //Validate username
        if (Validation.fullNameValidation(username)) {
            user.setName(username);
        } else {
            Toast.makeText(getApplicationContext(), "Username can only contain letters and whitespace", Toast.LENGTH_SHORT).show();
            return null;
        }

        //Validate email
        if (Validation.emailValidation(email)) {
            user.setEmail(email);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return null;
        }

        //Validate confirmPassword
        if (Validation.confirmPasswordValidation(password, confirmPassword)) {
            //Validate password
            if (Validation.passwordValidation(password)) {
                user.setPassword(AESCrypt.encrypt(password));
            } else {
                Toast.makeText(getApplicationContext(), "Password should have at least 1 number, 1 uppercase, 1 lowercase, 1 special character, and must be atleast 8 characters.", Toast.LENGTH_SHORT).show();
                return null;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Password and Confirm Password should match", Toast.LENGTH_SHORT).show();
            return null;
        }

        String itemText = (String) dropDown.getSelectedItem();
        if (itemText.equals("Employer")) {
            user.setIsEmployee("no");
        } else {
            user.setIsEmployee("yes");
        }

        user.setPreferredCategory("");
        user.setPreferredHours("");
        user.setPreferredLocation("");
        user.setPreferredPayment("");

        return database.addUser(user);
    }

    /**
     * onClick method for SignUp button
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        User user = new User();
        try {
            Task<Void> task = registerUser(user);
            if (task != null) {
                task.addOnSuccessListener(suc -> {
                    sessionManagement.clearSession();
                    Toast.makeText(this, "Successful SignUp", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(intent).startActivities();
                }).addOnFailureListener(fal -> {
                    Toast.makeText(this, "Unsuccessful SignUp", Toast.LENGTH_SHORT).show();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
