package com.example.quickcashgroup5.notificationmanagement;

/**
 * Abstract Observer class
 */
public abstract class Observer {
    public ISubject manager;

    /**
     * Notifies the user's email
     * @param subject
     * @param message
     */
    public abstract void update(String subject, String message);
}
