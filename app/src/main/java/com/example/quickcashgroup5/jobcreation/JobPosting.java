package com.example.quickcashgroup5.jobcreation;

import java.util.ArrayList;
import java.util.List;

/**
 * JobPosting Class
 */
public class JobPosting implements IJobPosting {

    String title;
    String location;
    String payment;
    String duration;
    String category;
    String date;
    String creatorEmail;
    String selectedApplicantEmail;
    String status;


    List<String> appliedApplicants;

    /**
     * Gets Status
     *
     * @return
     */
    @Override
    public String getStatus() {
        return status;
    }

    /**
     * Sets Status
     *
     * @param status
     */
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets Applied Applicants
     *
     * @return
     */
    @Override
    public List<String> getAppliedApplicants() {
        return appliedApplicants;
    }

    /**
     * JobPosting Constructor
     */
    public JobPosting() {
        this.appliedApplicants = new ArrayList<>();
    }

    /**
     * Gets Title
     *
     * @return
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets title
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets Location
     *
     * @return
     */
    @Override
    public String getLocation() {
        return location;
    }

    /**
     * Sets Location
     *
     * @param location
     */
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets Payment
     *
     * @return
     */
    @Override
    public String getPayment() {
        return payment;
    }

    /**
     * Sets Payments
     *
     * @param payment
     */
    @Override
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * Gets Duration
     *
     * @return
     */
    @Override
    public String getDuration() {
        return duration;
    }

    /**
     * Sets Duration
     *
     * @param duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Gets Category
     *
     * @return
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Sets Category
     *
     * @param category
     */
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets date
     *
     * @return
     */
    @Override
    public String getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date
     */
    @Override
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets Creator Email
     *
     * @return
     */
    @Override
    public String getCreatorEmail() {
        return creatorEmail;
    }

    /**
     * Set Creator Email
     *
     * @param creatorEmail
     */
    @Override
    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    /**
     * Gets Selected Applicant Email
     *
     * @return
     */
    @Override
    public String getSelectedApplicantEmail() {
        return selectedApplicantEmail;
    }

    /**
     * Set Selected Applicant Email
     *
     * @param selectedApplicantEmail
     */
    @Override
    public void setSelectedApplicantEmail(String selectedApplicantEmail) {
        this.selectedApplicantEmail = selectedApplicantEmail;
    }

    /**
     * Get Applied Applicant
     *
     * @param key
     * @return
     */
    @Override
    public Object getAppliedApplicant(int key) {
        return appliedApplicants.get(key);
    }

    /**
     * Adds Applied Applicants
     *
     * @param value
     */
    @Override
    public void addAppliedApplicants(String value) {
        this.appliedApplicants.add(value);
    }
}
