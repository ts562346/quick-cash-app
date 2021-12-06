package com.example.quickcashgroup5.usermanagement;

/**
 * Interface for SessionManagement
 */
public interface ISessionManagement {
    /**
     * Gets name
     *
     * @return
     */
    String getName();

    /**
     * Sets name
     *
     * @param name
     */
    void setName(String name);

    /**
     * Gets Email
     *
     * @return
     */
    String getEmail();

    /**
     * Sets Email
     *
     * @param email
     */
    void setEmail(String email);

    /**
     * Gets Role
     **/
    String getRole();

    /**
     * Sets Role
     *
     * @param role
     */
    void setRole(String role);

    /**
     * Gets OTP
     *
     * @return
     */
    int getOTP();

    /**
     * Sets OTP
     *
     * @param otp
     */
    void setOTP(int otp);

    /**
     * Checks if user is logged in
     *
     * @return
     */
    boolean isLoggedIn();

    /**
     * Creates Session
     *
     * @param name
     * @param email
     * @param role
     */
    void createSession(String name, String email, String role);

    /**
     * Implements AccessControl
     */
    void accessControl();

    /**
     * Clears the session
     */
    void clearSession();
}
