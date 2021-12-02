package com.example.quickcashgroup5.UserManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickcashgroup5.R;

public class PasswordResetCodeActivity extends Activity implements View.OnClickListener{
    private EditText editTextCode;
    private Button buttonSend;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManagement = new SessionManagement(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordresetcode);
//        getSupportActionBar().hide();
        editTextCode = (EditText) findViewById(R.id.code);
        buttonSend = (Button) findViewById(R.id.sendButton);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int inputCode = Integer.parseInt(editTextCode.getText().toString().trim());
        int resetCode = sessionManagement.getOTP();
        if(inputCode == resetCode){
            Bundle bundle = getIntent().getExtras();
            String email = bundle.getString("email");
            Intent intent = new Intent(PasswordResetCodeActivity.this, ResetPasswordActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);

        } else{
            Toast.makeText(this,"The OTP you have entered is not valid",Toast.LENGTH_LONG).show();
        }
    }
}
