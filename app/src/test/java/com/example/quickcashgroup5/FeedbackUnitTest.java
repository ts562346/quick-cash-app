package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.quickcashgroup5.UserManagement.Feedback;

import org.junit.Test;

public class FeedbackUnitTest {
    private static String name = "JohnDoe";
    private static String userType = "Employee";
    private static String userFeedback = "Great app!";
    private static int rating = 5;

    @Test
    public void feedbackConstructor() {
        Feedback feedback = new Feedback();
        assertNotNull(feedback);
    }

    @Test
    public void feedbackGetSet() {
        Feedback feedback = new Feedback();

        feedback.setName(name);
        assertEquals(feedback.getName(), name);

        feedback.setUserType(userType);
        assertEquals(feedback.getUserType(), userType);

        feedback.setUserFeedback(userFeedback);
        assertEquals(feedback.getUserFeedback(), userFeedback);

        feedback.setRating(rating);
        assertEquals(feedback.getRating(), rating);
    }
}
