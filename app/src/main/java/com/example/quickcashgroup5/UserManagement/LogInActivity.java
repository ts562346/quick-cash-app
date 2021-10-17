package com.example.quickcashgroup5.UserManagement;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    Button  loginButton,
            notRegisteredUserLabel;
    EditText emailET,
            passwordET;
    FirebaseDatabase database;
    DatabaseReference users;
    SessionManagement sessionManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        sessionManagement.accessControl();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        emailET = (EditText)findViewById(R.id.editTextTextEmailAddress);
        passwordET = (EditText)findViewById(R.id.editTextTextPassword);
        notRegisteredUserLabel = (Button) findViewById(R.id.notRegisteredUserLabel);
        loginButton = (Button) findViewById(R.id.loginButton);

        notRegisteredUserLabel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this, SignUpActivity.class));
                ((Activity)LogInActivity.this).finish();
            }
        });

        loginButton.setOnClickListener(this);

        initializeDatabase();
    }

    protected String sanitize(String value) {
        return value.trim().replaceAll("\b", "");
    }

    protected boolean emailValidation(String email) {
        return !email.isEmpty();
    }

    protected boolean passwordValidation(String password) {
        return !password.isEmpty();
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
        users = database.getReference();
    }

    public void authenticateUser (String email, String password){
        AESCrypt aes= new AESCrypt();
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if(u.getEmail().equals(email)){
                        try {
                            if(u.getPassword().equals(aes.encrypt(password))){
                                if (u.getIsEmployee().equals("yes")) {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employee");
                                } else {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employer");
                                }
                            }else{
                                //Unsuccessful Login
                                Toast.makeText(getApplicationContext(),"Unsuccessful Login",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                if(!sessionManagement.isLoggedIn()){
                    //Unsuccessful Login
                    Toast.makeText(getApplicationContext(),"Unsuccessful Login",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Email does not exist",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onCancelled: Something went wrong! Error:" +databaseError.getMessage() );
            }
        });
    }

    @Override
    public void onClick(View view) {
        String email = sanitize(emailET.getText().toString());
        String password = sanitize(passwordET.getText().toString());
        if(emailValidation(email)) {
            if(passwordValidation(password)) {
                authenticateUser(email, password);
                sessionManagement.accessControl();
            } else{
                Toast.makeText(getApplicationContext(),"Password field is empty",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Email field is empty",Toast.LENGTH_SHORT).show();
        }

    }



}
