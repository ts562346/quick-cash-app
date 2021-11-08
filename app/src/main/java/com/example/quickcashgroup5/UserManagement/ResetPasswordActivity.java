package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quickcashgroup5.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordActivity extends Activity implements View.OnClickListener {
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonReset;
    SessionManagement sessionManagement;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        sessionManagement.accessControl();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
//        getSupportActionBar().hide();
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
        buttonReset = (Button) findViewById(R.id.resetButton);
        buttonReset.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
    }

    @Override
    public void onClick(View v) {
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(password.equals(confirmPassword)){
            changePassword(password);
            Toast.makeText(this, "Your password has successfully been reset", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Your password should match the confirm password", Toast.LENGTH_SHORT).show();
        }
    }

    public void changePassword(String newPassword){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        DatabaseReference users = database.getReference();
        AESCrypt aes = new AESCrypt();
        users.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot adSnapshot : dataSnapshot.getChildren()) {
                    User u = adSnapshot.getValue(User.class);
                    if (u.getEmail().equals(email)) {
                        try {
                            Map<String, Object> updates = new HashMap<String,Object>();
                            updates.put("password", aes.encrypt(newPassword));
                            adSnapshot.getRef().updateChildren(updates);
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
