package com.example.quickcashgroup5.jobcreation;

import java.util.List;

/**
 * Interface of JobPosting
 */
public interface IJobPosting {
    /**
     * Gets the status
     *
     * @return
     */
    String getStatus();

    /**
     * Sets Status
     *
     * @param status
     */
    void setStatus(String status);

    /**
     * Gets AppliedApplicants
     *
     * @return
     */
    List<String> getAppliedApplicants();

    /**
     * Gets title
     *
     * @return
     */
    String getTitle();

    /**
     * Sets title
     *
     * @param title
     */
    void setTitle(String title);

    /**
     * Gets location
     *
     * @return
     */
    String getLocation();

    /**
     * Sets location
     *
     * @param location
     */
    void setLocation(String location);

    /**
     * Gets Payment
     *
     * @return
     */
    String getPayment();

    /**
     * Sets payment
     *
     * @param payment
     */
    void setPayment(String payment);

    /**
     * Gets duration
     *
     * @return
     */
    String getDuration();

    /**
     * Get Category
     *
     * @return
     */
    String getCategory();

    /**
     * Set Category
     *
     * @param category
     */
    void setCategory(String category);

    /**
     * Gets Date
     *
     * @return
     */
    String getDate();

    /**
     * Sets Date
     *
     * @param date
     */
    void setDate(String date);

    /**
     * Get Creator Email
     *
     * @return
     */
    String getCreatorEmail();

    /**
     * Sets Creator Email
     *
     * @param creatorEmail
     */
    void setCreatorEmail(String creatorEmail);

    /**
     * Gets Selected Applicant Email
     *
     * @return
     */
    String getSelectedApplicantEmail();

    /**
     * Sets Seleceted Applicant Email
     *
     * @param selectedApplicantEmail
     */
    void setSelectedApplicantEmail(String selectedApplicantEmail);

    /**
     * Gets applied applicant
     *
     * @param key
     * @return
     */
    Object getAppliedApplicant(int key);

    /**
     * Adds applied applicants
     *
     * @param value
     */
    void addAppliedApplicants(String value);
}
