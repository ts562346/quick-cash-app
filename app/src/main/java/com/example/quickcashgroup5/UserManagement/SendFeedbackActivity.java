package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        sidebar.setNavigationItemSelectedListener(this);

        editTextName.setText(sessionManagement.getName());
        editTextUserType.setText(sessionManagement.getRole());
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendFeedback();
            }
        });

        initializeDatabase();
    }

    private boolean createFeedback(Feedback feedback){
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

    protected void sendFeedback(){
        Feedback feedback = new Feedback();
        try {
            if (createFeedback(feedback)) {
                this.add(feedback).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Successfully sent the feedback", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(fal -> {
                    Toast.makeText(this, "Unsuccessfully sent the feedback", Toast.LENGTH_SHORT).show();
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
        userFeedbacks = database.getReference("Feedback");
    }

    // To open and close the navigation drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //https://stackoverflow.com/questions/42297381/onclick-event-in-navigation-drawer
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
                Intent intent = new Intent(this, CreateJob.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_preferences: {
                Toast.makeText(this, "Preferences page coming soon", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(this, PreferenceActivity.class);
//                startActivity(intent);
//                ((Activity) this).finish();
                break;
            }
            case R.id.nav_feedback: {
                Intent intent = new Intent(this, ViewFeedbacksActivity.class);
                startActivity(intent);
                this.finish();
                break;
            }
            case R.id.nav_logout: {
                sessionManagement.logout();
                break;
            }
        }

        return true;
    }
}
