package com.example.quickcashgroup5.FeedbackManagement;

import android.content.Context;
import android.content.Intent;

import com.example.quickcashgroup5.UserManagement.ISessionManagement;
import com.example.quickcashgroup5.UserManagement.SessionManagement;

/**
 * The abstract factory class for Feedback
 */
public class FeedbackAbstractFactory implements IFeedbackAbstractFactory {

    /**
     * The constructor of the class
     *
     * @param context
     * @return
     */
    @Override
    public ISessionManagement getSessionManagementInstance(Context context) {
        return new SessionManagement(context);
    }

    /**
     * Creates a new Feedback instance
     *
     * @return
     */
    @Override
    public IFeedback getFeedbackInstance() {
        return new Feedback();
    }

    /**
     * Gets the intent
     *
     * @param context
     * @param c
     * @return
     */
    @Override
    public Intent getIntentInstance(Context context, Class<?> c) {
        return new Intent(context, c);
    }
}
