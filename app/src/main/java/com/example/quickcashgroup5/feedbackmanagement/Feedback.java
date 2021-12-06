package com.example.quickcashgroup5.feedbackmanagement;

/**
 * The feedback class
 */
public class Feedback implements IFeedback {
    String name;
    String userType;
    String userFeedback;
    int rating;

    /**
     * The empty constructor for this class
     */
    public Feedback() {

    }

    /**
     * The constructor for this class
     *
     * @param name
     * @param userType
     * @param userFeedback
     * @param rating
     */
    public Feedback(String name, String userType, String userFeedback, int rating) {
        this.name = name;
        this.userType = userType;
        this.userFeedback = userFeedback;
        this.rating = rating;
    }

    /**
     * Gets the name
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets UserType
     *
     * @return
     */
    @Override
    public String getUserType() {
        return userType;
    }

    /**
     * Sets UserType
     *
     * @param userType
     */
    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets UserFeedback
     *
     * @return
     */
    @Override
    public String getUserFeedback() {
        return userFeedback;
    }

    /**
     * Sets UserFeedback
     *
     * @param userFeedback
     */
    @Override
    public void setUserFeedback(String userFeedback) {
        this.userFeedback = userFeedback;
    }

    /**
     * Gets the rating
     *
     * @return
     */
    @Override
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating
     *
     * @param rating
     */
    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }
}
