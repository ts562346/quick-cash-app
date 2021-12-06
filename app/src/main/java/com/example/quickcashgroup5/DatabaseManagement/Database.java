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

/**
 * The Database class where the fireDB methods are stored
 */
public class Database implements IDatabase {
    FirebaseDatabase database;
    DataSnapshot data;

    /**
     * The constructor of Database
     */
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

    /**
     * Adds a new user to the database
     *
     * @param user
     * @return
     */
    public Task<Void> addUser(IUser user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        return task;
    }

    /**
     * Adds a new feedback to the database
     *
     * @param feedback
     * @return
     */
    public Task<Void> addFeedback(Feedback feedback) {
        DatabaseReference feedbacks = database.getReference(Feedback.class.getSimpleName());
        Task<Void> task = feedbacks.push().setValue(feedback);
        return task;
    }

    /**
     * Adds a new JobPosting to the database
     *
     * @param job
     * @return
     */
    public Task<Void> addJobPosting(JobPosting job) {
        DatabaseReference jobPostings = database.getReference(JobPosting.class.getSimpleName());
        Task<Void> task = jobPostings.push().setValue(job);
        return task;
    }

    /**
     * Updates the User's values
     *
     * @param updates
     * @return
     */
    public Task<Void> updateUser(Map<String, Object> updates) {
        DatabaseReference users = getUserDataSnapshot((String) updates.get("email")).getRef();
        Task<Void> task = users.updateChildren(updates);
        return task;
    }

    /**
     * Finds and returns the respective User
     *
     * @param email
     * @return
     */
    @Override
    public User findUser(String email) {
        User user = null;

        DataSnapshot dataSnapshot = data.child(User.class.getSimpleName());
        for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
            User u = adSnapshot.getValue(User.class);
            if (u.getEmail().equals(email)) {
                user = u;
                break;
            }
        }

        return user;
    }

    /**
     * Finds and returns the respective JobPosting
     *
     * @param key
     * @return
     */
    public JobPosting findJobPosting(String key) {
        DataSnapshot dataSnapshot = data.child(JobPosting.class.getSimpleName());
        JobPosting job = dataSnapshot.child(key).getValue(JobPosting.class);

        return job;
    }

    /**
     * Finds and returns the respective User's DataSnapshot
     *
     * @param email
     * @return
     */
    public DataSnapshot getUserDataSnapshot(String email) {
        DataSnapshot user = null;

        DataSnapshot dataSnapshot = data.child(User.class.getSimpleName());
        for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
            User u = adSnapshot.getValue(User.class);
            if (u.getEmail().equals(email)) {
                user = adSnapshot;
                break;
            }
        }

        return user;
    }

    /**
     * Returns the FirebaseDatabase
     *
     * @return
     */
    public FirebaseDatabase getDatabase() {
        return database;
    }

    /**
     * Returns the respective DataSnapshot
     *
     * @param reference
     * @return
     */
    public DataSnapshot getData(String reference) {
        return data.child(reference);
    }
}