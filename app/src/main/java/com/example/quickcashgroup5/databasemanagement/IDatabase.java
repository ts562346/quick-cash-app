package com.example.quickcashgroup5.databasemanagement;

import com.example.quickcashgroup5.feedbackmanagement.Feedback;
import com.example.quickcashgroup5.jobcreation.JobPosting;
import com.example.quickcashgroup5.usermanagement.IUser;
import com.example.quickcashgroup5.usermanagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * The interface class of Database
 */
public interface IDatabase {
    /**
     * Adds a new user to the database
     *
     * @param user
     * @return
     */
    Task<Void> addUser(IUser user);

    /**
     * Adds a new feedback to the database
     *
     * @param feedback
     * @return
     */
    Task<Void> addFeedback(Feedback feedback);

    /**
     * Adds a new JobPosting to the database
     *
     * @param job
     * @return
     */
    Task<Void> addJobPosting(JobPosting job);

    /**
     * Updates the User's values
     *
     * @param updates
     * @return
     */
    Task<Void> updateUser(Map<String, Object> updates);

    /**
     * Finds and returns the respective User
     *
     * @param email
     * @return
     */
    User findUser(String email);

    /**
     * Finds and returns the respective JobPosting
     *
     * @param key
     * @return
     */
    JobPosting findJobPosting(String key);

    /**
     * Finds and returns the respective User's DataSnapshot
     *
     * @param email
     * @return
     */
    DataSnapshot getUserDataSnapshot(String email);

    /**
     * Returns the FirebaseDatabase
     *
     * @return
     */
    FirebaseDatabase getFirebaseDatabase();

    /**
     * Returns the respective DataSnapshot
     *
     * @param reference
     * @return
     */
    DataSnapshot getData(String reference);
}
