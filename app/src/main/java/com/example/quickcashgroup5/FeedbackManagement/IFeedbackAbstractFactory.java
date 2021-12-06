package com.example.quickcashgroup5.FeedbackManagement;

import android.content.Context;
import android.content.Intent;

import com.example.quickcashgroup5.UserManagement.ISessionManagement;

public interface IFeedbackAbstractFactory {

    ISessionManagement getSessionManagementInstance(Context context);

    IFeedback getFeedbackInstance();

    Intent getIntentInstance(Context context, Class<?> c);


}
