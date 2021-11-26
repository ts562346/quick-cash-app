package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateJob extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle actionBarDrawerToggle;
    protected NavigationView sidebar;
    protected EditText editTextTitle;
    protected Spinner spinnerCategory;
    protected EditText editTextDuration;
    protected EditText editTextLocation;
    protected EditText editTextPayment;
    protected SessionManagement sessionManagement;

    protected Button submit;

    protected FirebaseDatabase database;
    protected DatabaseReference jobPostings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createjob);
        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextTitle = findViewById(R.id.editTextJobTitle);
        editTextLocation = findViewById(R.id.editTextLocation);
        editTextPayment = findViewById(R.id.editTextPayment);
        editTextDuration = findViewById(R.id.editTextDuration);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertJob();
            }
        });

        initializeDatabase();
    }

    private boolean createJob(JobPosting job){
        String title = editTextTitle.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String payment = editTextPayment.getText().toString().trim();
        String duration = editTextDuration.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String creatorEmail = sessionManagement.getEmail();

        job.setTitle(title);
        job.setLocation(location);
        job.setPayment(payment);
        job.setDuration(duration);
        job.setCategory(category);
        job.setCreatorEmail(creatorEmail);
        job.setSelectedApplicantEmail("");

        return true;
    }

    /**
     * Adds JobPosting object to the Firebase Database
     *
     * @param job
     * @return
     */
    protected Task<Void> add(JobPosting job) {
        return jobPostings.push().setValue(job);
    }

    protected void insertJob(){
        JobPosting job = new JobPosting();
        try {
            if (createJob(job)) {
                this.add(job).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Successful Job Creation", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, LogInActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(fal -> {
                    Toast.makeText(this, "Unsuccessful Job Creation", Toast.LENGTH_SHORT).show();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the firebase database
     */
    protected void initializeDatabase() {
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobPostings = database.getReference(JobPosting.class.getSimpleName());
    }

    // To open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
