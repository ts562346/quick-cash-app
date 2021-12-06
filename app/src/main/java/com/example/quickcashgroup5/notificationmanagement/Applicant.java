package com.example.quickcashgroup5.notificationmanagement;

import com.example.quickcashgroup5.emailmanagement.SendNotification;

/**
 * Applicant class that extends Observer
 */
public class Applicant extends Observer {
    //Initialize variables
    private String email;
    String currentSubject;
    String currentMessage;
    SendNotification sendNotification;

    /**
     * Constructor of Applicant
     *
     * @param email
     * @param manager
     */
    public Applicant(String email, ISubject manager) {
        this.email = email;
        this.manager = manager;
        this.manager.attach(this);
    }

    /**
     * Sends notification the applicant
     *
     * @param subject
     * @param message
     */
    @Override
    public void update(String subject, String message) {
        this.currentSubject = subject;
        this.currentMessage = message;
        sendNotification = new SendNotification(this.email, this.currentSubject, this.currentMessage);
        sendNotification.execute();
    }
}