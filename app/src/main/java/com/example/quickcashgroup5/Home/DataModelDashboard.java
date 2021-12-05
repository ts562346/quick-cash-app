package com.example.quickcashgroup5.Home;

import com.example.quickcashgroup5.JobCreation.JobPosting;

public class DataModelDashboard {
    JobPosting jobPosting;
    String jobTitle;
    String status;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataModelDashboard(JobPosting jobPosting, String key) {
        this.key=key;
        this.jobPosting = jobPosting;
        this.status = jobPosting.getStatus();
        this.jobTitle = jobPosting.getTitle();
    }
}
