package com.example.quickcashgroup5.jobdetails;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.databasemanagement.Database;
import com.example.quickcashgroup5.home.EmployeeHomeActivity;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.example.quickcashgroup5.usermanagement.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * JobDescriptionEmployerActivity
 */
public class JobDescriptionEmployerActivity extends AppCompatActivity {

    private JobPosting jobPosting;
    Database database;
    Button submit;
    SessionManagement user;

    /**
     * Runs when created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdescriptionemployer);
        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("Key");
        jobPosting = new JobPosting();

        database = new Database();

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(view -> {
            Intent intent = new Intent(JobDescriptionEmployerActivity.this, EmployeeHomeActivity.class);
            startActivity(intent);
        });


        database.getFirebaseDatabase().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting = dataSnapshot.child("JobPosting").child(key).getValue(JobPosting.class);

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


                TextView status = findViewById(R.id.employerName);
                if (!jobPosting.getSelectedApplicantEmail().equals("")) {
                    String employeeName = null;
                    for (DataSnapshot ad : dataSnapshot.child("User").getChildren()) {
                        User u = ad.getValue(User.class);
                        if (u.getEmail().equals(jobPosting.getSelectedApplicantEmail())) {
                            employeeName = u.getName();
                        }
                    }
                    if (employeeName == null) {
                        status.setText("Invalid employee was selected");
                    }
                } else {
                    status.setText("Pending");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.d(TAG, "Database Error: " + error);
            }
        });

    }
}
