package com.example.quickcashgroup5.FeedbackManagement;

import com.example.quickcashgroup5.UserManagement.IUserManagementAbstractFactory;
import com.example.quickcashgroup5.UserManagement.UserManagementAbstractFactory;
import com.example.quickcashgroup5.UserManagement.UserManagementInjector;

public class FeedbackInjector {
    private static FeedbackInjector instance = null;
    private final IFeedbackAbstractFactory feedbackAbstractFactory;

    private FeedbackInjector() {
        feedbackAbstractFactory = new FeedbackAbstractFactory();
    }

    public static FeedbackInjector getInstance() {
        if (instance == null) {
            instance = new FeedbackInjector();
        }
        return instance;
    }

    public IFeedbackAbstractFactory getFeedbackAbstractFactory() {
        return feedbackAbstractFactory;
    }
}
