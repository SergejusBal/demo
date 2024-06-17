package com.example.Paskaita_2024_06_12_API.demo;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class GmailMail {

    private final String gmailUserName;
    private final String passwordMailFrom;
    private final String emailFrom;

    public GmailMail() {
        EmailData emailData = new EmailData();
        this.gmailUserName = emailData.getUsername();
        this.passwordMailFrom = emailData.getPassword();
        this.emailFrom = emailData.getEmail();
    }

    public String sendMail(String emailTo, String messageToBeSent){

        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        emailProperties.put("mail.smtp.starttls.required", "true");
        emailProperties.put("mail.debug", "true");

        Session session = Session.getInstance(emailProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailUserName, passwordMailFrom);
                    }
                });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field of the header
            message.setFrom(new InternetAddress(emailFrom));
            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            // Set Subject: header field
            message.setSubject("JavaMail API Test");
            // Now set the actual message
            message.setText(messageToBeSent);
            // Send message
            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "E-mail was sent successfully." ;
    }

}
