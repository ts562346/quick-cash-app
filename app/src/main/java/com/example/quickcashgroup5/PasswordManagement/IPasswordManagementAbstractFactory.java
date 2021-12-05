package com.example.quickcashgroup5.PasswordManagement;

import android.content.Context;
import android.content.Intent;

public interface IPasswordManagementAbstractFactory {
    Intent getIntentInstance(Context context, Class<?> c);

    IAESCrypt getAesCryptInstance();
}
