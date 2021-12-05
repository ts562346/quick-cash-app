package com.example.quickcashgroup5.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FragmentDashboard extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<DataModelDashboard> dataModelDashboard;


    FirebaseDatabase database;
    DatabaseReference jobs;
    public FragmentDashboard(){

    }



    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Check");
        initializeDatabase();
        if (getArguments() != null) {
            System.out.println("Check2");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SessionManagement sessionManagement = new SessionManagement(this.getContext());
        System.out.println("Check2");
        FragmentDashboard fragment = new FragmentDashboard();

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataModelDashboard = new ArrayList<>();


        if(sessionManagement.getRole().equals("Employer")){
            jobs.child("JobPosting").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                       JobPosting u = adSnapshot.getValue(JobPosting.class);
                       if (u.getCreatorEmail().equals(sessionManagement.getEmail())) {
                           DataModelDashboard ob1 = new DataModelDashboard(u,adSnapshot.getKey());
                           dataModelDashboard.add(ob1);
                       }
                   }
               }
               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
            });


            recyclerView.setAdapter(new MyAdapterEmployer(dataModelDashboard));
        }else {
            jobs.child("JobPosting").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                        JobPosting u = adSnapshot.getValue(JobPosting.class);
                        if (u.getAppliedApplicants().contains(sessionManagement.getEmail())) {
                            if(u.getSelectedApplicantEmail().equals("")){
                                u.setStatus("Applied");
                            }else{
                                if(!u.getSelectedApplicantEmail().equals(sessionManagement.getEmail())) {
                                    u.setStatus("Rejected");
                                }
                            }
                            DataModelDashboard ob1 = new DataModelDashboard(u,adSnapshot.getKey());
                            dataModelDashboard.add(ob1);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            recyclerView.setAdapter(new MyAdapterEmployee(dataModelDashboard));
        }

        return view;
    }
}

