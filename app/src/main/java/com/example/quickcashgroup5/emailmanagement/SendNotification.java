//Code adapted from https://www.tutorialspoint.com/how-to-send-email-on-android-using-javamail-api
package com.example.quickcashgroup5.emailmanagement;

import android.os.AsyncTask;

import com.example.quickcashgroup5.secret.Config;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class that send the Notification
 */
public class SendNotification extends AsyncTask<Void, Void, Void> {
    private Session session;
    private final String email;
    private final String subject;
    private final String message;

    /**
     * Constructor to set variables
     *
     * @param email
     * @param subject
     * @param message
     */
    public SendNotification(String email, String subject, String message) {
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
        System.out.println("Notification for" + email + " in progress");
    }

    /**
     * Method that runs after the class is executed
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println(email + "has been notified.");
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
            System.out.println("Notification not send");
        }
        return null;
    }
}
