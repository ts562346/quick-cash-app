package com.example.quickcashgroup5.feedbackmanagement;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.home.EmployerHomeActivity;
import com.example.quickcashgroup5.jobcreation.CreateJobActivity;
import com.example.quickcashgroup5.jobsearch.JobSearchActivity;
import com.example.quickcashgroup5.usermanagement.JobPreferenceActivity;
import com.example.quickcashgroup5.usermanagement.LogInActivity;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class to Send Feedback of the app
 */
public class SendFeedbackActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected EditText editTextName;
    protected EditText editTextUserType;
    protected EditText editTextFeedback;
    protected RatingBar starRating;
    protected SessionManagement sessionManagement;
    protected Button submit;
    protected FirebaseDatabase database;
    protected DatabaseReference userFeedbacks;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;

    /**
     * Runs when the activity is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendfeedback);

        editTextName = findViewById(R.id.editTextName);
        editTextUserType = findViewById(R.id.editTextUserType);
        editTextFeedback = findViewById(R.id.editTextFeedback);
        starRating = findViewById(R.id.rating);
        submit = findViewById(R.id.submit);

        sidebar = findViewById(R.id.sidebar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (sessionManagement.getRole().equals("Employee")) {
            sidebar.inflateMenu(R.menu.navigation_menu_employee);
        } else {
            sidebar.inflateMenu(R.menu.navigation_menu_employer);
        }
        sidebar.setNavigationItemSelectedListener(this);

        editTextName.setText(sessionManagement.getName());
        editTextUserType.setText(sessionManagement.getRole());
        submit.setOnClickListener(v -> sendFeedback());

        initializeDatabase();
    }

    /**
     * Creates Feedback
     *
     * @param feedback
     * @return
     */
    private boolean createFeedback(Feedback feedback) {
        String name = editTextName.getText().toString().trim();
        String userType = editTextUserType.getText().toString().trim();
        String userFeedback = editTextFeedback.getText().toString().trim();
        int rating = (int) starRating.getRating();

        feedback.setName(name);
        feedback.setUserType(userType);
        feedback.setUserFeedback(userFeedback);
        feedback.setRating(rating);

        return true;
    }

    /**
     * Adds JobPosting object to the Firebase Database
     *
     * @param feedback
     * @return
     */
    protected Task<Void> add(Feedback feedback) {
        return userFeedbacks.push().setValue(feedback);
    }

    /**
     * Sends the feedback
     */
    protected void sendFeedback() {
        Feedback feedback = new Feedback();
        try {
            createFeedback(feedback);
            this.add(feedback).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Successfully sent the feedback", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                startActivity(intent);
            }).addOnFailureListener(fal ->
                    Toast.makeText(this, "Unsuccessfully sent the feedback", Toast.LENGTH_SHORT).show()
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
        userFeedbacks = database.getReference("Feedback");
    }

    /**
     * To open and close the navigation drawer when the icon is clicked
     **/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer

    /**
     * The onclick of the Sidebar
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
            case R.id.nav_searchJob: {
                Intent intent = new Intent(this, JobSearchActivity.class);
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
                Log.d(TAG, "Asyncronous method canceled");
            }
        }

        return true;
    }
}
