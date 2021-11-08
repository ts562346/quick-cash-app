package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickcashgroup5.R;

public class ResetPasswordActivity extends Activity implements View.OnClickListener {
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonReset;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        sessionManagement.accessControl();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
//        getSupportActionBar().hide();
//        editTextPassword = (EditText) findViewById(R.id.password);
//        editTextConfirmPassword = (EditText) findViewById(R.id.confirmPassword);
//        buttonReset = (Button) findViewById(R.id.resetButton);
        buttonReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if(password.equals(confirmPassword)){
            Toast.makeText(this, "Your password has successfully been reset", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Your password should match the confirm password", Toast.LENGTH_SHORT).show();
        }
    }

}
