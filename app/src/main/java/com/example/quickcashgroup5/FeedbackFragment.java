package com.example.quickcashgroup5;

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
import com.example.quickcashgroup5.UserManagement.Feedback;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FeedbackFragment extends Fragment {
    Feedback feedback;
    TextView name;
    RatingBar rating;
    TextView userFeedback;

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

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