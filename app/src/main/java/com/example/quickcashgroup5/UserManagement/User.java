package com.example.quickcashgroup5.UserManagement;

/**
 * User class to store input data of users
 */

public class User {
    //Initialize variables
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String isEmployee; //if its y then yes(employee), otherwise no(employer)

    /**
     * Constructor for the user class
     *
     * @param name
     * @param email
     * @param password
     * @param confirmPassword
     * @param isEmployee
     */
    public User(String name, String email, String password, String confirmPassword, String isEmployee) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isEmployee = isEmployee;
    }

    /**
     * Empty constructor
     */
    public User() {
    }

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
     * Gets the confirm password form the class
     *
     * @return
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password for the class
     *
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
}
