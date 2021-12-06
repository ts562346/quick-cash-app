package com.example.quickcashgroup5.notificationmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * EmailNotificationManger that implement ISubject
 */
public class EmailNotificationManager implements ISubject {

    private List<Observer> users = new ArrayList<>();
    private String subject;
    private String message;

    /**
     * Attached observer to the list
     *
     * @param user
     */
    @Override
    public void attach(Observer user) {
        this.users.add(user);
    }

    /**
     * Sets and send the notification
     *
     * @param subject
     * @param message
     */
    public void setNotification(String subject, String message) {
        this.subject = subject;
        this.message = message;
        this.notifyAllUsers();
    }

    /**
     * Send notification to all observers
     */
    @Override
    public void notifyAllUsers() {
        for (Observer user : users) {
            user.update(subject, message);
        }
    }
}
