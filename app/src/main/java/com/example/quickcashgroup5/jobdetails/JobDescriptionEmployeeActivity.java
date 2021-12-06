package com.example.quickcashgroup5.jobdetails;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.databasemanagement.Database;
import com.example.quickcashgroup5.emailmanagement.SendNotification;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.jobsearch.JobSearchActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class JobDescriptionEmployeeActivity extends AppCompatActivity {

    private JobPosting jobPosting;
    Button accept;
    SessionManagement user;
    String key;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdecriptionemployee);
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        jobPosting=new JobPosting();
        database = new Database();

        accept = findViewById(R.id.submit);
        accept.setOnClickListener(view -> notifyEmployer());


        database.getFirebaseDatabase().getReference().child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting=dataSnapshot.getValue(JobPosting.class);

                TextView title = findViewById(R.id.title);
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Database Error: " + error);
            }
        });
    }

    private void notifyEmployer(){
        database.getFirebaseDatabase().getReference().child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                JobPosting job = dataSnapshot.getValue(JobPosting.class);
                for (DataSnapshot adSnapshot : dataSnapshot.child("appliedApplicant").getChildren()) {
                    job.addAppliedApplicants(adSnapshot.getValue(String.class));
                }
                try {
                    if(!job.getAppliedApplicants().contains(user.getEmail())) {
                        job.addAppliedApplicants(user.getEmail());
                        dataSnapshot.getRef().child("appliedApplicants").setValue(job.getAppliedApplicants());
                        String subject = user.getName() + " has applied to your " + job.getCategory() + " job.";
                        String message = user.getName() + " has applied to your " + job.getCategory() + " job, "
                                + job.getTitle() + ".";
                        new SendNotification(job.getCreatorEmail(), subject, message).execute();
                        Toast toast=Toast. makeText(getApplicationContext(),"Applied Successfully",Toast. LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast=Toast. makeText(getApplicationContext(),"Already applied to this job",Toast. LENGTH_LONG);
                        toast.show();
                    }
                    Intent intent = new Intent(JobDescriptionEmployeeActivity.this, JobSearchActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Database Error: " + error);
            }
        });
    }
}
