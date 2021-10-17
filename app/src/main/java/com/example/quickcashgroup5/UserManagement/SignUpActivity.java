package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    Button  registerButton;
    EditText nameET,
            emailET,
            passwordET,
            confirmPasswordET;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
    DatabaseReference users = database.getReference(User.class.getSimpleName());
    Spinner dropDown;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerButton = (Button)findViewById(R.id.registerButton);
        nameET = (EditText)findViewById(R.id.editTextUsername);
        emailET = (EditText)findViewById(R.id.editTextTextEmailAddress);
        passwordET = (EditText)findViewById(R.id.editTextTextPassword);
        confirmPasswordET = (EditText)findViewById(R.id.editTextTextConfirmPassword);
        registerButton.setOnClickListener(this);
        dropDown = (Spinner)findViewById(R.id.spin);

        initializeDatabase();

    }

    protected String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    protected boolean usernameValidation(String name) {
        if (!name.isEmpty()){
            //Username can only contain letters and whitespace
            return name.matches("^[A-Za-z\\s]+$");
        } else {
            return false;
        }
    }

    protected boolean emailValidation(String email) {
        if (!email.isEmpty()){
            //The first part of the email can only contain letters, digits, and periods
            //The second and third part can only contain letters
            //The third part can only be between 2 - 3 characters long
            return email.matches("^[a-zA-Z\\d\\.]+@[a-zA-Z]+\\.[a-zA-Z]{2,3}$");
        } else {
            return false;
        }
    }

    protected boolean passwordValidation(String password) {
        if (!password.isEmpty()){
            //Password should have at least 1 number, 1 uppercase, 1 lowercase, and 1 special character
            //The password should be at least 8 characters long
            return password.matches("^(?=.*?\\d+)(?=.*?[A-Z])(?=.*?[a-z])(?=.*?\\W).{8,}$");
        } else {
            return false;
        }
    }

    protected boolean confirmPasswordValidation(String password, String confirmPassword) {
        if (!password.isEmpty() && !confirmPassword.isEmpty()){
            //Password should be equal to confirmPassword
            return password.equals(confirmPassword);
        } else {
            return false;
        }
    }

    private boolean registerUser(User user) throws Exception {
        String username = sanitize(nameET.getText().toString());
        String email = sanitize(emailET.getText().toString());
        String password = sanitize(passwordET.getText().toString());
        String confirmPassword = sanitize(confirmPasswordET.getText().toString());

        AESCrypt aes = new AESCrypt();
        //Validate username
        if(usernameValidation(username)){
            user.setName(username);
        }else{
            Toast.makeText(getApplicationContext(),"Username can only contain letters and whitespace",Toast.LENGTH_SHORT).show();
            return false;
        }

        //Validate email
        if(emailValidation(email)){
            user.setEmail(email);
        }else{
            Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            return false;
        }

        //Validate confirmPassword
        if(confirmPasswordValidation(password, confirmPassword)){
            //Validate password
            if(passwordValidation(password)){
                user.setPassword(aes.encrypt(password));
            }else{
                Toast.makeText(getApplicationContext(),"Password should have at least 1 number, 1 uppercase, 1 lowercase, and 1 special character",Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(),"Password and Confirm Password should match",Toast.LENGTH_SHORT).show();
            return false;
        }

        String itemText = (String) dropDown.getSelectedItem();
        if(itemText.equals("Employer")){
            user.setIsEmployee("no");
        }else{
            user.setIsEmployee("yes");
        }

        return true;
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
        users = database.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user){
        return users.push().setValue(user);
    }

    @Override
    public void onClick(View view) {
        User user= new User();
        try {
            if(registerUser(user)){
                this.add(user).addOnSuccessListener(suc ->{
                    Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, SignUpActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(fal -> {
                    Toast.makeText(this, "Data Insertion failed", Toast.LENGTH_SHORT).show();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
