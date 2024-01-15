/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cli
 */
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

import java.sql.SQLException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class Resultat extends Document {
    public static void main(String[] args) {

        // downloadPDF();  // No need to call downloadPDF() if you want to exit the program when the condition is not met.
    }

    public static void generatePDFFromDatabase(Demande demande) {
            int studentId = demande.getIdEtudiant();

        
        
        Document document = new Document(PageSize.A4);

        try {
            // Spécifiez le chemin où vous souhaitez enregistrer le PDF
            PdfWriter.getInstance(document, new FileOutputStream("documents\\"+demande.getType()+".pdf"));

            document.open();

            // Création du tableau pour la mise en page
            PdfPTable layoutTable = new PdfPTable(2);
            layoutTable.setWidthPercentage(100);
            layoutTable.setSpacingBefore(10f);
            layoutTable.setSpacingAfter(10f);

            // Colonne 1 : Logo
            PdfPCell logoCell = new PdfPCell();
            Image logo = Image.getInstance(new URL("https://ensa-tetouan.ac.ma/wp-content/uploads/2023/01/PNG6-1024x383.png"));
            logo.scaleToFit(200, 200);
            logoCell.addElement(new Paragraph(""));
            logoCell.addElement(logo);
            logoCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            logoCell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
            logoCell.setBorder(PdfPCell.NO_BORDER);

            // Colonne 2 : Contenu
            PdfPCell contentCell = new PdfPCell();
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

            contentCell.addElement(new Paragraph("         Universite Abdelmalek Essaadi", boldFont));
            contentCell.addElement(new Paragraph("Ecole Nationale des Sciences Appliquees de", boldFont));

            Paragraph tetouanParagraph = new Paragraph();
            tetouanParagraph.add(new Chunk("                             ", boldFont));
            tetouanParagraph.add(new Chunk("Tetouan", boldFont));

            contentCell.addElement(tetouanParagraph);

            contentCell.addElement(new Paragraph(" "));

            contentCell.setBorder(PdfPCell.NO_BORDER);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Ajout des colonnes à la table de mise en page
            layoutTable.addCell(logoCell);
            layoutTable.addCell(contentCell);

            document.add(layoutTable);

            Font releveDesNotesFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(128, 0, 128));
            Paragraph releveDesNotes = new Paragraph("Releve des Notes", releveDesNotesFont);
            releveDesNotes.setAlignment(Element.ALIGN_CENTER);

            document.add(releveDesNotes);
            document.add(new Paragraph(" "));

            // Fetch student data from database
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                // Create a database connection using your ConnexionBasedonne class.
                ConnexionBasedonne dbConnection = new ConnexionBasedonne();
                connection = dbConnection.obtenirconnexion();

                // Create a prepared statement to fetch data from the database.
                String query = "SELECT Nom,Prenom, CIN, Numero_Apogee,filiere, Date_naissance FROM etudiants WHERE ID = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, studentId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // Fetch data from the result set.
                    String fullName = resultSet.getString("Nom");
                     String ullName = resultSet.getString("Prenom");

                    String cin = resultSet.getString("CIN");
                    String codeApogee = resultSet.getString("Numero_Apogee");
                    String dateNaissance = resultSet.getString("Date_naissance");
                    String filiere = resultSet.getString("filiere");

            

                    // Display fetched data
                    document.add(new Paragraph("Filiere: "+filiere));
                    document.add(new Paragraph("Annee Universitaire 2020/2021"));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("L'eleve Ingenieur:"));
                    document.add(new Paragraph("Nom et Prenom: " + fullName+ullName));
                    document.add(new Paragraph("CNE: " + cin));
                    document.add(new Paragraph("Code Apogee: " + codeApogee));
                    document.add(new Paragraph("Date de Naissance: " + dateNaissance));

                    // ...

                    // Fetch and display notes data from database
                    String notesQuery = "SELECT Matiere, Note, statut FROM Releves_de_Notes WHERE id_etudiant = ?";
                    preparedStatement = connection.prepareStatement(notesQuery);
                    preparedStatement.setInt(1, studentId);
                    resultSet = preparedStatement.executeQuery();

                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Tableau des notes:"));

                   // ...

                    // Création du tableau des notes
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100);
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);

                    // Ajout des en-têtes du tableau
                    PdfPCell cell1 = new PdfPCell(new Paragraph("Intitule du Module"));
                    PdfPCell cell2 = new PdfPCell(new Paragraph("Note/20"));
                    PdfPCell cell3 = new PdfPCell(new Paragraph("Resultat"));

                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);

                    double sumOfNotes = 0;

                    // Iterate through the result set using a for loop
                    for (int i = 0; i < 7; i++) {  // Assuming there are 7 modules
                        if (resultSet.next()) {
                            // Fetch data from the result set.
                            String nomModule = resultSet.getString("Matiere");
                            String noteModule = resultSet.getString("Note");
                            int status = resultSet.getInt("statut");

                            // Determine the status text based on the value of status
                            String statusText = (status == 1) ? "Valide" : "Non Validee";

                            // Ajout des lignes du tableau
                            table.addCell(nomModule);
                            table.addCell(noteModule);
                            table.addCell(statusText);

                            // Calculate sum of notes for moyenne de l'annee
                            sumOfNotes += Double.parseDouble(noteModule);
                        } else {
                            // Handle the case where there are fewer than 7 rows in the result set
                            break;
                        }
                    }

                    // Calculate moyenne de l'annee
                    double moyenne = Math.round((sumOfNotes / 7) * 100.0) / 100.0;

                    // Determine resultat de l'annee
                    String resultatAnnee = (moyenne >= 12) ? "Admis" : "Non Admis";

                    document.add(table);

                    // Ajout de la moyenne et du résultat de l'année
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Moyenne de l'annee: " + moyenne));
                    document.add(new Paragraph("Resultat de l'annee: " + resultatAnnee));
                    document.add(new Paragraph(" "));

                    // ...

                    document.add(new Paragraph(" "));

                    document.add(new Paragraph("AC: Acquis par Compensation   NV: Non Valide"));
                    document.add(new Paragraph("N.B. Le present document n'est delivre qu'en un seul exemplaire. Il appartient a l'etudiant d'en faire des photocopies certifiees conformes."));
                    document.add(new Paragraph(" "));
                } else {
                    System.out.println("No data found for ID = " + studentId);
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

            document.close();

            System.out.println("Le document PDF a ete genere avec succes.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


    }
