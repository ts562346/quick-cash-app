package com.example.quickcashgroup5.usermanagement;

public interface IUser {
    String getName();

    void setName(String name);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    String getIsEmployee();

    void setIsEmployee(String isEmployee);

    String getPreferredLocation();

    void setPreferredLocation(String preferredLocation);

    String getPreferredCategory();

    void setPreferredCategory(String preferredCategory);

    String getPreferredPayment();

    void setPreferredPayment(String preferredPayment);

    String getPreferredHours();

    void setPreferredHours(String preferredHours);
}
