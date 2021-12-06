package com.example.quickcashgroup5.jobsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.home.DataModelDashboard;
import com.example.quickcashgroup5.jobdetails.JobDescriptionEmployeeActivity;
import com.example.quickcashgroup5.R;

import java.util.ArrayList;

public class MyAdapterJobSearch extends RecyclerView.Adapter<MyAdapterJobSearch.MyViewHolderJobSearch>  {

    ArrayList<DataModelDashboard> dataHolder;
    ArrayList<String> key = new ArrayList<>();
    String tag = "MyViewHolder";



    public MyAdapterJobSearch(ArrayList<DataModelDashboard> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolderJobSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_jobsearch, parent, false);
        return new MyViewHolderJobSearch(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderJobSearch holder, int position) {
        key.add(dataHolder.get(position).getKey());
        holder.payment.setText(dataHolder.get(position).getStatus());
        holder.title.setText(dataHolder.get(position).getJobTitle());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class MyViewHolderJobSearch extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView title;
        TextView payment;
        Context context;

        public MyViewHolderJobSearch(@NonNull View itemView) {

            super(itemView);
            context = itemView.getContext();
            title = itemView.findViewById(R.id.title);
            title.setOnClickListener(this);
            payment = itemView.findViewById(R.id.payment);
        }

        @Override
        public void onClick(View view) {
            Intent jobDescription =  new Intent(context, JobDescriptionEmployeeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("Key", key.get(getAdapterPosition()));
            jobDescription.putExtras(bundle);
            context.startActivity(jobDescription);

        }



    }
}
