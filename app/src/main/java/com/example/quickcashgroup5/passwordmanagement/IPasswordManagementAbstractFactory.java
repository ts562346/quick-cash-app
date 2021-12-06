package com.example.quickcashgroup5.passwordmanagement;

import android.content.Context;
import android.content.Intent;

/**
 * PasswordMangement AbstractFactory
 */
public interface IPasswordManagementAbstractFactory {
    /**
     * Gets Intent's Instance
     *
     * @param context
     * @param c
     * @return
     */
    Intent getIntentInstance(Context context, Class<?> c);

    /**
     * Gets AESCrypts's instance
     *
     * @return
     */
    IAESCrypt getAesCryptInstance();
}
