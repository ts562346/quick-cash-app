package com.example.quickcashgroup5.passwordmanagement;

/**
 * Class to inject Password Management
 */
public class PasswordManagementInjector {
    private static PasswordManagementInjector instance = null;
    private final IPasswordManagementAbstractFactory passwordManagementAbstractFactory;

    /**
     * Constructor of the class
     */
    private PasswordManagementInjector() {
        passwordManagementAbstractFactory = new PasswordManagementAbstractFactory();
    }

    /**
     * Gets the Instance of this class
     *
     * @return
     */
    public static PasswordManagementInjector getInstance() {
        if (instance == null) {
            instance = new PasswordManagementInjector();
        }
        return instance;
    }

    /**
     * Returns a PasswordManagementAbstractFactory
     *
     * @return
     */
    public IPasswordManagementAbstractFactory getPasswordManagementAbstractFactory() {
        return passwordManagementAbstractFactory;
    }
}
