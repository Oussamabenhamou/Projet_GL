/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cli
 */

public class Reclamation {


    private int idReclamation;
    private String date;
    private String message;
    
    private int idEtudiant;
    private String emailEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    
    
    public Reclamation(int id, String date, String message, int  idEtudiant, String emailEtudiant,String nomEtudiant,String prenomEtudiant) {
        this.idReclamation = id;
        this.date = date;
        this.message = message;
        this.idEtudiant = idEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        
    }    
    public String getmessageReclamation(){
        return message;
    }
    
    public int getIdReclamation() {
        return idReclamation;
    }
    
   
    public String getDate() {
        return date;
    }
   
    public String getmessage() {
        return message;
    }
    public String tosearchedString() {
        return  idReclamation  + date  + nomEtudiant + prenomEtudiant ;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public String getEmailEtudiant() {
        return emailEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }
}

