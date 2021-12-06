package com.example.quickcashgroup5.usermanagement;

import android.content.Context;
import android.content.Intent;

public class UserManagementAbstractFactory implements IUserManagementAbstractFactory {
    @Override
    public ISessionManagement getSessionManagementInstance(Context context) {
        return new SessionManagement(context);
    }

    @Override
    public IUser getUserInstance() {
        return new User();
    }

    @Override
    public Intent getIntentInstance(Context context, Class<?> c) {
        return new Intent(context, c);
    }
}
