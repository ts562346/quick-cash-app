package com.example.quickcashgroup5.feedbackmanagement;

import static android.content.ContentValues.TAG;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The activity to view the Feedbacks
 */
public class ViewFeedbacksActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database;
    DatabaseReference feedbacks;
    Button submit;
    protected SessionManagement sessionManagement;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView sidebar;

    /**
     * Runs when the class is created
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedbacks);
        sessionManagement = new SessionManagement(this);

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

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(v -> {
            startActivity(new Intent(ViewFeedbacksActivity.this, SendFeedbackActivity.class));
            ViewFeedbacksActivity.this.finish();
        });

        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        feedbacks = database.getReference();

        find();
    }

    /**
     * Finds the feedbacks and adds to the layout
     */
    protected void find() {
        feedbacks.child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Feedback feedback = adSnapshot.getValue(Feedback.class);
                    addToLayout(feedback);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d(TAG, "onMenuItemClick: details");
            }
        });
    }

    //https://www.c-sharpcorner.com/UploadFile/1e5156/dynamically-add-fragment-in-android-studio/

    /**
     * Adds the feedback fragments to the feedback
     *
     * @param feedback
     */
    protected void addToLayout(Feedback feedback) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FeedbackFragment fm2 = new FeedbackFragment();
        fm2.setFeedback(feedback);
        fragmentTransaction.add(R.id.dynamicLayout, fm2, null);
        fragmentTransaction.commit();
    }

    /**
     * To open and close the navigation drawer when the icon is clicked
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
