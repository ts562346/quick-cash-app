package com.example.quickcashgroup5.UserManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobPosting {

    String title;
    String location;
    String payment;
    String duration;
    String category;
    String date;
    String creatorEmail;
    String selectedApplicantEmail;
    List<String> appliedApplicants;

    public JobPosting(){
        this.appliedApplicants = new ArrayList<String>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreatorEmail(){
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail){this.creatorEmail = creatorEmail;}

    public String getSelectedApplicantEmail() {
        return selectedApplicantEmail;
    }

    public void setSelectedApplicantEmail(String selectedApplicantEmail) {
        this.selectedApplicantEmail = selectedApplicantEmail;
    }

    public Object getAppliedApplicant(int key) {
        return appliedApplicants.get(key);
    }

    public void addAppliedApplicants(String value) {
        this.appliedApplicants.add(value);
    }
}
