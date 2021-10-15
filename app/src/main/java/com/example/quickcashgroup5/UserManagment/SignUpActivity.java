package com.example.quickcashgroup5.UserManagment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;



public class SignUpActivity extends AppCompatActivity {
    Button   button;
    EditText name;
    EditText email;
    EditText password;
    EditText confirmPassword;
//    EditText mEdit;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        User user= new User();

        button = (Button)findViewById(R.id.button);
        name   = (EditText)findViewById(R.id.edittext);
        email   = (EditText)findViewById(R.id.edittext);
        password   = (EditText)findViewById(R.id.edittext);
        confirmPassword   = (EditText)findViewById(R.id.edittext);
//        mEdit   = (EditText)findViewById(R.id.edittext);

        button.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    user.setName(name.getText().toString());
                    user.setEmail(email.getText().toString());
                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        user.setPassword(password.getText().toString());
                    }else{

                    }

                }
            });
    }
}
