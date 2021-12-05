package com.example.quickcashgroup5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.annotation.NonNull;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.quickcashgroup5.JobCreation.CreateJobActivity;
import com.example.quickcashgroup5.JobCreation.JobPosting;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

public class CreateJobUnitTest extends CreateJobActivity {
    private final String jobTitle = "1234567890";
    private final String location = "Halifax, Nova Scotia";
    private static final String creatorEmail = "espresso@bot.test";
    private final String payment = "15";
    private final String duration = "12";
    private final String category = "Computer Repair";

    @Test
    public void checkIfJobIsInserted() {
        editTextTitle.setText(jobTitle);
        editTextLocation.setText(location);
        editTextPayment.setText(payment);
        editTextDuration.setText(duration);
        spinnerCategory.setSelection(2);
        sessionManagement.setEmail(creatorEmail);

        insertJob();

        jobPostings.child("JobPosting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    JobPosting job = adSnapshot.getValue(JobPosting.class);
                    if (job.getTitle().equals(jobTitle)) {
                        try {
                            assertEquals(job.getCategory(), category);
                            assertEquals(job.getCreatorEmail(), creatorEmail);
                            assertEquals(job.getDuration(), duration);
                            assertEquals(job.getLocation(), location);
                            assertEquals(job.getPayment(), payment);
                            assertEquals(job.getSelectedApplicantEmail(), "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
