/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import model.*;
import view.*;
import view.EspaceDeTraitement.EspaceDeTraitement;

import java.util.ArrayList;


public class DemandeManager {

    private ArrayList <Demande> demandes;
    private DataBase bd;
    private EspaceDeTraitement espaceDeTraitement;

    public DemandeManager(DataBase bd) {
        this.bd = bd;
        demandes = bd.fetchDemandes(DataBase.DEMANDE_CERTIFICAT_SCOLARITE);
        espaceDeTraitement = new EspaceDeTraitement(demandes, this);
    }

    public EspaceDeTraitement getEspaceDeTraitement() {
        return espaceDeTraitement;
    }

    public void filterDemande(String typeDemande){
        demandes = bd.fetchDemandes(typeDemande);
        espaceDeTraitement.updateListDemande(bd.fetchDemandes(typeDemande));
    }

    public void rechercher(String input){
        ArrayList <Demande> demandesRecherches = new ArrayList<>();
        
        for (Demande demande : demandes) {
            if(demande.tosearchedString().contains(input)){
                demandesRecherches.add(demande);
            }
        }
        espaceDeTraitement.updateListDemande(demandesRecherches);
    }

    public void accepterDemande(Demande demande){
        // demande.setStatu(Demande.VALIDER);
        espaceDeTraitement.updateListDemande(demandes);
        bd.updateStatuDemAccept(demande);
        espaceDeTraitement.updateListDemande(bd.fetchDemandes(demande.getTypeDemande()));
        System.out.println("Demande " + demande.getIdDemande()+" accepter");

    }

    public void refuserDemande(Demande demande){
        demande.setStatu(Demande.REFUSER);
        espaceDeTraitement.updateListDemande(demandes);
        bd.updateStatuDemRefuse(demande);
        espaceDeTraitement.updateListDemande(bd.fetchDemandes(demande.getTypeDemande()));
        System.out.println("Demande "+demande.getIdDemande()+" refuser");
    }
    
    
}
