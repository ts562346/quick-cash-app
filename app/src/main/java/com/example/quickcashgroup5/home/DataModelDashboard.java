package com.example.quickcashgroup5.home;

import com.example.quickcashgroup5.jobcreation.JobPosting;

/**
 * DataModel for Dashboard
 */
public class DataModelDashboard {
    JobPosting jobPosting;
    String jobTitle;
    String status;
    String key;

    /**
     * Gets key
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets JobTitles
     *
     * @return
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Sets JobTitle
     *
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * Get JobPostings
     *
     * @return
     */
    public JobPosting getJobPosting() {
        return jobPosting;
    }

    /**
     * Get Status
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set Status
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Constructor for this class
     *
     * @param jobPosting
     * @param key
     */
    public DataModelDashboard(JobPosting jobPosting, String key) {
        this.key = key;
        this.jobPosting = jobPosting;
        this.status = jobPosting.getStatus();
        this.jobTitle = jobPosting.getTitle();
    }
}
