package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Statistics {
    private int num_scola;
    private int num_notes;
    private int num_reuss;
    private int num_stage;
    private int num_dem;
    private int numb_dem_att;
    private int numb_val;
    private int numb_ref;
    private int num_recla;
    
    public int getNum_scola() {
        return num_scola;
    }

    public int getNum_notes() {
        return num_notes;
    }

    public int getNum_reuss() {
        return num_reuss;
    }

    public int getNum_stage() {
        return num_stage;
    }

    public int getNum_dem() {
        return num_dem;
    }

    public int getNumb_dem_att() {
        return numb_dem_att;
    }

    public int getNumb_val() {
        return numb_val;
    }

    public int getNumb_ref() {
        return numb_ref;
    }

    public int getNum_recla() {
        return num_recla;
    }
  

    Connection con;

    public Statistics() {
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
         getStatistics();
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

   

    public  void getStatistics() {
        

        try {
            // Fetch statistics from database
            String countQuery = "SELECT " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Nom_Document = 'certificat de scolarité' and statut=0) AS num_scola, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Nom_Document = 'Releves de notes' and statut=0) AS num_notes, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Nom_Document = 'Attestation de réussite' AND Statut = 0) AS num_reuss, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Nom_Document = 'Stage' AND Statut = 0) AS num_stage, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants) AS num_dem, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Statut = 0) AS numb_dem_att, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Statut = 1) AS numb_val, " +
                    "(SELECT COUNT(*) FROM Demandes_Etudiants WHERE Statut = -1) AS numb_ref, " +
                    "(SELECT COUNT(*) FROM Reclamation) AS num_recla";

            try (PreparedStatement preparedStatement = con.prepareStatement(countQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    this.num_scola = resultSet.getInt("num_scola");
                    this.num_notes = resultSet.getInt("num_notes");
                    this.num_reuss = resultSet.getInt("num_reuss");
                    this.num_stage = resultSet.getInt("num_stage");
                    this.num_dem = resultSet.getInt("num_dem");
                    this.numb_dem_att = resultSet.getInt("numb_dem_att");
                    this.numb_val = resultSet.getInt("numb_val");
                    this.numb_ref = resultSet.getInt("numb_ref");
                    this.num_recla = resultSet.getInt("num_recla");
                }
            }

            // Print the statistics

        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }


}
