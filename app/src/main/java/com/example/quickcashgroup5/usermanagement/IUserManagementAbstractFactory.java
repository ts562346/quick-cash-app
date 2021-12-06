package com.example.quickcashgroup5.usermanagement;

import android.content.Context;
import android.content.Intent;

/**
 * Interface of UserManagementAbstractFactory
 */
public interface IUserManagementAbstractFactory {

    /**
     * Constructor
     *
     * @param context
     * @return
     */
    ISessionManagement getSessionManagementInstance(Context context);

    /**
     * Get istance of user
     *
     * @return
     */
    IUser getUserInstance();

    /**
     * Get Intent's Instance
     *
     * @param context
     * @param c
     * @return
     */
    Intent getIntentInstance(Context context, Class<?> c);

}
