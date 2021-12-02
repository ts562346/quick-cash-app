//package com.example.quickcashgroup5;
//
//import static org.hamcrest.core.IsInstanceOf.instanceOf;
//import static org.hamcrest.core.StringContains.containsString;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import androidx.annotation.NonNull;
////import androidx.test.espresso.action.ViewActions;
////import androidx.test.ext.junit.rules.ActivityScenarioRule;
//
//import com.example.quickcashgroup5.UserManagement.CreateJob;
//import com.example.quickcashgroup5.UserManagement.JobPosting;
//import com.example.quickcashgroup5.UserManagement.LogInActivity;
//import com.example.quickcashgroup5.UserManagement.SessionManagement;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//public class CreateJobUnitTest {
//    private String jobTitle = "1234567890";
//    private String location = "Halifax, Nova Scotia";
//    private static String creatorEmail = "espresso@bot.test";
//    private String payment = "15";
//    private String duration = "12";
//    private String category = "Computer Repair";
//
//    @BeforeClass
//    public static void setup() {
//        //Mock the IPromoOffer interface
//        firstOffer = Mockito.mock(IPromoOffer.class);
//        Mockito.when(firstOffer.getOffer()).thenReturn("This is a cool offer!");
//        Mockito.when(firstOffer.getExpiredDate()).thenReturn("2021-12-31");
//        secondOffer = Mockito.mock(IPromoOffer.class);
//        Mockito.when(secondOffer.getExpiredDate()).thenReturn("2021-09-06");
//    }
//
//    @Test
//    public void checkIfJobIsInserted() {
//        editTextTitle.setText(jobTitle);
//        editTextLocation.setText(location);
//        editTextPayment.setText(payment);
//        editTextDuration.setText(duration);
//        spinnerCategory.setSelection(2);
//        sessionManagement.setEmail(creatorEmail);
//
//        insertJob();
//
//        JobPosting job = adSnapshot.getValue(JobPosting.class);
//
//        assertEquals(job.getCategory(), category);
//        assertEquals(job.getCreatorEmail(), creatorEmail);
//        assertEquals(job.getDuration(), duration);
//        assertEquals(job.getLocation(), location);
//        assertEquals(job.getPayment(), payment);
//        assertEquals(job.getSelectedApplicantEmail(), "");
//
//        Mockito.verify(, Mockito.atLeastOnce()).getCategory();
//        Mockito.verify(, Mockito.atLeastOnce()).getCreatorEmail();
//        Mockito.verify(, Mockito.atLeastOnce()).getDuration();
//        Mockito.verify(, Mockito.atLeastOnce()).getLocation();
//        Mockito.verify(, Mockito.atLeastOnce()).getPayment();
//        Mockito.verify(, Mockito.atLeastOnce()).getSelectedApplicantEmail();
//}
