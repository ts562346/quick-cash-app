//Code adapted from https://www.tutorialspoint.com/how-to-send-email-on-android-using-javamail-api

package com.example.quickcashgroup5.EmailManagement;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.quickcashgroup5.Secret.Config;
import com.example.quickcashgroup5.PasswordManagement.PasswordResetCodeActivity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class to send OTP to the user
 */
public class SendOTP extends AsyncTask<Void, Void, Void> {
    private final Context context;
    private Session session;
    private final String email;
    private final String subject;
    private final String message;
    private ProgressDialog progressDialog;

    /**
     * Constructor to set the variables
     *
     * @param context
     * @param email
     * @param subject
     * @param message
     */
    public SendOTP(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    /**
     * Method that runs before the class is executed
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Sending OTP", "Please wait...", false, false);
    }

    /**
     * Method that runs after the class is executed
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context, "OTP Sent", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, PasswordResetCodeActivity.class);
        intent.putExtra("email", email);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    /**
     * Method that runs in the background
     *
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
            }
        });
        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(Config.EMAIL));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
            Toast.makeText(context, "Sorry we ran into an error please try again later.", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
