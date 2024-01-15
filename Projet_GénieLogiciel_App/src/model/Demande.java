/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author cli
 */

public class Demande {
    public static final int PAS_ENCOURE = 0;
    public static final int VALIDER = 1;
    public static final int REFUSER = -1;

    private int idDemande;
    private String date;
    private String type;
    
    private int idEtudiant;
    private String emailEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private int statu = PAS_ENCOURE;
    
    public Demande(int id, String date, String type, int  idEtudiant, String emailEtudiant,String nomEtudiant,String prenomEtudiant, int statu) {
        this.idDemande = id;
        this.date = date;
        this.type = type;
        this.idEtudiant = idEtudiant;
        this.emailEtudiant = emailEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.statu = statu;
    }    
    public String getTypeDemande(){
        return type;
    }
    
    public int getIdDemande() {
        return idDemande;
    }
    
    public void setStatu(int statu) {
        this.statu = statu;
    }
    public String getDate() {
        return date;
    }
    public int getStatu() {
        return statu;
    }
    public String getType() {
        return type;
    }
    public String tosearchedString() {
        return  idDemande  + date  + nomEtudiant + prenomEtudiant ;
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

