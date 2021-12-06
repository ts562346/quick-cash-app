package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.quickcashgroup5.jobcreation.JobPosting;

import org.junit.Test;

public class JobPostingUnitTest {
    private String title = "This is a unit test";
    private String location = "Halifax, Canada";
    private String payment = "15";
    private String duration = "20";
    private String category = "Computer Repair";
    private String creatorEmail = "espresso@bot.test";
    private String date = "10/11/21";
    private String selectedApplicantEmail = "selected@applicant.com";
    private String appliedApplicant1 = "applicant1@applied.com";
    private String appliedApplicant2 = "applicant2@applied.com";
    private String appliedApplicant3 = "applicant3@applied.com";

    @Test
    public void jobPostingConstructor() {
        JobPosting jobPosting = new JobPosting();
        assertNotNull(jobPosting);
    }

    @Test
    public void jobPostingGetSet() {
        JobPosting jobPosting = new JobPosting();

        jobPosting.setTitle(title);
        assertEquals(jobPosting.getTitle(), title);

        jobPosting.setLocation(location);
        assertEquals(jobPosting.getLocation(), location);

        jobPosting.setPayment(payment);
        assertEquals(jobPosting.getPayment(), payment);

        jobPosting.setDuration(duration);
        assertEquals(jobPosting.getDuration(), duration);

        jobPosting.setCategory(category);
        assertEquals(jobPosting.getCategory(), category);

        jobPosting.setCreatorEmail(creatorEmail);
        assertEquals(jobPosting.getCreatorEmail(), creatorEmail);

        jobPosting.setDate(date);
        assertEquals(jobPosting.getDate(), date);

        jobPosting.setSelectedApplicantEmail(selectedApplicantEmail);
        assertEquals(jobPosting.getSelectedApplicantEmail(), selectedApplicantEmail);

        jobPosting.addAppliedApplicants(appliedApplicant1);
        jobPosting.addAppliedApplicants(appliedApplicant2);
        jobPosting.addAppliedApplicants(appliedApplicant3);
        assertEquals(jobPosting.getAppliedApplicant(0), appliedApplicant1);
        assertEquals(jobPosting.getAppliedApplicant(1), appliedApplicant2);
        assertEquals(jobPosting.getAppliedApplicant(2), appliedApplicant3);
    }

}
