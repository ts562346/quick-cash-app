package com.example.quickcashgroup5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SelectEmployeeActivity extends AppCompatActivity{
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    Button submit;
    SessionManagement user;

    protected void onCreate(Bundle savedInstanceState) {

        user= new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectemployee);


        Bundle bundle = getIntent().getExtras();
        String key = bundle.getString("Key");
        initializeDatabase();
        jobPosting=new JobPosting();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.selectEmployeeRecycle, new FragmentSelectEmployee(user,key)).commit();




    }
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }
}
