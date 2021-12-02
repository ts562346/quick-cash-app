package com.example.quickcashgroup5;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.UserManagement.Feedback;
import com.example.quickcashgroup5.UserManagement.JobPosting;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseManagement {
    FirebaseDatabase database;

    protected DatabaseManagement() {
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
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

    private boolean add(User user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        return isAdded(task);
    }

    private boolean add(Feedback feedback) {
        DatabaseReference feedbacks = database.getReference(Feedback.class.getSimpleName());
        Task<Void> task = feedbacks.push().setValue(feedback);
        return isAdded(task);
    }

    private boolean add(JobPosting job) {
        DatabaseReference jobPostings = database.getReference(JobPosting.class.getSimpleName());
        Task<Void> task = jobPostings.push().setValue(job);
        return isAdded(task);
    }

    public User findUser(String email) {
        User user = null;

        DataSnapshot dataSnapshot = getDataSnapshot(User.class.getSimpleName());
        for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
            User u = adSnapshot.getValue(User.class);
            if(u.getEmail().equals(email)){
                user = u;
            }
        }

        return user;
    }

    private DataSnapshot getDataSnapshot(String reference){
        DatabaseReference ref = database.getReference(reference);
        final DataSnapshot[] data = {null};
        ref.child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data[0] = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return data[0];
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

    private boolean add(User user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        return isAdded(task);
    }

    private boolean add(Feedback feedback) {
        DatabaseReference feedbacks = database.getReference(Feedback.class.getSimpleName());
        Task<Void> task = feedbacks.push().setValue(feedback);
        return isAdded(task);
    }

    private boolean add(JobPosting job) {
        DatabaseReference jobPostings = database.getReference(JobPosting.class.getSimpleName());
        Task<Void> task = jobPostings.push().setValue(job);
        return isAdded(task);
    }
}
