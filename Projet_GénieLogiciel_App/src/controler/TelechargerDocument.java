package controler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import model.Demande;
import model.DocumentsGenerator;

public class TelechargerDocument {
    private Demande demande;
    public TelechargerDocument(Demande demande) {
        // TODO
        this.demande = demande;
        new DocumentsGenerator(demande);
        downloadPDF();
        System.out.println("Telecharger le document de la demande " + demande.getIdDemande());

    }



    public void downloadPDF() {
        String docName = demande.getType();

        // Add .pdf extension if not provided
        if (!docName.endsWith(".pdf")) {
            docName += ".pdf";
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                Path source = Paths.get("documents\\"+docName);
                Path destination = Paths.get(fileChooser.getSelectedFile().getPath(), docName);
                System.out.println("source : " + source);
                System.out.println("destination : " + destination);
                
                // Move the file to the specified destination
                Files.move(source, destination);
                System.out.println("PDF file has been renamed and moved successfully to: " + destination.toString());
            } catch (Exception e) {
                System.out.println("An error occurred while renaming/moving the PDF file: " + e.getMessage());
            }
        } else {
            System.out.println("Operation canceled by the user.");
        }
    }

}

/*

import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    private static JTextField fileNameField;

    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("PDF Downloader");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager to FlowLayout
        frame.setLayout(new FlowLayout());

        // Create a new JTextField
        fileNameField = new JTextField(20);
        frame.getContentPane().add(fileNameField);

        // Create a new JButton
        JButton button = new JButton("Telecharger");
        frame.getContentPane().add(button);

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate the PDF
                generatePDF("Student Name", "CNE", "Apogee Code", "Date of Issuance");

                // Download the PDF
                downloadPDF();

                // Close the JFrame
                frame.dispose();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void generatePDF(String nomEtudiant, String cne, String codeApogee, String dateDelivrance) {
        Document document = new Document(PageSize.A4);

        try {
            // Spécifiez le chemin où vous souhaitez enregistrer le PDF
            PdfWriter.getInstance(document, new FileOutputStream("Releve_De_Notes_Avec_Logo_Droite222.pdf"));

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
            contentCell.addElement(new Paragraph("Universite Abdelmalek Essaadi"));
            contentCell.addElement(new Paragraph("Ecole Nationale des Sciences Appliquees de Tetouan"));
            contentCell.addElement(new Paragraph(" "));

            contentCell.setBorder(PdfPCell.NO_BORDER);
            contentCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Ajout des colonnes à la table de mise en page

            layoutTable.addCell(logoCell);
            layoutTable.addCell(contentCell);

            document.add(layoutTable);
            document.add(new Paragraph("Releve des Notes"));
            document.add(new Paragraph("Filiere: Premiere annee genie informatique"));
            document.add(new Paragraph("Annee Universitaire 2020/2021"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("L'eleve Ingenieur:"));
            document.add(new Paragraph("Nom et Prenom: " + nomEtudiant));
            document.add(new Paragraph("CNE: " + cne));
            document.add(new Paragraph("Code Apogee: " + codeApogee));
            document.add(new Paragraph("A obtenu les resultats suivants pour la deuxieme de la filiere Genie Informatique"));
            document.add(new Paragraph("La presente attestation est delivree a l'interesse(e) pour servir et valoir ce que de droit."));
            document.add(new Paragraph("Fait a Tetouan, le: " + dateDelivrance));
            document.add(new Paragraph("AC: Acquis par Compensation   NV: Non Valide"));
            document.add(new Paragraph("N.B. Le present document n'est delivre qu'en un seul exemplaire. Il appartient a l'etudiant d'en faire des photocopies certifiees conformes."));
            document.add(new Paragraph(" "));
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

            // Ajout des lignes du tableau
            String[][] notes = {
                    {"Theorie de langages et compilation", "16.5", "Valide"},
                    {"Programmation web1", "15.0", "Valide"},
                    {"Programmation C", "13.48", "Valide"},
                    {"Architecture d'ordinateurs et assembleur", "15.75", "Valide"},
                    {"Electronique numerique", "17.22", "Valide"},
                    {"Proba, Stat et stoch", "16.25", "Valide"},
                    {"B.D.Relationnelles", "15.6", "Valide"},
                    {"Reseaux Informatiques1", "16.4", "Valide"},
                    {"Recherche operationnel et theorie des graphes", "19.0", "Valide"},
                    {"Management1", "16.0", "Valide"},
                    {"Outils Ling. et Tech", "15.85", "Valide"},
            };

            for (String[] row : notes) {
                table.addCell(row[0]);
                table.addCell(row[1]);
                table.addCell(row[2]);
            }

            document.add(table);

            // Ajout de la moyenne et du résultat de l'année
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Moyenne de l'annee: 14.75"));
            document.add(new Paragraph("Resultat de l'annee: Admis"));

            document.close();

            System.out.println("Le document PDF a ete genere avec succes.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void downloadPDF() {
        String newName = fileNameField.getText();

        // Add .pdf extension if not provided
        if (!newName.endsWith(".pdf")) {
            newName += ".pdf";
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                Path source = Paths.get("Releve_De_Notes_Avec_Logo_Droite222.pdf");
                Path destination = Paths.get(fileChooser.getSelectedFile().getPath(), newName);

                // Move the file to the specified destination
                Files.move(source, destination);
                System.out.println("PDF file has been renamed and moved successfully to: " + destination.toString());
            } catch (Exception e) {
                System.out.println("An error occurred while renaming/moving the PDF file: " + e.getMessage());
            }
        } else {
            System.out.println("Operation canceled by the user.");
        }
    }

    // ... (other code)
} */