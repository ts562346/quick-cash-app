package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                    System.out.print("User" + u.getEmail());
                    System.out.println("Session" + sessionManagement.getEmail() + "hello");
                    if (u.getEmail().equals(sessionManagement.getEmail())) {
                        System.out.print(u.getEmail());
                        try {
                            System.out.print(u.getEmail());

                            /**
                             * Added location, minPayment and minHours to
                             * the database of user.
                             */

                            Map<String, Object> updates = new HashMap<String, Object>();
                            updates.put("preferredLocation", location);
                            adSnapshot.getRef().updateChildren(updates);

                            updates = new HashMap<String, Object>();
                            updates.put("preferredLocation", minPayment);
                            adSnapshot.getRef().updateChildren(updates);

                            updates = new HashMap<String, Object>();
                            updates.put("preferredLocation", minHours);
                            adSnapshot.getRef().updateChildren(updates);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("HELLO");
            }


        });
    }
}



