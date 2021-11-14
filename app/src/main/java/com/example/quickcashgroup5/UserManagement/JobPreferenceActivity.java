package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.Map;


public class JobPreferenceActivity extends AppCompatActivity {
    SessionManagement sessionManagement;
    FirebaseDatabase database;
    DatabaseReference users;

    Spinner category;
    EditText location, minPayment, minHours;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManagement = new SessionManagement(this);
        if (sessionManagement.getRole().equals("Employer")) {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobpreference);
        category = findViewById(R.id.jobCategory);
        location = findViewById(R.id.editTextLocation);
        minPayment = findViewById(R.id.editTextMinPay);
        minHours = findViewById(R.id.editTextMinHours);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePreference(); // calls method to update DB with required fields
                // once submit button is clicked, redirect to EmployeeHomeActivity
                startActivity(new Intent(JobPreferenceActivity.this, EmployeeHomeActivity.class));
            }
        });


    }

    public static boolean locationValidation(String name) {
        if (!name.isEmpty()) {
            //Location can only contain letters and whitespace
            return name.matches("^[A-Za-z\\s]+$");
        } else {
            return false;
        }
    }

    public void updatePreference() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        DatabaseReference users = database.getReference();
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);

                    String userEmail = u.getEmail();
                    String sessionEmail = sessionManagement.getEmail();

                    System.out.println("User" + userEmail);
                    System.out.println(" Session" + sessionEmail);
                    System.out.println(userEmail.equals(sessionEmail));


                    if (userEmail.equals(sessionEmail)) {
                        System.out.println("entered if statement");
                        try {
                            System.out.print("entered try clause");

                            /**
                             * Added location, minPayment and minHours to
                             * the database of user.
                             */

                            Map<String, Object> updates = new HashMap<String, Object>();
                            updates.put("preferredLocation", location.getText().toString());
                            adSnapshot.getRef().updateChildren(updates);

                            updates = new HashMap<String, Object>();
                            updates.put("preferredPayment", minPayment.toString());
                            adSnapshot.getRef().updateChildren(updates);

                            updates = new HashMap<String, Object>();
                            updates.put("preferredHours", minHours.toString());
                            adSnapshot.getRef().child("preferredLocation").setValue(updates);

//                            users.child("preferredLocation").setValue(location.getText().toString());


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }break;


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("HELLO");
            }


        });
    }
}

//    public void updatePreference() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
//        DatabaseReference users = database.getReference();
//
//        String userEmail = sessionManagement.getEmail();
//
//        User user = new User();
//        user.setEmail("test@gmail,com");
//        user.setPreferredLocation(location.getText().toString());
//        user.setEmail("test@gmail,com");
//
//
//        users.child("test@gmail,com").updateChildren(postValues);
//
//        users.child("User").child("preferredLocation").setValue(location.getText().toString());
//
//    }
//}