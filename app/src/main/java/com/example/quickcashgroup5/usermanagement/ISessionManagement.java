package com.example.quickcashgroup5.usermanagement;

public interface ISessionManagement {
    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    String getRole();

    void setRole(String role);

    int getOTP();

    void setOTP(int otp);

    boolean isLoggedIn();

    void createSession(String name, String email, String role);

    void accessControl();

    void clearSession();
}
