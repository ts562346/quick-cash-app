package com.example.quickcashgroup5.JobCreation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public List<String> getAppliedApplicants() {
        return appliedApplicants;
    }

    public JobPosting(){
        this.appliedApplicants = new ArrayList<String>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getPayment() {
        return payment;
    }

    @Override
    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getCreatorEmail(){
        return creatorEmail;
    }

    @Override
    public void setCreatorEmail(String creatorEmail){this.creatorEmail = creatorEmail;}

    @Override
    public String getSelectedApplicantEmail() {
        return selectedApplicantEmail;
    }

    @Override
    public void setSelectedApplicantEmail(String selectedApplicantEmail) {
        this.selectedApplicantEmail = selectedApplicantEmail;
    }

    @Override
    public Object getAppliedApplicant(int key) {
        return appliedApplicants.get(key);
    }

    @Override
    public void addAppliedApplicants(String value) {
        this.appliedApplicants.add(value);
    }
}
