package com.example.quickcashgroup5.UserManagement;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.PasswordManagement.AESCrypt;
import com.example.quickcashgroup5.PasswordManagement.IAESCrypt;
import com.example.quickcashgroup5.PasswordManagement.IPasswordManagementAbstractFactory;
import com.example.quickcashgroup5.PasswordManagement.PasswordManagementInjector;
import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * SignUpActivity class
 * Used to allow the user to register for the app
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    //Initializes the variables
    Button registerButton, registeredUserLabel;
    EditText nameEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
    DatabaseReference users = database.getReference(User.class.getSimpleName());
    Spinner dropDown;
    ISessionManagement sessionManagement;

    boolean userExists;

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
        userExists = false;

        registeredUserLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                SignUpActivity.this.finish();
            }
        });
        registerButton.setOnClickListener(this);

        initializeDatabase();

    }

    /**
     * Sanitizes string inputs
     *
     * @param value
     * @return
     */
    protected String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    /**
     * Validate username
     *
     * @param name
     * @return
     */
    public static boolean usernameValidation(String name) {
        if (!name.isEmpty()) {
            //Username can only contain letters and whitespace
            return name.matches("^[A-Za-z\\s]+$");
        } else {
            return false;
        }
    }

    /**
     * Validates email
     *
     * @param email
     * @return
     */
    public static boolean emailValidation(String email) {
        if (!email.isEmpty()) {
            //The first part of the email can only contain letters, digits, and periods
            //The second and third part can only contain letters
            //The third part can only be between 2 - 3 characters long
            return email.matches("^[a-zA-Z\\d\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
        } else {
            return false;
        }
    }

    /**
     * Validates password
     *
     * @param password
     * @return
     */
    public static boolean passwordValidation(String password) {
        if (!password.isEmpty()) {
            //Password should have at least 1 number, 1 uppercase, 1 lowercase, and 1 special character
            //The password should be at least 8 characters long
            return password.matches("^(?=.*?\\d+)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\\W).{8,}$");
        } else {
            return false;
        }
    }

    /**
     * Validates confirm password
     *
     * @param password
     * @param confirmPassword
     * @return
     */
    public static boolean confirmPasswordValidation(String password, String confirmPassword) {
        if (!password.isEmpty() && !confirmPassword.isEmpty()) {
            //Password should be equal to confirmPassword
            return password.equals(confirmPassword);
        } else {
            return false;
        }
    }

    private boolean registerUser(IUser user) throws Exception {
        IUserManagementAbstractFactory userManagementAbstractFactory = UserManagementInjector.
                getInstance().getUserAbstractFactory();
        IPasswordManagementAbstractFactory passwordManagementAbstractFactory =
                PasswordManagementInjector.getInstance().getPasswordManagementAbstractFactory();
        String username = sanitize(nameEditText.getText().toString());
        String email = sanitize(emailEditText.getText().toString());
        String password = sanitize(passwordEditText.getText().toString());
        String confirmPassword = sanitize(confirmPasswordEditText.getText().toString());
        IAESCrypt aes = passwordManagementAbstractFactory.getAesCryptInstance();




        //Check if email is already used
        if (isPreviousUser(email)) {
            Toast.makeText(getApplicationContext(), "Email has already been registered.", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Validate username
        if (usernameValidation(username)) {
            user.setName(username);
        } else {
            Toast.makeText(getApplicationContext(), "Username can only contain letters and whitespace", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Validate email
        if (emailValidation(email)) {
            user.setEmail(email);
        } else {
            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Validate confirmPassword
        if (confirmPasswordValidation(password, confirmPassword)) {
            //Validate password
            if (passwordValidation(password)) {
                user.setPassword(aes.encrypt(password));
            } else {
                Toast.makeText(getApplicationContext(), "Password should have at least 1 number, 1 uppercase, 1 lowercase, 1 special character, and must be atleast 8 characters.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Password and Confirm Password should match", Toast.LENGTH_SHORT).show();
            return false;
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

        return true;
    }

    /**
     * Checks if the user's email was previously registered
     *
     * @param email
     * @return
     */
    public boolean isPreviousUser(String email) {
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if (u.getEmail().equals(email)) {
                        System.out.println("Old User");
                        userExists = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("New User");
            }
        });
        return userExists;
    }

    /**
     * Initializes the firebase database
     */
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        users = database.getReference(User.class.getSimpleName());
    }

    /**
     * Adds user object to the Firebase Database
     *
     * @param user
     * @return
     */
    public Task<Void> add(User user) {
        return users.push().setValue(user);
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
            if (registerUser(user)) {
                this.add(user).addOnSuccessListener(suc -> {
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
