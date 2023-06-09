package com.example.quickcashgroup5.employeeselection;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * The class for fragment of SelectEmployee
 */
public class FragmentSelectEmployee extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    String key;
    ArrayList<DataModelSelectEmployee> selectEmployeeDataModel;
    private static SessionManagement sessionManagement;
    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;

    /**
     * Sets the JobPosting
     *
     * @param jobPosting
     */
    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    /**
     * The constructor of this class
     *
     * @param sessionManagement
     * @param key
     */
    public FragmentSelectEmployee(SessionManagement sessionManagement, String key) {
        // Required empty public constructor
        this.key = key;
        FragmentSelectEmployee.sessionManagement = sessionManagement;
    }

    /**
     * Initializes the database
     */
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }

    /**
     * Runs when fragment is created
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDatabase();
    }

    /**
     * Runs when view is created
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        selectEmployeeDataModel = new ArrayList<>();

        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting = dataSnapshot.getValue(JobPosting.class);
                for (int i = 0; i < jobPosting.getAppliedApplicants().size(); i++) {
                    Log.d(TAG, jobPosting.getAppliedApplicants().get(i));
                    DataModelSelectEmployee name = new DataModelSelectEmployee(key, jobPosting.getAppliedApplicants().get(i));
                    selectEmployeeDataModel.add(name);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Database Error: " + error);
            }
        });


        MyAdapterSelectEmployee myAdapter = new MyAdapterSelectEmployee(selectEmployeeDataModel);
        recyclerView.setAdapter(myAdapter);


        return view;
    }
}
