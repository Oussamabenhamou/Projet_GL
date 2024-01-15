
package model ;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class ModifyPDF extends Document {
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

                PdfReader pdfReader = new PdfReader("attestation_S.pdf");
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
                    pageContentByte.setTextMatrix(170, 572);
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


}