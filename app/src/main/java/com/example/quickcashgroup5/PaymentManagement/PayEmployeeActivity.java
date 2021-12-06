package com.example.quickcashgroup5.PaymentManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.DatabaseManagement.Database;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PayEmployeeActivity extends AppCompatActivity {

    private JobPosting jobPosting;
    Database database;
    Button submit;
    SessionManagement user;
    User employee;

    protected void onCreate(Bundle savedInstanceState) {
        user = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payemployee);
        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("Key");
        jobPosting = new JobPosting();

        submit = findViewById(R.id.apply);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(employee != null) {
                    Intent intent = new Intent(PayEmployeeActivity.this, Paypal.class);
                    intent.putExtra("Key", key);
                    intent.putExtra("Name", employee.getName());
                    float totalPayment = Float.parseFloat(jobPosting.getPayment()) * Float.parseFloat(jobPosting.getDuration());
                    intent.putExtra("Payment", String.valueOf(totalPayment));
                    startActivity(intent);
                }
            }
        });

        database = new Database();
        FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/").getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobPosting = dataSnapshot.child("JobPosting").child(key).getValue(JobPosting.class);
                System.out.println(key);
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

                TextView status = findViewById(R.id.employerName);
                String employeeEmail = jobPosting.getSelectedApplicantEmail();

                employee = null;
                if(!employeeEmail.equals("")) {
                    employee = database.findUser(employeeEmail);
                    status.setText(employee.getName());
                }else{
                    status.setText("Pending");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Database Error: " + error);
            }
        });

    }
}
