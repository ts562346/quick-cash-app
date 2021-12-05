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
    boolean isSelected = false;
    String key;


    protected void onCreate(Bundle savedInstanceState) {
        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobstatusemployee);
        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        initializeDatabase();
        jobPosting=new JobPosting();
        System.out.println("lol2"+isSelected);

        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("lol3"+isSelected);
                jobPosting=dataSnapshot.getValue(JobPosting.class);
                System.out.println(jobPosting.getTitle());

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
                if(jobPosting.getSelectedApplicantEmail()==null) {
                    status.setText("Waiting");
                    isSelected=false;
                    System.out.println("Lol "+isSelected);
                }else if(jobPosting.getSelectedApplicantEmail().equals(user.getEmail())){
                    status.setText("Ongoing");
                    isSelected=true;
                }else if(!jobPosting.getSelectedApplicantEmail().equals(user.getEmail())){
                    status.setText("Rejected");
                    isSelected=true;
                }else if(jobPosting.getStatus().equals("Completed")){
                    status.setText("Completed");
                    isSelected=false;

                }else{
                    isSelected=false;
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

        System.out.println("lol1"+isSelected);
    }


    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }



    private void isSelected( boolean isSelected) {
        if (isSelected) {
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
                    Intent intent = new Intent(JobStatusEmployeeActivity.this, EmployeeHomeActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            submit.setText("Back");
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(JobStatusEmployeeActivity.this, EmployeeHomeActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}