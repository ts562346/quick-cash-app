package com.example.quickcashgroup5.JobSearch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.Home.DataModelDashboard;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

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
