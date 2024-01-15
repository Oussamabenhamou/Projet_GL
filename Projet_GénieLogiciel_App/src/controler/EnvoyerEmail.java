package controler;

import model.Demande;
import model.Reclamation;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;



public class EnvoyerEmail {

    public EnvoyerEmail(Demande demande, String msg) {
        // generer le pdf
       
        sendEmail(demande.getEmailEtudiant(), msg);
        System.out.println("Envoyer un email");
        System.out.println("a"+ demande.getEmailEtudiant() + " " );
        System.out.println("avec le msg : " + msg);
    }
    
    public EnvoyerEmail(Reclamation reclamation, String msg) {
        sendEmail(reclamation.getEmailEtudiant(), msg);
        System.out.println("Envoyer un email");
        System.out.println("a"+ reclamation.getEmailEtudiant() + " " );
        System.out.println("avec le msg : " + msg);
    }


    static void sendEmail(String EmailEtudiant, String msg) {
        System.out.println("Preparing to send email with attachment...");
        
        String subject = "Test d'envoi de document PDF"; // Email subject
        String msgText = "Bonjour, la raison de refus est "+ msg; // Email content

        final String username = "Projet.GL2023@gmail.com"; // Your email address
        final String password = "awfa bymr yvbv akhy"; // Your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP server (e.g., Gmail)
        props.put("mail.smtp.port", "587"); // SMTP port (587 is usually used for TLS)
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            System.out.println("Send email to " + EmailEtudiant);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            // message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailEtudiant));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailEtudiant));
            message.setSubject(subject);

            // Add the text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msgText);

            // Create the multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            // multipart.addBodyPart(attachmentBodyPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("E-mail sent successfully with attachment!");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}