package com.example.quickcashgroup5.FeedbackManagement;

/**
 * Interface class of feedback
 */
public interface IFeedback {
    /**
     * Gets the name
     *
     * @return
     */
    String getName();

    /**
     * Sets the name
     *
     * @param name
     */
    void setName(String name);

    /**
     * Gets the UserType
     *
     * @return
     */
    String getUserType();

    /**
     * Sets UserType
     *
     * @param userType
     */
    void setUserType(String userType);

    /**
     * Gets the User Feedback
     *
     * @return
     */
    String getUserFeedback();

    /**
     * Sets the User Feedback
     *
     * @param userFeedback
     */
    void setUserFeedback(String userFeedback);

    /**
     * Gets the rating
     *
     * @return
     */
    int getRating();

    /**
     * Sets the rating
     *
     * @param rating
     */
    void setRating(int rating);
}
