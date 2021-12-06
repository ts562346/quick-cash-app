package com.example.quickcashgroup5.employeeselection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcashgroup5.home.EmployerHomeActivity;
import com.example.quickcashgroup5.emailmanagement.SendNotification;
import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * The adapter for SelectEmployee
 **/
public class MyAdapterSelectEmployee extends RecyclerView.Adapter<MyAdapterSelectEmployee.MyViewHolderSelectEmployee> {

    ArrayList<DataModelSelectEmployee> dataHolder;
    String tag = "MyViewHolder";
    ArrayList<String> key = new ArrayList<>();
    ArrayList<String> email = new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference jobs;
    private JobPosting jobPosting;

    /**
     * The constructor for this class
     *
     * @param dataHolder
     */
    public MyAdapterSelectEmployee(ArrayList<DataModelSelectEmployee> dataHolder) {
        this.dataHolder = dataHolder;
    }

    /**
     * Runs when ViewHolder is created
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolderSelectEmployee onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_selectemployee, parent, false);
        return new MyViewHolderSelectEmployee(view);
    }

    /**
     * Runs when ViewHolder is binded
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSelectEmployee holder, int position) {
        key.add(dataHolder.get(position).getKey());
        email.add(dataHolder.get(position).getName());
        holder.name.setText(dataHolder.get(position).getName());
    }

    /**
     * Gets the size of the data holder
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    /**
     * The Class RecyclerView ViewHolderSelectEmployee
     */
    class MyViewHolderSelectEmployee extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        ImageButton accept;
        Context context;

        /**
         * The constructor of the class
         *
         * @param itemView
         */
        public MyViewHolderSelectEmployee(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            accept = itemView.findViewById(R.id.accept);
            accept.setOnClickListener(this);
        }

        /**
         * onClick method
         *
         * @param view
         */
        @Override
        public void onClick(View view) {
            database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
            jobs = database.getReference();
            jobs.child("JobPosting").child(key.get(getAdapterPosition())).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    jobPosting = dataSnapshot.getValue(JobPosting.class);
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

                    Intent intent = new Intent(context, EmployerHomeActivity.class);
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
