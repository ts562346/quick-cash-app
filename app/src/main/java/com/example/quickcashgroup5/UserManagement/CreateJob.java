package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateJob extends AppCompatActivity {

    //variable setting
    //(chage the names in the frontend as well)
    private EditText editTextTitle;
    private Spinner spinnerCategory;
    private EditText editTextDuration;
    private EditText editTextLocation;
    private EditText editTextPayment;
    private SessionManagement sessionManagement;

    private Button submit;

    FirebaseDatabase database;
    DatabaseReference jobPostings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
//        sessionManagement.accessControl();
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_createjob);

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
    public Task<Void> add(JobPosting job) {
        return jobPostings.push().setValue(job);
    }

    private void insertJob(){
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

}
