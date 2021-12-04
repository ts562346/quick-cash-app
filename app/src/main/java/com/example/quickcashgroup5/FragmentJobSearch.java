package com.example.quickcashgroup5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentJobSearch extends Fragment {
    ArrayList<DataModelDashboard> dataModelJobSearch;
    private static SessionManagement sessionManagement;
    static HashMap<String, JobPosting> jobPostings;

    public FragmentJobSearch(HashMap<String, JobPosting> jobPostings) {
        // Required empty public constructor
        this.jobPostings = jobPostings;
    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        System.out.println("Test 2");
//        super.onCreate(savedInstanceState);
//        System.out.println("Check");
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataModelJobSearch = new ArrayList<>();
        if(jobPostings!=null) {
            for (String key: jobPostings.keySet()) {
                JobPosting job = jobPostings.get(key);
                if(job.getSelectedApplicantEmail().equals("")) {
                    DataModelDashboard ob1 = new DataModelDashboard(job, key);
                    dataModelJobSearch.add(ob1);
                }
            }
        }


            MyAdapterJobSearch myAdapterJobSearch = new MyAdapterJobSearch(dataModelJobSearch);
            recyclerView.setAdapter(myAdapterJobSearch);


        return view;
    }
}
