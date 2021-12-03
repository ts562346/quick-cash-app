package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.Map;


public class JobPreferenceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManagement sessionManagement;
    Spinner category;
    EditText location, minPayment, minHours;
    Button submit;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sessionManagement = new SessionManagement(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobpreference);
        category = findViewById(R.id.jobCategory);
        location = findViewById(R.id.editTextLocation);
        minPayment = findViewById(R.id.editTextMinPay);
        minHours = findViewById(R.id.editTextMinHours);
        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (sessionManagement.getRole().equals("Employee")) {
            sidebar.inflateMenu(R.menu.navigation_menu_employee);
        } else {
            sidebar.inflateMenu(R.menu.navigation_menu_employer);
        }
        sidebar.setNavigationItemSelectedListener(this);

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


                    if (userEmail.equals(sessionEmail)) {
                        try {
                            /**
                             * Added location, minPayment and minHours to
                             * the database of user.
                             */

                            Map<String, Object> updates = new HashMap<String, Object>();
                            updates.put("preferredCategory", category.getSelectedItem());
                            updates.put("preferredLocation", location.getText().toString());
                            updates.put("preferredPayment", minPayment.getText().toString());
                            updates.put("preferredHours", minHours.getText().toString());
                            adSnapshot.getRef().updateChildren(updates);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("HELLO");
            }
        });
    }

    // To open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, EmployerHomeActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_createJob: {
                Intent intent = new Intent(this, CreateJob.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_preferences: {
                Intent intent = new Intent(this, JobPreferenceActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_feedback: {
                Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_logout: {
                sessionManagement.logout();
                break;
            }
        }

        return true;
    }
}

