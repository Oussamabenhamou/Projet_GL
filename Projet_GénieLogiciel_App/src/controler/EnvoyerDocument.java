package controler;

import model.Demande;
import model.DocumentsGenerator;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;





public class EnvoyerDocument {

    private Demande demande;
    public EnvoyerDocument(Demande demande) {
        // TODO
        // generer le pdf
        this.demande = demande;
        new DocumentsGenerator(demande);

        String path = "documents\\" + demande.getType() + ".pdf";
        System.out.println(path);
        sendEmailWithAttachment(demande.getEmailEtudiant(), path);
        System.out.println("Envoyer un email");
        System.out.println("a"+ demande.getEmailEtudiant() + " " );

    }
    



    static void sendEmailWithAttachment(String EmailEtudiant, String pathDoc) {
        System.out.println("Preparing to send email with attachment...");
        
        String subject = "Test d'envoi de document PDF"; // Email subject
        String messageText = "Bonjour, Veuillez trouver ci-joint le document PDF."; // Email content

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
            messageBodyPart.setText(messageText);

            // Add the attachment part
            BodyPart attachmentBodyPart = new MimeBodyPart();
            // String filePath = "C:\\Users\\DELL\\Desktop\\java/attestationS.pdf"; 
            String filePath = pathDoc; // Specify the path to the PDF file
            DataSource source = new FileDataSource(filePath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName("document.pdf");

            // Create the multipart message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

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