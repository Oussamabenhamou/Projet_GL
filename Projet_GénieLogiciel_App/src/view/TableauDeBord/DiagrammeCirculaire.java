package view.TableauDeBord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class DiagrammeCirculaire extends JPanel {

    public JFreeChart diagramme;
    public DefaultPieDataset ensembleDonnees;

    public DiagrammeCirculaire() {
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());

        // Création de l'ensemble de données (dataset)
        ensembleDonnees = creerEnsembleDonnees();

        // Création du diagramme circulaire
        diagramme = ChartFactory.createPieChart(
                "Répartition des statuts des documents",
                ensembleDonnees,
                false, // Afficher la légende
                false,
                false
        );

        // Personnalisation du diagramme
        diagramme.setBackgroundPaint(Color.white);

        // Ajout du diagramme à un panneau Swing
        ChartPanel panelDiagramme = new ChartPanel(diagramme);
        this.add(panelDiagramme, BorderLayout.CENTER);

        this.setVisible(true);
    }

    // Méthode pour créer un ensemble de données par défaut
    private static DefaultPieDataset creerEnsembleDonnees() {
        DefaultPieDataset ensembleDonnees = new DefaultPieDataset();
        ensembleDonnees.setValue("Refusées", 35.0);
        ensembleDonnees.setValue("Non traitées", 25.0);
        ensembleDonnees.setValue("Acceptées", 40.0);
        return ensembleDonnees;
    }

    // Méthode pour mettre à jour les données du diagramme
    public void mettreAJourDonnees(String categorie, double valeur) {
        ensembleDonnees.setValue(categorie, valeur);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Diagramme Circulaire Dynamique");
//        frame.setSize(400, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new BorderLayout());
//
//        DiagrammeCirculaire diagrammeCirculaire = new DiagrammeCirculaire();
//        frame.add(diagrammeCirculaire);
//
//        frame.setVisible(true);
//
//        // Simulation de la mise à jour des données en temps réel
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("Catégorie 1 :");
//            int categorie1 = Integer.valueOf(scanner.nextLine());
//            System.out.println("Catégorie 2 :");
//            int categorie2 = Integer.valueOf(scanner.nextLine());
//            System.out.println("Catégorie 3 :");
//            int categorie3 = Integer.valueOf(scanner.nextLine());
//            diagrammeCirculaire.mettreAJourDonnees("Catégorie 1", categorie1);
//            diagrammeCirculaire.mettreAJourDonnees("Catégorie 2", categorie2);
//            diagrammeCirculaire.mettreAJourDonnees("Catégorie 3", categorie3);
//            System.out.println("Diagramme mis à jour");
//        }
//    }
}
