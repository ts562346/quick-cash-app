package com.example.quickcashgroup5.notificationmanagement;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationManager implements ISubject {

    private List<Observer> users = new ArrayList<>();
    private String subject;
    private String message;

    @Override
    public void attach(Observer user) {
        this.users.add(user);
    }

    public void setNotification(String subject, String message) {
        this.subject = subject;
        this.message = message;
        this.notifyAllUsers();
    }

    @Override
    public void notifyAllUsers() {
        for (Observer user:users) {
            user.update(subject, message);
        }
    }
}
