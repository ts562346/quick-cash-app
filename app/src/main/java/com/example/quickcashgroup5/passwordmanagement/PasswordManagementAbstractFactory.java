package com.example.quickcashgroup5.passwordmanagement;

import android.content.Context;
import android.content.Intent;

public class PasswordManagementAbstractFactory implements IPasswordManagementAbstractFactory {
    @Override
    public Intent getIntentInstance(Context context, Class<?> c) {
        return new Intent(context, c);
    }

    @Override
    public IAESCrypt getAesCryptInstance() {
        return new AESCrypt();
    }
}
