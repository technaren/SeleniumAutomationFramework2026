package demo;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import utils.Log;

public class EmailSender {
    final String senderEmail = "narenthiraa@gmail.com";
    final String appPassword = "blgzwbsdqtadghnf";
    final String recipientEmail = "narenthiraa@gmail.com";

    Properties prop = new Properties();

    {
        // SMTP Server Properties
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", "587");
    }

    Session session;

    {
        session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });
        session.setDebug(true);
    }

    public void sendEmail() {
        try {
            // Create Email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Test Email From QA Automation");

            // Email Body Part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hello \n\n This is a test email from Java \n\n Regards,\nQA Team");

            // Attachment Part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            String filePath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            String filePath2 = System.getProperty("user.dir") + "/selenium-automation/reports/" + "ExtentReport.html";
            System.out.println("Attachment path is - " + filePath2);
            Log.info("Attachment path is - " + filePath);
            attachmentPart.attachFile(new File(filePath2));

            // Combine body and attachment parts
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            // Send Email
            Transport.send(message);
            System.out.println("Email Sent Successfully ***");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EmailSender().sendEmail();
    }
}
