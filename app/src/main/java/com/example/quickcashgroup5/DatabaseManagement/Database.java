package com.example.quickcashgroup5.DatabaseManagement;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.FeedbackManagement.Feedback;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class Database {
    FirebaseDatabase database;
    DataSnapshot data;

    public Database() {
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Database Error: " + error);
            }
        });
    }

    private boolean isAdded(Task<Void> task){
        AtomicBoolean success = new AtomicBoolean(false);

        task.addOnSuccessListener(suc -> {
            success.set(true);
        }).addOnFailureListener(fal -> {
            success.set(false);
        });

        return success.get();
    }

    public Task<Void> add(User user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        return task;
    }

    public Task<Void> add(Feedback feedback) {
        DatabaseReference feedbacks = database.getReference(Feedback.class.getSimpleName());
        Task<Void> task = feedbacks.push().setValue(feedback);
        return task;
    }

    public Task<Void> add(JobPosting job) {
        DatabaseReference jobPostings = database.getReference(JobPosting.class.getSimpleName());
        Task<Void> task = jobPostings.push().setValue(job);
        return task;
    }

    public User findUser(String email) {
        User user = null;

        DataSnapshot dataSnapshot = data.child(User.class.getSimpleName());
        for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
            User u = adSnapshot.getValue(User.class);
            if(u.getEmail().equals(email)){
                user = u;
                break;
            }
        }

        return user;
    }
}
