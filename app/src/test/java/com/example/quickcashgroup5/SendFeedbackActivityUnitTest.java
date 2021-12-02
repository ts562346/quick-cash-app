//package com.example.quickcashgroup5;
//
//import static org.junit.Assert.assertEquals;
//
//import com.example.quickcashgroup5.UserManagement.JobPosting;
//import com.example.quickcashgroup5.UserManagement.SessionManagement;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//public class SendFeedbackActivityUnitTest {
//    private static SessionManagement sessionManagement;
//    private static String name = "JohnDoe";
//    private static String email = "john.doe@gmail.com";
//    private static String userType = "Employee";
//    private static String userFeedback = "Great app!";
//    private static int rating = 5;
//
//    @BeforeClass
//    public static void setup() {
//        sessionManagement = Mockito.mock(SessionManagement.class);
//        Mockito.when(sessionManagement.getName()).thenReturn(name);
//        Mockito.when(sessionManagement.getEmail()).thenReturn(email);
//        Mockito.when(sessionManagement.getRole()).thenReturn(userType);
//        Mockito.when(sessionManagement.isLoggedIn()).thenReturn(true);
//
//        firstOffer = Mockito.mock(IPromoOffer.class);
//        Mockito.when(firstOffer.getOffer()).thenReturn("This is a cool offer!");
//        Mockito.when(firstOffer.getExpiredDate()).thenReturn("2021-12-31");
//        secondOffer = Mockito.mock(IPromoOffer.class);
//        Mockito.when(secondOffer.getExpiredDate()).thenReturn("2021-09-06");
//    }
//
//    @Test
//    public void checkIfFeedbackIsInserted() {
//        editTextTitle.setText(jobTitle);
//        editTextLocation.setText(location);
//        editTextPayment.setText(payment);
//        editTextDuration.setText(duration);
//        spinnerCategory.setSelection(2);
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
//        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getName();
//        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getEmail();
//        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).getRole();
//        Mockito.verify(sessionManagement, Mockito.atLeastOnce()).isLoggedIn();
//
//        Mockito.verify(, Mockito.atLeastOnce()).getCategory();
//        Mockito.verify(, Mockito.atLeastOnce()).getCreatorEmail();
//        Mockito.verify(, Mockito.atLeastOnce()).getDuration();
//        Mockito.verify(, Mockito.atLeastOnce()).getLocation();
//        Mockito.verify(, Mockito.atLeastOnce()).getPayment();
//        Mockito.verify(, Mockito.atLeastOnce()).getSelectedApplicantEmail();
//    }
//}