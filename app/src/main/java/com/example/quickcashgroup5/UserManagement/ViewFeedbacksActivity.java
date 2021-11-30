package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.FeedbackFragment;
import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewFeedbacksActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference feedbacks;
    Button submit;
    ValueEventListener event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewfeedbacks);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ViewFeedbacksActivity.this, SendFeedbackActivity.class));
                feedbacks.child("Feedback").removeEventListener(event);
                ((Activity) ViewFeedbacksActivity.this).finish();
            }
        });

        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        feedbacks = database.getReference();

        find();
    }

    protected void find(){
        event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    Feedback feedback = adSnapshot.getValue(Feedback.class);
                    addToLayout(feedback);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        feedbacks.child("Feedback").addValueEventListener(event);
    }

    //https://www.c-sharpcorner.com/UploadFile/1e5156/dynamically-add-fragment-in-android-studio/
    protected void addToLayout(Feedback feedback){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        FeedbackFragment fm2 = new FeedbackFragment();
        fm2.setFeedback(feedback);
        fragmentTransaction.add(R.id.dynamicLayout, fm2, null);
        fragmentTransaction.commit();
    }
}
