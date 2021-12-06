package com.example.quickcashgroup5.databasemanagement;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.feedbackmanagement.Feedback;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.IUser;
import com.example.quickcashgroup5.usermanagement.User;
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
    FirebaseDatabase firebaseDatabase;
    DataSnapshot data;

    /**
     * The constructor of Database
     */
    public Database() {
        firebaseDatabase = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        firebaseDatabase.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Database Error: " + error);
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
        DatabaseReference users = firebaseDatabase.getReference(User.class.getSimpleName());
        return users.push().setValue(user);
    }

    /**
     * Adds a new feedback to the database
     *
     * @param feedback
     * @return
     */
    public Task<Void> addFeedback(Feedback feedback) {
        DatabaseReference feedbacks = firebaseDatabase.getReference(Feedback.class.getSimpleName());
        return feedbacks.push().setValue(feedback);
    }

    /**
     * Adds a new JobPosting to the database
     *
     * @param job
     * @return
     */
    public Task<Void> addJobPosting(JobPosting job) {
        DatabaseReference jobPostings = firebaseDatabase.getReference(JobPosting.class.getSimpleName());
        return jobPostings.push().setValue(job);
    }

    /**
     * Updates the User's values
     *
     * @param updates
     * @return
     */
    public Task<Void> updateUser(Map<String, Object> updates) {
        DatabaseReference users = getUserDataSnapshot((String) updates.get("email")).getRef();
        return users.updateChildren(updates);
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
        return dataSnapshot.child(key).getValue(JobPosting.class);
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
    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
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