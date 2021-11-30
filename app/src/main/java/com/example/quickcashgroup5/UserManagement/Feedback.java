package com.example.quickcashgroup5.UserManagement;

public class Feedback {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
