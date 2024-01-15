/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import model.*;
import view.*;
import view.EspaceDeHistorique.EspaceDeHistorique;


import java.util.ArrayList;


public class HistoriqueManager {

    private ArrayList <Demande> Historiques;
    private DataBase bd;
    private EspaceDeHistorique EspaceDeHistorique;

    public HistoriqueManager(DataBase bd) {
        this.bd = bd;
        Historiques = bd.fetchHistoriques();
        EspaceDeHistorique = new EspaceDeHistorique(Historiques, this);
    }

    public EspaceDeHistorique getEspaceDeHistorique() {
        return EspaceDeHistorique;
    }

    public void filterHistorique(){
        Historiques = bd.fetchHistoriques();
        EspaceDeHistorique.updateListHistorique(bd.fetchHistoriques());
    }

    public void rechercher(String input){
        ArrayList <Demande> HistoriquesRecherches = new ArrayList<>();
        
        for (Demande Historique : Historiques) {
            if(Historique.tosearchedString().contains(input)){
                HistoriquesRecherches.add(Historique);
            }
        }
        EspaceDeHistorique.updateListHistorique(HistoriquesRecherches);
    }

    // public void telechargerHistorique(Demande Historique){
    //     // Historique.setStatu(Historique.VALIDER);
    //     EspaceDeHistorique.updateListHistorique(Historiques);
    //     bd.updateStatuDemAccept(Historique);
    //     EspaceDeHistorique.updateListHistorique(bd.fetchHistoriques());
    //     new TelechargerDocument(Historique);
    //     // System.out.println("Historique " + Historique.getIdDemande()+" telecharger");

    // }

    public void RenvoyerHistorique(Demande Historique){
        EspaceDeHistorique.updateListHistorique(Historiques);
        bd.updateStatuDemAccept(Historique);
        EspaceDeHistorique.updateListHistorique(bd.fetchHistoriques());
        // System.out.println("Historique "+Historique.getIdDemande()+" Renvoyer");
    }
  
}
