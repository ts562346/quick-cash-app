package com.example.quickcashgroup5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.Home.EmployeeHomeActivity;
import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobStatusEmployeeActivity extends AppCompatActivity {
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button submit;
    SessionManagement user;


    protected void onCreate(Bundle savedInstanceState) {
        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobstatusemployee);
        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("Key");
        initializeDatabase();
        jobPosting=new JobPosting();

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        JobPosting job = dataSnapshot.getValue(JobPosting.class);
                        job.setStatus("Completed");
                        dataSnapshot.getRef().child("status").setValue(job.getStatus());

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent =  new Intent(JobStatusEmployeeActivity.this, EmployeeHomeActivity.class);
                startActivity(intent);
            }
        });


        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting=dataSnapshot.getValue(JobPosting.class);
                System.out.println(jobPosting.getTitle());

                TextView title = (TextView)findViewById(R.id.jobTitle);
                title.setText(jobPosting.getTitle());

                TextView location = (TextView)findViewById(R.id.locationLabel);
                location.setText(jobPosting.getLocation());

                TextView payment = (TextView)findViewById(R.id.payment);
                payment.setText(jobPosting.getPayment());

                TextView hours = (TextView)findViewById(R.id.hours);
                hours.setText(jobPosting.getDuration());

                TextView category = (TextView)findViewById(R.id.categoryLabel);
                category.setText(jobPosting.getCategory());


                TextView employer = (TextView)findViewById(R.id.employerName);
                employer.setText(jobPosting.getCreatorEmail());


                TextView status = (TextView) findViewById(R.id.statusUpdate);
                if(jobPosting.getSelectedApplicantEmail()==null || !jobPosting.getSelectedApplicantEmail().equals(user.getEmail())) {
                    status.setText("Waiting");
                }else if(jobPosting.getSelectedApplicantEmail().equals(user.getEmail())){
                    status.setText("Ongoing");
                }else if(jobPosting.getStatus().equals("Completed")){
                    status.setText("Completed");
                }else{
                    status.setText("Paid");
                }

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
