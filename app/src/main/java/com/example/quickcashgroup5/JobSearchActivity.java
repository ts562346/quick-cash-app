package com.example.quickcashgroup5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.UserManagement.JobPreferenceActivity;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

public class JobSearchActivity extends AppCompatActivity implements View.OnClickListener{
    SessionManagement sessionManagement;
    Button logout, jobPreference;

    /**
     * Method that runs when activity is created
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
//        setContentView(R.layout.activity_jobsearch);


//            Intent refresh = new Intent(this, JobSearchActivity.class);
//            startActivity(refresh);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jobsearch);
        getSupportFragmentManager().beginTransaction().replace(R.id.recycleViewContainer, new FragmentJobSearch(sessionManagement)).commit();

    }

    /**
     * Onclick method for logging out
     *
     * @param view
     */
    public void onClick(View view) {

    }
}
