package com.example.quickcashgroup5;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.UserManagement.Feedback;
import com.example.quickcashgroup5.UserManagement.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;

public class DatabaseManagement {
    FirebaseDatabase database;

    protected DatabaseManagement() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
    }

    public boolean isPreviousUser(String email) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());

        AtomicBoolean userExists = new AtomicBoolean(false);
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if (u.getEmail().equals(email)) {
                        System.out.println("Old User");
                        userExists.set(true);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("New User");
            }
        });
        return userExists.get();
    }

    private boolean add(User user) {
        DatabaseReference users = database.getReference(User.class.getSimpleName());
        Task<Void> task = users.push().setValue(user);
        AtomicBoolean success = new AtomicBoolean(false);

        task.addOnSuccessListener(suc -> {
            success.set(true);
        }).addOnFailureListener(fal -> {
            success.set(false);
        });

        return success.get();
    }
}
