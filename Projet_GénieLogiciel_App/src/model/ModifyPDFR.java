/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class ModifyPDFR extends Document{
    public static void main(String[] args) {
        ConnexionBasedonne dbConnection = new ConnexionBasedonne();

    }

    public static void modifyAttestationPDF(ConnexionBasedonne dbConnection, Demande demande) {
        int studentId = demande.getIdEtudiant();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnection.obtenirconnexion();

            String query = "SELECT Nom, Prenom, CIN, Numero_Apogee, Date_naissance FROM etudiants WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String fullName = resultSet.getString("Prenom");
                String lastName = resultSet.getString("Nom");
                String cin = resultSet.getString("CIN");
                String codeApogee = resultSet.getString("Numero_Apogee");
                String dateNaissance = resultSet.getString("Date_naissance");

                PdfReader pdfReader = new PdfReader("Attestation_R.pdf");
                PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("documents\\"+demande.getType()+".pdf"));
                BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

                int pages = pdfReader.getNumberOfPages();

                for (int i = 1; i <= pages; i++) {
                    PdfContentByte pageContentByte = pdfStamper.getOverContent(i);
                    pageContentByte.setFontAndSize(baseFont, 14);

                    pageContentByte.beginText();
                    pageContentByte.setTextMatrix(145, 572);
                    pageContentByte.showText(lastName);
                    pageContentByte.endText();

                    pageContentByte.beginText();
                    pageContentByte.setTextMatrix(200, 572);
                    pageContentByte.showText(fullName);
                    pageContentByte.endText();

                    pageContentByte.beginText();
                    pageContentByte.setTextMatrix(195, 542);
                    pageContentByte.showText(cin);
                    pageContentByte.endText();

                    pageContentByte.beginText();
                    pageContentByte.setTextMatrix(100, 477);
                    pageContentByte.showText(dateNaissance);
                    pageContentByte.endText();

                    pageContentByte.beginText();
                    pageContentByte.setTextMatrix(185, 513);
                    pageContentByte.showText(codeApogee);
                    pageContentByte.endText();
                }

                pdfStamper.close();
                System.out.println("PDF modified successfully.");
            } else {
                System.out.println("No data found for ID = " + studentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendEmailWithAttachment(int studentId) {
        ConnexionBasedonne dbConnection = new ConnexionBasedonne();
        Connection connection = dbConnection.obtenirconnexion();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT email FROM etudiants WHERE ID = ?";
        String to = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(studentId, 1);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                to = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Add the text message part
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageText);

            // Add the attachment part
            BodyPart attachmentBodyPart = new MimeBodyPart();
            String filePath = "attestation_R.pdf"; // Specify the path to the PDF file
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
