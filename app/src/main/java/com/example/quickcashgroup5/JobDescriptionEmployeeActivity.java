package com.example.quickcashgroup5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.JobSearchActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobDescriptionEmployeeActivity extends AppCompatActivity {

    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button accept;
    SessionManagement user;

    protected void onCreate(Bundle savedInstanceState) {
        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdecriptionemployee);
        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("Key");
        initializeDatabase();
        jobPosting=new JobPosting();

        accept = findViewById(R.id.submit);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
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
                                    Toast toast=Toast. makeText(getApplicationContext(),"Applied Successfully",Toast. LENGTH_LONG);
                                    toast. show();
                                }else{
                                    Toast toast=Toast. makeText(getApplicationContext(),"Already applied to this job",Toast. LENGTH_LONG);
                                    toast. show();
                                }
                                Intent intent = new Intent(JobDescriptionEmployeeActivity.this, JobSearchActivity.class);
                                startActivity(intent);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting=dataSnapshot.getValue(JobPosting.class);
                System.out.println(jobPosting.getTitle());

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
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });





    }
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }
}
