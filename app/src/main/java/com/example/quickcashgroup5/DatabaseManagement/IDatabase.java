package com.example.quickcashgroup5.DatabaseManagement;

import com.example.quickcashgroup5.FeedbackManagement.Feedback;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;

public interface IDatabase {
    Task<Void> addUser(User user);

    Task<Void> addFeedback(Feedback feedback);

    Task<Void> addJobPosting(JobPosting job);

    User findUser(String email);
}
