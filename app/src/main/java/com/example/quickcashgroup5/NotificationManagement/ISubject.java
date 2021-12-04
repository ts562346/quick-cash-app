package com.example.quickcashgroup5.NotificationManagement;

public interface ISubject {

    public void attach(Observer user);

    public void notifyAllUsers();
}
