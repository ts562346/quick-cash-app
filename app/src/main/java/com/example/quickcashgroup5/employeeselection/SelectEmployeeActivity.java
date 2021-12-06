package com.example.quickcashgroup5.employeeselection;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The Activity for selecting employee
 */
public class SelectEmployeeActivity extends AppCompatActivity {
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button refresh;
    SessionManagement user;
    String key;

    /**
     * OnCreate method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        user = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectemployee);

        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        initializeDatabase();
        jobPosting = new JobPosting();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.selectEmployeeRecycle, new FragmentSelectEmployee(user, key)).commit();

        refresh = findViewById(R.id.refresh_button);
        refresh.setOnClickListener(view -> {

        });

        FragmentSelectEmployee f = new FragmentSelectEmployee(user, key);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.selectEmployeeRecycle, f, "Select employee");
        ft.commit();

    }

    /**
     * Initializes the database
     */
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }
}
