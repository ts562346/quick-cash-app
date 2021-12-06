package com.example.quickcashgroup5.usermanagement;

/**
 * User class to store input data of users
 */

public class User implements IUser {
    //Initialize variables
    private String name;
    private String email;
    private String password;
    private String isEmployee; //if its yes then yes(employee), otherwise no(employer)
    private String preferredLocation;
    private String preferredCategory;
    private String preferredPayment;
    private String preferredHours;

    /**
     * Constructor for the user class
     *
     * @param name
     * @param email
     * @param password
     * @param isEmployee
     */
    public User(String name, String email, String password, String isEmployee) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isEmployee = isEmployee;
    }


    /**
     * Empty constructor
     */
    public User() {}

    /**
     * Gets the name from the class
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the class
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email from the class
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the class
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password from the class
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the class
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the status whether user IsEmployee
     *
     * @return
     */
    public String getIsEmployee() {
        return isEmployee;
    }

    /**
     * Sets the IsEmployee boolean depending on whether user is employee
     *
     * @param isEmployee
     */
    public void setIsEmployee(String isEmployee) {
        this.isEmployee = isEmployee;
    }


    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPreferredCategory() {
        return preferredCategory;
    }

    public void setPreferredCategory(String preferredCategory) {
        this.preferredCategory = preferredCategory;
    }

    public String getPreferredPayment() {
        return preferredPayment;
    }

    public void setPreferredPayment(String preferredPayment) {
        this.preferredPayment = preferredPayment;
    }

    public String getPreferredHours() {
        return preferredHours;
    }

    public void setPreferredHours(String preferredHours) {
        this.preferredHours = preferredHours;
    }

}