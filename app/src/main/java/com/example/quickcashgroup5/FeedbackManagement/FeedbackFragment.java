package com.example.quickcashgroup5.FeedbackManagement;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quickcashgroup5.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
/**
 * The fragment class for the Feedback
 */
public class FeedbackFragment extends Fragment {
    Feedback feedback;
    TextView name;
    RatingBar rating;
    TextView userFeedback;

    /**
     * Sets the feedback
     * @param feedback
     */
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    /**
     * Runs when the view is created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, null);

        name = v.findViewById(R.id.name);
        rating = v.findViewById(R.id.rating);
        userFeedback = v.findViewById(R.id.feedback);

        name.setText(feedback.getUserType() + ": " + feedback.getName());
        rating.setRating(feedback.getRating());
        userFeedback.setText(feedback.getUserFeedback());

        return v;
    }
}