package com.example.quickcashgroup5.EmployeeSelection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.Home.EmployerHomeActivity;
import com.example.quickcashgroup5.EmailManagement.SendNotification;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapterSelectEmployee extends RecyclerView.Adapter<MyAdapterSelectEmployee.MyViewHolderSelectEmployee>  {

    ArrayList<DataModelSelectEmployee> dataHolder;
    String TAG = "MyViewHolder";
    ArrayList<String> key = new ArrayList<String>();
    ArrayList<String> email = new ArrayList<String>();
    FirebaseDatabase database;
    DatabaseReference jobs;
    private JobPosting jobPosting;

    public MyAdapterSelectEmployee(ArrayList<DataModelSelectEmployee> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public MyViewHolderSelectEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_selectemployee, parent, false);
        return new MyViewHolderSelectEmployee(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSelectEmployee holder, int position) {
        key.add(dataHolder.get(position).getKey());
        email.add(dataHolder.get(position).getName());
        holder.name.setText(dataHolder.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class MyViewHolderSelectEmployee extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageButton accept;
        Context context;
        public MyViewHolderSelectEmployee(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            accept = itemView.findViewById(R.id.accept);
            accept.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
            jobs = database.getReference();
            jobs.child("JobPosting").child(key.get(getAdapterPosition())).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    jobPosting=dataSnapshot.getValue(JobPosting.class);
                    jobPosting.setSelectedApplicantEmail(email.get(getAdapterPosition()));
                    dataSnapshot.getRef().child("selectedApplicantEmail").setValue(jobPosting.getSelectedApplicantEmail());
                    jobPosting.setStatus("Ongoing");
                    dataSnapshot.getRef().child("status").setValue(jobPosting.getStatus());

                    String subject = "Congrats you have been selected for a " + jobPosting.getCategory() + " job.";
                    String message = "Congrats you have been selected for a " + jobPosting.getCategory() + " job, "
                            + jobPosting.getTitle() + ". \nThe wage is CAD " + jobPosting.getPayment()
                            + " per hour and the number of hours you will be working is "
                            + jobPosting.getDuration() + ". \nThe job will take place at " + jobPosting.getLocation() + ".";
                    new SendNotification(email.get(getAdapterPosition()), subject, message).execute();

                    Intent intent =  new Intent(context, EmployerHomeActivity.class);
                    context.startActivity(intent);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });
        }
    }
}