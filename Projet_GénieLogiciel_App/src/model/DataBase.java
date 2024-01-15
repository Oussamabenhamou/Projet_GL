/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author cli
 */
public class DataBase {
    public static final String DEMANDE_CERTIFICAT_SCOLARITE ="certificat de scolarité";
    public static final String DEMANDE_RELEVE_DE_NOTE ="Relevés de Notes";
    public static final String DEMANDE_CERTIFICAT_REUSSITE ="Attestation de réussite";
    public static final String DEMANDE_CONVENTION_DE_STAGE ="Stage";


    private ArrayList <Demande> demandes;
    private ArrayList <Reclamation> Reclamations;

    public ArrayList<Demande> fetchHistoriques(){
        demandes = new ArrayList<>();
    
        try {
            String selectQuery = "SELECT de.ID, de.Date, de.Nom_Document, de.ID_Etudiant, et.Email, et.Nom, et.Prenom, de.Statut " +
                                 "FROM Demandes_Etudiants de " +
                                 "JOIN Etudiants et ON de.ID_Etudiant = et.ID " +
                                 "WHERE de.Statut = -1 OR de.Statut = 1";
    
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String date = resultSet.getString("Date");
                        int idEtudiant = resultSet.getInt("ID_Etudiant");
                        String email = resultSet.getString("Email");
                        String nomEtudiant = resultSet.getString("Nom");
                        String prenomEtudiant = resultSet.getString("Prenom");
                        String nomDocument = resultSet.getString("Nom_Document");
                        int statut = resultSet.getInt("Statut");
    
                        Demande demande = new Demande(id, date, nomDocument, idEtudiant, email, nomEtudiant, prenomEtudiant, statut);
                        demandes.add(demande);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return demandes;
    }

    public ArrayList<Demande> fetchDemandes(String nomDocument) {
        demandes = new ArrayList<>();
    
        try {
            String selectQuery = "SELECT de.ID, de.Date, de.Nom_Document, de.ID_Etudiant, et.Email, et.Nom, et.Prenom, de.Statut " +
                                 "FROM Demandes_Etudiants de " +
                                 "JOIN Etudiants et ON de.ID_Etudiant = et.ID " +
                                 "WHERE de.Nom_Document = ?";
    
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, nomDocument);
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String date = resultSet.getString("Date");
                        int idEtudiant = resultSet.getInt("ID_Etudiant");
                        String email = resultSet.getString("Email");
                        String nomEtudiant = resultSet.getString("Nom");
                        String prenomEtudiant = resultSet.getString("Prenom");
                        int statut = resultSet.getInt("Statut");
    
                        Demande demande = new Demande(id, date, nomDocument, idEtudiant, email, nomEtudiant, prenomEtudiant, statut);
                        demandes.add(demande);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return demandes;
    }
    
    
    

    
   
    Connection con;

    public DataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            String url = "jdbc:mysql://localhost:3306/Document_Management_App";
            String username = "admin";
            String password = "password";
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    Connection obtenirconnexion() {
        return con;
    }

    void fermerconnexion() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    public void updateStatuDemAccept(Demande demande){
        try {
            String updateQuery = "UPDATE Demandes_Etudiants SET Statut = 1 WHERE ID = ?";
    
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, demande.getIdDemande());
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateStatuDemRefuse(Demande demande){
        try {
            String updateQuery = "UPDATE Demandes_Etudiants SET Statut = -1 WHERE ID = ?";
    
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, demande.getIdDemande());
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //fetchReclamations
    public ArrayList<Reclamation> fetchReclamations() {
        Reclamations = new ArrayList<>();
    
        try {
            String selectQuery = "SELECT re.ID, re.Date, re.Id_Etudiant, et.Email, et.Nom, et.Prenom, re.Message "
                    + "FROM Reclamation re "
                    + "JOIN Etudiants et ON re.Id_Etudiant = et.ID;";

    
            try (PreparedStatement preparedStatement = con.prepareStatement(selectQuery)) {
                
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt ("ID");
                        String date = resultSet.getString("Date");
                        String message = resultSet.getString("Message");
                        int idEtudiant = resultSet.getInt("ID_Etudiant");
                        String email = resultSet.getString("Email");
                        String nomEtudiant = resultSet.getString("Nom");
                        String prenomEtudiant = resultSet.getString("Prenom");

                        Reclamation reclamation = new Reclamation( id,  date,  message,   idEtudiant,  email, nomEtudiant, prenomEtudiant);
                        Reclamations.add(reclamation);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Reclamations;
    }
    
}