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




import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class ModifyPDFC extends Document {
        

    public static void main(String[] args) {
                ConnexionBasedonne dbConnection = new ConnexionBasedonne();


    }
    
    public static void modifyInternshipPDF(ConnexionBasedonne dbConnection, Demande demande) {
        int studentId = demande.getIdEtudiant();
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Create a database connection using your ConnexionBasedonne class.
            connection = dbConnection.obtenirconnexion();

            // Create a prepared statement to fetch data from the database.
            String query = "SELECT * FROM internship WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Fetch data from the result set.
                String nameSociete = resultSet.getString("Name_societe");
                String email = resultSet.getString("email");
                String numberPhone = resultSet.getString("number_phone");
                String address = resultSet.getString("address");
                String fullName = resultSet.getString("Full_name");
                String encadrantFullName = resultSet.getString("encadrant_fullname");
                String nameEta = resultSet.getString("name_eta");
                String begDate = resultSet.getString("beg_date");
                String finalDate = resultSet.getString("final_date");
                String themeName = resultSet.getString("theme_name");

                // Create PdfReader instance.
                PdfReader pdfReader = new PdfReader("Convention.pdf");

                // Create PdfStamper instance.
                PdfStamper pdfStamper = new PdfStamper(pdfReader,
                        new FileOutputStream("documents\\"+demande.getType()+".pdf"));

                // Create BaseFont instance.
                BaseFont baseFont = BaseFont.createFont(
                        BaseFont.TIMES_ROMAN,
                        BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

                // Get the number of pages in pdf.
                int pages = pdfReader.getNumberOfPages();

                // Iterate through pages.
                for (int i = 1; i <= pages; i++) {
                    // Contain the pdf data.
                    PdfContentByte pageContentByte = pdfStamper.getOverContent(i);

                    // Set text font and size.
                    pageContentByte.setFontAndSize(baseFont, 12);

                    if (i == 1) {
                        // Write fetched data to the first page.
                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(105, 537);
                        pageContentByte.showText(nameSociete);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(80, 429);
                        pageContentByte.showText(email);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(70, 465);
                        pageContentByte.showText(numberPhone);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(100, 501);
                        pageContentByte.showText(address);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(200, 393);
                        pageContentByte.showText(fullName);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(105, 63);
                        pageContentByte.showText(encadrantFullName);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(290, 43);
                        pageContentByte.showText(nameEta);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(150, 286);
                        pageContentByte.showText(begDate);
                        pageContentByte.endText();

                        pageContentByte.beginText();
                        pageContentByte.setTextMatrix(252, 286);
                        pageContentByte.showText(finalDate);
                        pageContentByte.endText();
                    } else if (i == 2) {
                        // Write fetched data to the second page.
                        PdfContentByte pageContentBytes = pdfStamper.getOverContent(i);
                        pageContentBytes.setFontAndSize(baseFont, 12);

                        pageContentBytes.beginText();
                        pageContentBytes.setTextMatrix(153, 743);
                        pageContentBytes.showText(themeName);
                        pageContentBytes.endText();
                    }
                }

                // Close the PdfStamper.
                pdfStamper.close();

                System.out.println("PDF modified successfully.");
            } else {
                System.out.println("No data found for ID = 1");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close database resources.
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
            String filePath = "C:\\Users\\DELL\\Desktop\\java/Cfioutaal.pdf"; // Specify the path to the PDF file
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