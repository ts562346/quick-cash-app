package com.example.quickcashgroup5.passwordmanagement;

public class PasswordManagementInjector {
    private static PasswordManagementInjector instance = null;
    private final IPasswordManagementAbstractFactory passwordManagementAbstractFactory;

    private PasswordManagementInjector() {
        passwordManagementAbstractFactory = new PasswordManagementAbstractFactory();
    }

    public static PasswordManagementInjector getInstance() {
        if (instance == null) {
            instance = new PasswordManagementInjector();
        }
        return instance;
    }

    public IPasswordManagementAbstractFactory getPasswordManagementAbstractFactory() {
        return passwordManagementAbstractFactory;
    }
}
