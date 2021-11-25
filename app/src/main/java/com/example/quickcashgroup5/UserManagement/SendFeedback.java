package com.example.quickcashgroup5.UserManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendFeedback extends AppCompatActivity {

    //variable setting
    //(chage the names in the frontend as well)
    protected EditText editTextName;
    protected EditText editTextUserType;
    protected EditText editTextFeedback;
    protected RatingBar starRating;
    protected SessionManagement sessionManagement;
    protected Button submit;
    protected FirebaseDatabase database;
    protected DatabaseReference userFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sendfeedback);

        editTextName = findViewById(R.id.editTextName);
        editTextUserType = findViewById(R.id.editTextUserType);
        editTextFeedback = findViewById(R.id.editTextFeedback);
        starRating = findViewById(R.id.rating);
        submit = findViewById(R.id.submit);

        editTextName.setText(sessionManagement.getName());
        editTextUserType.setText(sessionManagement.getRole());
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendFeedback();
            }
        });

        initializeDatabase();
    }

    private boolean createFeedback(Feedback feedback){
        String name = editTextName.getText().toString().trim();
        String userType = editTextUserType.getText().toString().trim();
        String userFeedback = editTextFeedback.getText().toString().trim();
        int rating = (int) starRating.getRating();

        feedback.setName(name);
        feedback.setUserType(userType);
        feedback.setUserFeedback(userFeedback);
        feedback.setRating(rating);

        return true;
    }

    /**
     * Adds JobPosting object to the Firebase Database
     *
     * @param feedback
     * @return
     */
    protected Task<Void> add(Feedback feedback) {
        return userFeedbacks.push().setValue(feedback);
    }

    protected void sendFeedback(){
        Feedback feedback = new Feedback();
        try {
            if (createFeedback(feedback)) {
                this.add(feedback).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Successfully sent the feedback", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, LogInActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(fal -> {
                    Toast.makeText(this, "Unsuccessfully sent the feedback", Toast.LENGTH_SHORT).show();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the firebase database
     */
    protected void initializeDatabase() {
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        userFeedbacks = database.getReference(Feedback.class.getSimpleName());
    }

}
