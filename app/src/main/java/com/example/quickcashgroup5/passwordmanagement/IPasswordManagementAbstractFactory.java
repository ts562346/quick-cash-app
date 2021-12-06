package com.example.quickcashgroup5.passwordmanagement;

import android.content.Context;
import android.content.Intent;

public interface IPasswordManagementAbstractFactory {
    Intent getIntentInstance(Context context, Class<?> c);

    IAESCrypt getAesCryptInstance();
}
