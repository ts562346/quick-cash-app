package com.example.quickcashgroup5.UserManagement;

import android.content.Context;
import android.content.Intent;

public interface IUserManagementAbstractFactory {

    ISessionManagement getSessionManagementInstance(Context context);

    IUser getUserInstance();

    Intent getIntentInstance(Context context, Class<?> c);

}
