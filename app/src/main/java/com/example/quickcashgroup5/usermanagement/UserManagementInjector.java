package com.example.quickcashgroup5.usermanagement;

/**
 * UserManagementInjector
 */
public class UserManagementInjector {
    private static UserManagementInjector instance = null;
    private final IUserManagementAbstractFactory userAbstractFactory;

    /**
     * Constructor of class
     */
    private UserManagementInjector() {
        userAbstractFactory = new UserManagementAbstractFactory();
    }

    /**
     * Gets instance of the class
     *
     * @return
     */
    public static UserManagementInjector getInstance() {
        if (instance == null) {
            instance = new UserManagementInjector();
        }
        return instance;
    }

    /**
     * Gets instace of UserAbstractFactory
     *
     * @return
     */
    public IUserManagementAbstractFactory getUserAbstractFactory() {
        return userAbstractFactory;
    }
}
