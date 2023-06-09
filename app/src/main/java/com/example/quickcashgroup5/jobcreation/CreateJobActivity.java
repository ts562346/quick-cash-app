package com.example.quickcashgroup5.jobcreation;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.quickcashgroup5.feedbackmanagement.ViewFeedbacksActivity;
import com.example.quickcashgroup5.home.EmployerHomeActivity;
import com.example.quickcashgroup5.notificationmanagement.Applicant;
import com.example.quickcashgroup5.notificationmanagement.EmailNotificationManager;
import com.example.quickcashgroup5.usermanagement.JobPreferenceActivity;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.example.quickcashgroup5.usermanagement.User;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Activity to create jobs
 */
public class CreateJobActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

    EmailNotificationManager manager;

    /**
     * Runs when Activity is created
     *
     * @param savedInstanceState
     */
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

        sidebar.setNavigationItemSelectedListener(this);
        submit.setOnClickListener((View v) ->
                insertJob()
        );

        manager = new EmailNotificationManager();

        initializeDatabase();
    }

    /**
     * Creates the JobPosting
     *
     * @param job
     * @return
     */
    private boolean createJob(JobPosting job) {
        String title = editTextTitle.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        String payment = editTextPayment.getText().toString().trim();
        String duration = editTextDuration.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String creatorEmail = sessionManagement.getEmail();

        job.setStatus("New");
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

    /**
     * Inserts the JobPosting if it is valid
     */
    protected void insertJob() {
        JobPosting job = new JobPosting();
        try {
            createJob(job);
            this.add(job).addOnSuccessListener(suc -> {
                database.getReference("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            User u = data.getValue(User.class);
                            if (u.getIsEmployee().equals("yes")) {
                                new Applicant(u.getEmail(), manager);
                            }
                        }
                        String subject = "A new ";
                        subject += job.getCategory() + " job has been created";
                        String message = "A new ";
                        message += job.getCategory() + " job has been created. \nThe wage is CAD ";
                        message += job.getPayment() + " per hour and the number of hours you will be working is ";
                        message += job.getDuration() + ". \nThe job will be taking place at " + job.getLocation() + ".";
                        manager.setNotification(subject, message);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d(TAG, "Asyncronous method canceled");
                    }
                });
                Toast.makeText(this, "Successful Job Creation", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, EmployerHomeActivity.class);
                startActivity(intent);
            }).addOnFailureListener(fal ->
                    Toast.makeText(this, "Unsuccessful Job Creation", Toast.LENGTH_SHORT).show()
            );
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

    /**
     * To open and close the navigation drawer when the icon is clicked
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer

    /**
     * Side Bar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                Intent intent = new Intent(this, EmployerHomeActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_createJob: {
                Intent intent = new Intent(this, CreateJobActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_preferences: {
                Intent intent = new Intent(this, JobPreferenceActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_feedback: {
                Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_logout: {
                sessionManagement.clearSession();
                Intent intent = new Intent(this, LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
                break;
            }
            default: {
                Log.d(TAG, "Side bar Error");
            }
        }

        return true;
    }

}
