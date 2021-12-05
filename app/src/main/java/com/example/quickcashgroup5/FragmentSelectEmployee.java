package com.example.quickcashgroup5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.SortedSet;

public class FragmentSelectEmployee extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String key;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<DataModelSelectEmployee> selectEmployeeDataModel;
    private static SessionManagement sessionManagement;

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    private JobPosting jobPosting;
    FirebaseDatabase database;
    DatabaseReference jobs;
    public FragmentSelectEmployee(){

    }



    public FragmentSelectEmployee(SessionManagement sessionManagement, String key) {
        // Required empty public constructor
        this.key=key;
        FragmentSelectEmployee.sessionManagement = sessionManagement;
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDatabase();
        System.out.println("Check4555");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        selectEmployeeDataModel = new ArrayList<>();
        System.out.println(key);

        jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobPosting=dataSnapshot.getValue(JobPosting.class);
                for (int i=0; i<jobPosting.getAppliedApplicants().size(); i++){
                    System.out.println(jobPosting.getAppliedApplicants().get(i));
                    DataModelSelectEmployee name= new DataModelSelectEmployee(key, jobPosting.getAppliedApplicants().get(i));
                    selectEmployeeDataModel.add(name);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });


        MyAdapterSelectEmployee myAdapter = new MyAdapterSelectEmployee(selectEmployeeDataModel);
        recyclerView.setAdapter(myAdapter);


        return view;
    }
}
