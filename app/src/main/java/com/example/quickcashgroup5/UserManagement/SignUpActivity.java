package com.example.quickcashgroup5.UserManagement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.MainActivity;
import com.example.quickcashgroup5.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    Button  registerButton;
    EditText name,
    email,
    password,
    confirmPassword;
//    EditText mEdit;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
    DatabaseReference users = database.getReference(User.class.getSimpleName());

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerButton = (Button)findViewById(R.id.registerButton);
        name = (EditText)findViewById(R.id.editTextUsername);
        email = (EditText)findViewById(R.id.editTextTextEmailAddress);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        confirmPassword = (EditText)findViewById(R.id.editTextTextConfirmPassword);
        registerButton.setOnClickListener(this);

        initializeDatabase();


    }
    protected boolean isValidEmailAddress(String emailAddress) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(emailAddress);
        return m.matches();
    }

    private boolean registerUser(User user){
        if(!name.getText().toString().isEmpty()){
            user.setName(name.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(),"User name is empty",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!email.getText().toString().isEmpty() && isValidEmailAddress(email.getText().toString())){
            user.setEmail(email.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().equals(confirmPassword.getText().toString())){
            user.setPassword(password.getText().toString());
        }else{
            Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
            return false;
        }
        user.setIsEmployee("y");
        return true;
    }
    protected void initializeDatabase() {
        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quick-cash-group-project-default-rtdb.firebaseio.com/");
        users = database.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user){
        return users.push().setValue(user);
    }

    @Override
    public void onClick(View view) {
        User user= new User();
        if(registerUser(user)){
            this.add(user).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(fal -> {
                Toast.makeText(this, "Data Insertion failed", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
