package com.example.quickcashgroup5.EmployeeSelection;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectEmployeeActivity extends AppCompatActivity{
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button refresh;
    SessionManagement user;
    String key;

    protected void onCreate(Bundle savedInstanceState) {

        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectemployee);

        Bundle bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        initializeDatabase();
        jobPosting=new JobPosting();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.selectEmployeeRecycle, new FragmentSelectEmployee(user,key)).commit();

        refresh = findViewById(R.id.refresh_button);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        FragmentSelectEmployee f = new FragmentSelectEmployee(user,key);
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.selectEmployeeRecycle, f, "Select employee");
        ft.commit();

    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }
}
