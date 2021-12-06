package com.example.quickcashgroup5.notificationmanagement;

public abstract class Observer {

    public ISubject manager;

    public abstract void update(String subject, String message);
}
