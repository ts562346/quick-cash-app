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

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    Button  loginButton;
    EditText email,
            password;
    FirebaseDatabase database;
    DatabaseReference users;
    SessionManagement sessionManagement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        sessionManagement = new SessionManagement(this);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this);

        initializeDatabase();
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
        users = database.getReference();
    }

    public void authenticateUser (){
        AESCrypt aes= new AESCrypt();
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot: dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if(u.getEmail().equals(email.getText().toString())){
                        try {
                            if(u.getPassword().equals(aes.encrypt(password.getText().toString()))){
                                if (u.getIsEmployee().equals("yes")) {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employee");
                                } else {
                                    sessionManagement.createSession(u.getName(), u.getEmail(), "Employer");
                                }
                            }else{
                                //Unsuccessful Login
                                System.out.println("bad");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        //Unsuccessful Login
                        System.out.println("bad");
                    }
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

        if(!email.getText().toString().isEmpty()) {
            authenticateUser();
            sessionManagement.accessControl();
        }else{
            Toast.makeText(getApplicationContext(),"Email field is empty",Toast.LENGTH_SHORT).show();
        }

    }



}
