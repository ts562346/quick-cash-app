package com.example.quickcashgroup5.notificationmanagement;

/**
 * Interface of Subject
 */
public interface ISubject {

    /**
     * Attaches user
     *
     * @param user
     */
    public void attach(Observer user);

    /**
     * Notifies all users
     */
    public void notifyAllUsers();
}
