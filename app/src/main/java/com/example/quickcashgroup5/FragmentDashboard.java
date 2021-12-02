package com.example.quickcashgroup5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDashboard extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<DataModelDashboard> dataModelDashboard;
    private static SessionManagement sessionManagement;

    FirebaseDatabase database;
    DatabaseReference jobs;
    public FragmentDashboard(){

    }


    public FragmentDashboard(SessionManagement sessionManagement) {
        // Required empty public constructor
        this.sessionManagement= sessionManagement;
    }

    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentDashboard newInstance(String param1, String param2) {
        FragmentDashboard fragment = new FragmentDashboard(sessionManagement);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        System.out.println("Check2");


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

