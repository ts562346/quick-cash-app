package com.example.quickcashgroup5.NotificationManagement;

/**
 * User class to store input data of users
 */

public class Applicant extends Observer {
    //Initialize variables
    private String email;
    String currentSubject;
    String currentMessage;
    SendNotification sendNotification;

    public Applicant(String email, ISubject manager) {
        this.email = email;
        this.manager = manager;
        this.manager.attach(this);
    }

    @Override
    public void update(String subject, String message) {
        this.currentSubject = subject;
        this.currentMessage = message;
        sendNotification = new SendNotification(this.email, this.currentSubject, this.currentMessage);
        sendNotification.execute();
    }
}