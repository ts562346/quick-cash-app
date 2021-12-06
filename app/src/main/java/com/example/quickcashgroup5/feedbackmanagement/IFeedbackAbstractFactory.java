package com.example.quickcashgroup5.feedbackmanagement;

import android.content.Context;
import android.content.Intent;

import com.example.quickcashgroup5.usermanagement.ISessionManagement;

/**
 * The Interface for the abstract factory of Feedback
 */
public interface IFeedbackAbstractFactory {

    /**
     * Generates an Instance of Session Managment
     *
     * @param context
     * @return
     */
    ISessionManagement getSessionManagementInstance(Context context);

    /**
     * Generates an Instance of Feedback
     *
     * @return
     */
    IFeedback getFeedbackInstance();

    /**
     * Generated an Instance of Intent
     *
     * @param context
     * @param c
     * @return
     */
    Intent getIntentInstance(Context context, Class<?> c);


}
