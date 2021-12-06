package com.example.quickcashgroup5.DatabaseManagement;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.FeedbackManagement.Feedback;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.IUser;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Database implements IDatabase {
    FirebaseDatabase database;
    DataSnapshot data;

    public Database() {
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        database.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
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

    public Task<Void> addUser(IUser user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        return task;
    }

    public Task<Void> addFeedback(Feedback feedback) {
        DatabaseReference feedbacks = database.getReference(Feedback.class.getSimpleName());
        Task<Void> task = feedbacks.push().setValue(feedback);
        return task;
    }

    public Task<Void> addJobPosting(JobPosting job) {
        DatabaseReference jobPostings = database.getReference(JobPosting.class.getSimpleName());
        Task<Void> task = jobPostings.push().setValue(job);
        return task;
    }

    public Task<Void> updatePreferences(Map<String, Object> updates) {
        DatabaseReference users = getUserDataSnapshot((String) updates.get("email")).getRef();
        Task<Void> task = users.updateChildren(updates);
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

    public JobPosting findJobPosting(String key) {
        DataSnapshot dataSnapshot = data.child(JobPosting.class.getSimpleName());
        JobPosting job = dataSnapshot.child(key).getValue(JobPosting.class);

        return job;
    }

    public DataSnapshot getUserDataSnapshot(String email) {
        DataSnapshot user = null;

        DataSnapshot dataSnapshot = data.child(User.class.getSimpleName());
        for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
            User u = adSnapshot.getValue(User.class);
            if(u.getEmail().equals(email)){
                user = adSnapshot;
                break;
            }
        }

        return user;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }
}
