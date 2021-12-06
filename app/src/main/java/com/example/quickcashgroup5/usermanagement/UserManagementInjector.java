package com.example.quickcashgroup5.usermanagement;

public class UserManagementInjector {
    private static UserManagementInjector instance = null;
    private final IUserManagementAbstractFactory userAbstractFactory;

    private UserManagementInjector() {
        userAbstractFactory = new UserManagementAbstractFactory();
    }

    public static UserManagementInjector getInstance() {
        if (instance == null) {
            instance = new UserManagementInjector();
        }
        return instance;
    }

    public IUserManagementAbstractFactory getUserAbstractFactory() {
        return userAbstractFactory;
    }
}
