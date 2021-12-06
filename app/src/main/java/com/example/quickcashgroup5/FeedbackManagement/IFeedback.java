package com.example.quickcashgroup5.FeedbackManagement;

public interface IFeedback {
    String getName();

    void setName(String name);

    String getUserType();

    void setUserType(String userType);

    String getUserFeedback();

    void setUserFeedback(String userFeedback);

    int getRating();

    void setRating(int rating);
}
