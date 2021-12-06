package com.example.quickcashgroup5.passwordmanagement;

import android.content.Context;
import android.content.Intent;

/**
 * PasswordManagmentAbstractFactory
 */
public class PasswordManagementAbstractFactory implements IPasswordManagementAbstractFactory {

    /**
     * Gets Intent's Instance
     *
     * @param context
     * @param c
     * @return
     */
    @Override
    public Intent getIntentInstance(Context context, Class<?> c) {
        return new Intent(context, c);
    }

    /**
     * Gets AESCrypt's Instance
     *
     * @return
     */
    @Override
    public IAESCrypt getAesCryptInstance() {
        return new AESCrypt();
    }
}
