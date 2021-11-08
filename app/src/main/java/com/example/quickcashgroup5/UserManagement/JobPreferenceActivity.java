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
        if(sessionManagement.getRole().equals("Employer")){
            startActivity(new Intent(getApplicationContext(),LogInActivity.class));
        }

        super.onCreate(savedInstanceState);
        // getSupportActionBar().hide();
        setContentView(R.layout.activity_jobpreference);
        Log.i("JobPreferneceActivity", "Reached here");
        category = findViewById(R.id.jobCategory);
        location = findViewById(R.id.editTextLocation);
        minPayment = findViewById(R.id.editTextMinPay);
        minHours = findViewById(R.id.editTextMinHours);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePreference();
               // startActivity(new Intent(JobPreferenceActivity.this, EmployeeHomeActivity.class));
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

    public void updatePreference () {
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
//                            users.child("User").child(adSnapshot.getKey()).child("/preferredLocation").setValue(location);
//                            users.child("User").child(adSnapshot.getKey()).child("/preferredCategory").setValue(category);
//                            users.child("User").child(adSnapshot.getKey()).child("/preferredHours").setValue(minHours);
//                            users.child("User").child(adSnapshot.getKey()).child("/preferredPayment").setValue(minPayment);
                            //dataSnapshot.child(u.getPreferredCategory()).child("preferredLocation").u(location);
//                                if(locationValidation(location)) {
                            Context context = getApplicationContext();

                            Toast toast = Toast.makeText(context, (CharSequence) location, Toast. LENGTH_LONG);
                            toast.show();

                            Toast toast2 = Toast.makeText(context, "hello", Toast. LENGTH_LONG);
                            toast2.show();
                            toast.show();
                                    Map<String, Object> updates = new HashMap<String, Object>();
                                    System.out.println(location);
                                    updates.put("preferredLocation", location);
                                    adSnapshot.getRef().updateChildren(updates);
                                //}
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



