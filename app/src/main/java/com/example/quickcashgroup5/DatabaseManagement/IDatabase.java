package com.example.quickcashgroup5.DatabaseManagement;

import com.example.quickcashgroup5.FeedbackManagement.Feedback;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.IUser;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.util.Map;

public interface IDatabase {
    Task<Void> addUser(IUser user);

    Task<Void> addFeedback(Feedback feedback);

    Task<Void> addJobPosting(JobPosting job);

    Task<Void> updateUser(Map<String, Object> updates);

    User findUser(String email);

    DataSnapshot getUserDataSnapshot(String email);
}
