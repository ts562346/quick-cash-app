package com.example.quickcashgroup5.usermanagement;

/**
 * Interface of the User Class
 */
public interface IUser {
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
     * Gets email
     *
     * @return
     */
    String getEmail();

    /**
     * Sets email
     *
     * @param email
     */
    void setEmail(String email);

    /**
     * Gets Password
     *
     * @return
     */
    String getPassword();

    /**
     * Sets Password
     *
     * @param password
     */
    void setPassword(String password);

    /**
     * Get IsEmployee
     *
     * @return
     */
    String getIsEmployee();

    /**
     * Set IsEmployee
     *
     * @param isEmployee
     */
    void setIsEmployee(String isEmployee);

    /**
     * Get the preferedLocation
     *
     * @return
     */
    String getPreferredLocation();

    /**
     * Set the preferredLocatio
     *
     * @param preferredLocation
     */
    void setPreferredLocation(String preferredLocation);

    /**
     * Get the Preferred Category
     *
     * @return
     */
    String getPreferredCategory();

    /**
     * Sets the Preferred Category
     *
     * @param preferredCategory
     */
    void setPreferredCategory(String preferredCategory);

    /**
     * Gets the preferred Payement
     *
     * @return
     */
    String getPreferredPayment();

    /**
     * Sets the preferred payment
     *
     * @param preferredPayment
     */
    void setPreferredPayment(String preferredPayment);

    /**
     * Gets the Preferred hours
     *
     * @return
     */
    String getPreferredHours();

    /**
     * Sets the preferred hours
     *
     * @param preferredHours
     */
    void setPreferredHours(String preferredHours);
}
