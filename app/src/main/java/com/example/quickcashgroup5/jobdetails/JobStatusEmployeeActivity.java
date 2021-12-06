package com.example.quickcashgroup5.jobdetails;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.home.EmployeeHomeActivity;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * JobStatusEmployeeActivity Class
 */
public class JobStatusEmployeeActivity extends AppCompatActivity {
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button submit;
    SessionManagement user;
    boolean isSelected = false;
    String key;

    /**
     * Runs when created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobstatusemployee);
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        initializeDatabase();
        jobPosting = new JobPosting();

        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting = dataSnapshot.getValue(JobPosting.class);

                TextView title = findViewById(R.id.jobTitle);
                title.setText(jobPosting.getTitle());

                TextView location = findViewById(R.id.locationLabel);
                location.setText(jobPosting.getLocation());

                TextView payment = findViewById(R.id.payment);
                payment.setText(jobPosting.getPayment());

                TextView hours = findViewById(R.id.hours);
                hours.setText(jobPosting.getDuration());

                TextView category = findViewById(R.id.categoryLabel);
                category.setText(jobPosting.getCategory());


                TextView employer = findViewById(R.id.employerName);
                employer.setText(jobPosting.getCreatorEmail());


                TextView status = findViewById(R.id.statusUpdate);
                if (jobPosting.getSelectedApplicantEmail().equals("") && jobPosting.getAppliedApplicants().contains(user.getEmail())) {
                    status.setText("Waiting");
                    isSelected = false;
                } else if (jobPosting.getSelectedApplicantEmail().equals(user.getEmail())) {
                    status.setText("Ongoing");
                    isSelected = true;
                } else if (!jobPosting.getSelectedApplicantEmail().equals(user.getEmail()) && jobPosting.getAppliedApplicants().contains(user.getEmail())) {
                    status.setText("Rejected");
                    isSelected = true;
                } else if (jobPosting.getStatus().equals("Completed")) {
                    status.setText("Completed");
                    isSelected = false;

                } else {
                    isSelected = false;
                    status.setText("Paid");
                }
                isSelected(isSelected);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        submit = findViewById(R.id.submit);
    }

    /**
     * Initializes the database
     */
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }

    /**
     * Runs when employee is Selected
     *
     * @param isSelected
     */
    private void isSelected(boolean isSelected) {
        if (isSelected) {
            submit.setOnClickListener(view -> {
                jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JobPosting job = dataSnapshot.getValue(JobPosting.class);
                        job.setStatus("Completed");
                        dataSnapshot.getRef().child("status").setValue(job.getStatus());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Log.d(TAG, "Database Error: " + error);
                    }
                });
                Intent intent = new Intent(JobStatusEmployeeActivity.this, EmployeeHomeActivity.class);
                startActivity(intent);
            });
        } else {
            submit.setText("Back");
            submit.setOnClickListener(view -> {
                Intent intent = new Intent(JobStatusEmployeeActivity.this, EmployeeHomeActivity.class);
                startActivity(intent);
            });
        }

    }
}