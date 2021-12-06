package com.example.quickcashgroup5.usermanagement;

import android.content.Context;
import android.content.Intent;

/**
 * UserManagementAbstractFactory
 */
public class UserManagementAbstractFactory implements IUserManagementAbstractFactory {
    /**
     * Gets the instance of SessionManagement
     *
     * @param context
     * @return
     */
    @Override
    public ISessionManagement getSessionManagementInstance(Context context) {
        return new SessionManagement(context);
    }

    /**
     * Gets the User's instance
     *
     * @return
     */
    @Override
    public IUser getUserInstance() {
        return new User();
    }

    /**
     * Gets the Intent's Instance
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
