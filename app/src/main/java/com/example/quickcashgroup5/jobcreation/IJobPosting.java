package com.example.quickcashgroup5.jobcreation;

import java.util.List;

public interface IJobPosting {
    String getStatus();

    void setStatus(String status);

    List<String> getAppliedApplicants();

    String getTitle();

    void setTitle(String title);

    String getLocation();

    void setLocation(String location);

    String getPayment();

    void setPayment(String payment);

    String getDuration();

    String getCategory();

    void setCategory(String category);

    String getDate();

    void setDate(String date);

    String getCreatorEmail();

    void setCreatorEmail(String creatorEmail);

    String getSelectedApplicantEmail();

    void setSelectedApplicantEmail(String selectedApplicantEmail);

    Object getAppliedApplicant(int key);

    void addAppliedApplicants(String value);
}
