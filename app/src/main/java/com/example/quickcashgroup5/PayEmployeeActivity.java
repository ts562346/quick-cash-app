package com.example.quickcashgroup5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class PayEmployeeActivity extends AppCompatActivity {

    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button submit;
    SessionManagement user;

    protected void onCreate(Bundle savedInstanceState) {
        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdescriptionemployer);
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
                            Intent intent = new Intent(PayEmployeeActivity.this, EmployeeHomeActivity.class);
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


                TextView status = (TextView) findViewById(R.id.employerName);
                if(jobPosting.getSelectedApplicantEmail().equals("")) {
                    status.setText(jobPosting.getSelectedApplicantEmail());
                }else{
                    status.setText("Pending");
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
