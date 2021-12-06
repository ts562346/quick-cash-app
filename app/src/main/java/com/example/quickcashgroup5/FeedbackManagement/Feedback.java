package com.example.quickcashgroup5.FeedbackManagement;

public class Feedback implements IFeedback {
    String name;
    String userType;
    String userFeedback;
    int rating;

    public Feedback() {}

    public Feedback(String name, String userType, String userFeedback, int rating) {
        this.name = name;
        this.userType = userType;
        this.userFeedback = userFeedback;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getUserFeedback() {
        return userFeedback;
    }

    @Override
    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }
}
