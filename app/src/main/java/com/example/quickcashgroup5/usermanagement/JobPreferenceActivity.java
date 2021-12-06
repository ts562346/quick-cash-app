package com.example.quickcashgroup5.usermanagement;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.databasemanagement.Database;
import com.example.quickcashgroup5.datavalidation.Validation;
import com.example.quickcashgroup5.feedbackmanagement.ViewFeedbacksActivity;
import com.example.quickcashgroup5.home.EmployeeHomeActivity;
import com.example.quickcashgroup5.home.EmployerHomeActivity;
import com.example.quickcashgroup5.jobcreation.CreateJobActivity;
import com.example.quickcashgroup5.jobsearch.JobSearchActivity;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;


public class JobPreferenceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManagement sessionManagement;
    Spinner category;
    EditText location;
    EditText minPayment;
    EditText minHours;
    Button submit;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;
    Database database;

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
        submit.setOnClickListener(view -> {
            Task<Void> task = updatePreference();
            if (task != null) {
                task.addOnSuccessListener(suc -> {
                    Toast.makeText(getApplicationContext(), "Job preferences have been updated", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(JobPreferenceActivity.this, EmployeeHomeActivity.class));
                }).addOnFailureListener(fal ->
                    Toast.makeText(getApplicationContext(), "There was an error updating your preferences", Toast.LENGTH_SHORT).show()
                );
            } else {

                Log.d(TAG, "Validation failed");
            }
        });

        database = new Database();
    }

    public Task<Void> updatePreference() {
        String email = sessionManagement.getEmail();
        String selectedCategory = (String) category.getSelectedItem();

        String selectedLocation = Validation.sanitize(location.getText().toString());
        if(!Validation.locationValidation(selectedLocation, this)){
            Toast.makeText(this, "Location is not valid", Toast.LENGTH_LONG);
            return null;
        }

        String wage = Validation.sanitize(minPayment.getText().toString());
        String duration = Validation.sanitize(minHours.getText().toString());
        if(!Validation.wageValidation(wage) || !Validation.wageValidation(duration)){
            Toast.makeText(this, "Wage or Hours not valid\n"
                    + "Please make sure you enter maximum of 2 decimal places", Toast.LENGTH_LONG);
            return null;
        }

        User u = database.findUser(sessionManagement.getEmail());
        if(u != null) {
            Map<String, Object> updates = new HashMap<>();
            updates.put("email", email);
            updates.put("preferredCategory", selectedCategory);
            updates.put("preferredLocation", selectedLocation);
            updates.put("preferredPayment", wage);
            updates.put("preferredHours", duration);
            return database.updateUser(updates);
        } else {
                Log.d(TAG, "Signed in user is not in database");
            Toast.makeText(this, "There was an error updating your preferences", Toast.LENGTH_LONG).show();
            return null;
        }
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
            case R.id.nav_searchJob: {
                Intent intent = new Intent(this, JobSearchActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_createJob: {
                Intent intent = new Intent(this, CreateJobActivity.class);
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
                sessionManagement.clearSession();
                Intent intent = new Intent(this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            }
            default: {
                Log.d(TAG, "Asyncronous method canceled");
            }
        }

        return true;
    }
}

