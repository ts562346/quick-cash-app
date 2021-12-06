package com.example.quickcashgroup5.notificationmanagement;

public interface ISubject {

    public void attach(Observer user);

    public void notifyAllUsers();
}
