/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;
import model.*;
import view.*;
import view.EspaceDeReclamation.EspaceDeReclamation;


import java.util.ArrayList;


public class ReclamationManager {

    private ArrayList <Reclamation> Reclamations;
    private DataBase bd;
    private EspaceDeReclamation EspaceDeReclamation;

    public ReclamationManager(DataBase bd) {
        this.bd = bd;
        Reclamations = bd.fetchReclamations();
        EspaceDeReclamation = new EspaceDeReclamation(Reclamations, this);
    }

    public EspaceDeReclamation getEspaceDeReclamation() {
        return EspaceDeReclamation;
    }

    public void filterReclamation(){
        Reclamations = bd.fetchReclamations();
        EspaceDeReclamation.updateListReclamation(bd.fetchReclamations());
    }

    public void rechercher(String input){
        ArrayList <Reclamation> ReclamationsRecherches = new ArrayList<>();
        
        for (Reclamation Reclamation : Reclamations) {
            if(Reclamation.tosearchedString().contains(input)){
                ReclamationsRecherches.add(Reclamation);
            }
        }
        EspaceDeReclamation.updateListReclamation(ReclamationsRecherches);
    }

    // public void telechargerReclamation(Reclamation Reclamation){
    //     // Reclamation.setStatu(Reclamation.VALIDER);
    //     EspaceDeReclamation.updateListReclamation(Reclamations);
    //     bd.updateStatuDemAccept(Reclamation);
    //     EspaceDeReclamation.updateListReclamation(bd.fetchReclamations());
    //     new TelechargerDocument(Reclamation);
    //     // System.out.println("Reclamation " + Reclamation.getIdReclamation()+" telecharger");

    // }

    public void RenvoyerReclamation(Reclamation Reclamation){
               
        EspaceDeReclamation.updateListReclamation(bd.fetchReclamations());
        // System.out.println("Reclamation "+Reclamation.getIdReclamation()+" Renvoyer");
    }
  
}
