//https://git.cs.dal.ca/prof3130/paypal_demo/

package com.example.quickcashgroup5.paymentmanagement;
import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_LONG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quickcashgroup5.R;
import com.example.quickcashgroup5.home.EmployerHomeActivity;
import com.example.quickcashgroup5.secret.Config;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Paypal extends AppCompatActivity {

    ActivityResultLauncher activityResultLauncher;
    private static PayPalConfiguration config;
    FirebaseDatabase database;
    DatabaseReference jobs;
    TextView notice;
    Button btnPayNow;
    Button btnCancel;
    Bundle bundle;
    String key;
    String payee;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal);
        config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(Config.PAYPAL_CLIENT_ID);
        notice = findViewById(R.id.notice);
        btnPayNow = findViewById(R.id.btnPayNow);
        btnCancel = findViewById(R.id.btnCancel);

        bundle = getIntent().getExtras();
        key = bundle.getString("Key");
        payee = bundle.getString("Name");
        amount = bundle.getString("Payment");

        getSupportActionBar().hide();

        notice.setText("Are you sure you want to pay " + payee + " CAD " + amount + "?");
        initialize();

        btnPayNow.setOnClickListener(v -> processPayment());

        btnCancel.setOnClickListener(v -> {
            //Intent
            Intent intent = new Intent(Paypal.this, EmployerHomeActivity.class);
            startActivity(intent);
        });

        //initialize the database and the two references related to banner ID and email address.
        database = FirebaseDatabase.getInstance("https://quickcashgroupproject-default-rtdb.firebaseio.com/");
        jobs = database.getReference();
    }

    private void initialize() {
        // Initialize result launcher
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                PaymentConfirmation confirmation = result.getData().getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        // Getting the payment details
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        // on below line we are extracting json response and displaying it in a text view.
                        JSONObject payObj = new JSONObject(paymentDetails);
                        String payID = payObj.getJSONObject("response").getString("id");
                        Log.d(TAG, payID);
                        String state = payObj.getJSONObject("response").getString("state");
                        if(state.equals("approved")){
                            jobs.child("JobPosting").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Map<String, Object> updates = new HashMap<>();
                                    updates.put("status", "Paid");
                                    dataSnapshot.getRef().updateChildren(updates);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.d(TAG, "Database Error: " + error);
                                }
                            });
                        }
                        String update = "Your payment has been " + state;
                        Toast.makeText(Paypal.this, update, LENGTH_LONG).show();
                        //Go back
                        Intent i = i = new Intent(Paypal.this, EmployerHomeActivity.class);
                        startActivity(i);
                        Paypal.this.finish();
                    } catch (JSONException e) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
                    }
                }

            } else if (result.getResultCode() == PaymentActivity.RESULT_EXTRAS_INVALID){
                Log.d(TAG,"Launcher Result Invalid");
            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                Log.d(TAG, "Launcher Result Cancelled");
            }
        });
    }

    private void processPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"CAD","Purchase Goods",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        activityResultLauncher.launch(intent);
    }
}
