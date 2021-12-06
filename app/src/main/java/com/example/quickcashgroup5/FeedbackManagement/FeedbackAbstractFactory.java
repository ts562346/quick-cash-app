package com.example.quickcashgroup5.FeedbackManagement;

import android.content.Context;
import android.content.Intent;

import com.example.quickcashgroup5.UserManagement.ISessionManagement;
import com.example.quickcashgroup5.UserManagement.SessionManagement;


public class FeedbackAbstractFactory implements IFeedbackAbstractFactory{
    @Override
    public ISessionManagement getSessionManagementInstance(Context context) {
        return new SessionManagement(context);
    }

    @Override
    public IFeedback getFeedbackInstance() {
        return new Feedback();
    }

    @Override
    public Intent getIntentInstance(Context context, Class<?> c) {
        return new Intent(context, c);
    }
}
