package controler;
import java.awt.Toolkit;
import model.DataBase;
import view.MainFrame;
import view.TableauDeBord.*;
import view.EspaceDeTraitement.*;
import view.EspaceDeHistorique.*;
import view.EspaceDeReclamation.*;

public class MainControler {
    private DataBase bd;
    private DemandeManager demandeManager;
    private HistoriqueManager historiqueManager;
    private ReclamationManager reclamationManager;
    
    private MainFrame mainFrame;
    
    private TableauDeBord tableauDeBord;
    private EspaceDeTraitement espaceDeTraitement;
    private EspaceDeHistorique espaceDeHistorique;
    private EspaceDeReclamation espaceDeReclamation;

    public  MainControler(String username) {
        bd = new DataBase();
        demandeManager = new DemandeManager(bd);
        historiqueManager = new HistoriqueManager(bd);
        tableauDeBord = new TableauDeBord(mainFrame);
        reclamationManager = new ReclamationManager(bd);
        espaceDeTraitement = demandeManager.getEspaceDeTraitement();
        espaceDeHistorique = historiqueManager.getEspaceDeHistorique();
        espaceDeReclamation = reclamationManager.getEspaceDeReclamation();
        mainFrame = new MainFrame( tableauDeBord,  espaceDeTraitement,  espaceDeHistorique,  espaceDeReclamation);
        mainFrame.setUsername(username);
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(Authentification.class.getResource("Icon.png")));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        
    }

    // public static void main(String[] args) {
    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //             public void run() {
    //                 MainControler mainControler = new MainControler();
    //             }
    //         }
    //     );
    // }
}
