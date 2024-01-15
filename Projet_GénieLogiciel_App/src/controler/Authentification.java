package controler;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.MainFrame;
import view.EspaceDeHistorique.EspaceDeHistorique;
import view.EspaceDeReclamation.EspaceDeReclamation;
import view.EspaceDeTraitement.EspaceDeTraitement;
import view.TableauDeBord.TableauDeBord;
import model.DataBase;

import java.awt.*;
import java.sql.*;

public class Authentification {
    
    private Connection con;
    private PreparedStatement Pst;
    private ResultSet rs;

    private JFrame frmAuthentification;
    private JTextField Identifiant;
    private JPasswordField Password;
    private Authentification authen;
    private DataBase bd;

    private MainFrame mainFrame;

    private TableauDeBord tableauDeBord;
    private EspaceDeTraitement espaceDeTraitement;
    private EspaceDeHistorique espaceDeHistorique;
    private EspaceDeReclamation espaceDeReclamation;


    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Authentification window = new Authentification();
                    window.frmAuthentification.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Authentification() {
        Authentification();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void Authentification() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            String url = "jdbc:mysql://localhost:3306/Document_Management_App";
            String username = "admin";
            String password = "password";
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        frmAuthentification = new JFrame();
        frmAuthentification.setIconImage(Toolkit.getDefaultToolkit().getImage(Authentification.class.getResource("Icon.png")));

        frmAuthentification.setTitle("Service Etudiants");
        frmAuthentification.getContentPane().setBackground(new Color(255, 255, 255));
        frmAuthentification.setBounds(0, 0, 950, 650);
        frmAuthentification.setResizable(false);
        frmAuthentification.setLocationRelativeTo(null);
        frmAuthentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAuthentification.getContentPane().setLayout(null);

        Identifiant = new JTextField();
        Identifiant.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        Identifiant.setBounds(298, 314, 310, 44);
        Identifiant.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        Identifiant.setBackground(new Color(255, 255, 255));
        frmAuthentification.getContentPane().add(Identifiant);
        Identifiant.setColumns(10);

        Password = new JPasswordField();
        Password.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        Password.setBounds(298, 412, 310, 44);
        Password.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
        Password.setBackground(new Color(255, 255, 255));
        frmAuthentification.getContentPane().add(Password);

        JLabel lblIdentifiant = new JLabel("Identifiant");
        lblIdentifiant.setBounds(298, 284, 310, 44);
        lblIdentifiant.setForeground(new Color(0, 0, 0));
        lblIdentifiant.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        frmAuthentification.getContentPane().add(lblIdentifiant);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(298, 382, 310, 44);
        lblPassword.setForeground(new Color(0, 0, 0));
        lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        frmAuthentification.getContentPane().add(lblPassword);

        JLabel lblError = new JLabel("SERVICES POUR LES ETUDIANTS");
        lblError.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setBounds(223, 143, 463, 97);
        lblError.setForeground(new Color(0, 0, 0));
        frmAuthentification.getContentPane().add(lblError);

        JButton Login = new JButton("Se connecter");
        Login.setBounds(463, 518, 145, 44);
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredEmail = Identifiant.getText();
                char[] enteredPasswordChars = Password.getPassword();
                String enteredPassword = new String(enteredPasswordChars);
            
                String emailFromDatabase = null;
                String passwordFromDatabase = null;
            
                try {
                    Pst = con.prepareStatement("SELECT Email, Mot_De_Passe FROM Administrateurs WHERE id = 1");
                    ResultSet rs = Pst.executeQuery();
                    if (rs.next()) {
                        emailFromDatabase = rs.getString("Email");
                        passwordFromDatabase = rs.getString("Mot_De_Passe");
                        System.out.println(emailFromDatabase + " " + passwordFromDatabase);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (emailFromDatabase != null && passwordFromDatabase != null
                && emailFromDatabase.equals(enteredEmail) && passwordFromDatabase.equals(enteredPassword)) {
            // Authentication successful
            JOptionPane.showMessageDialog(null, "Login successful!");
            
            new MainControler(Identifiant.getText().split("@")[0]);
            frmAuthentification.dispose();
            // Additional logic if needed
        }
        
 else if (emailFromDatabase != null && passwordFromDatabase != null
                        && !emailFromDatabase.equals(enteredEmail) && passwordFromDatabase.equals(enteredPassword)) {
                    JOptionPane.showMessageDialog(null, "L'identifiant est incorrect!");

                    Identifiant.setText("");
                    Password.setText("");
                } else if (emailFromDatabase != null && passwordFromDatabase != null
                        && emailFromDatabase.equals(enteredEmail) && !passwordFromDatabase.equals(enteredPassword)) {
                    JOptionPane.showMessageDialog(null, "Mot de passe incorrect!");

                    Identifiant.setText("");
                    Password.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Les données sont incorrectes!");

                    Identifiant.setText("");
                    Password.setText("");
                }
            }
        });

        Login.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
        Login.setForeground(new Color(255, 255, 255));
        Login.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        Login.setBackground(new Color(30, 144, 255));
        Login.setFocusable(false);
        frmAuthentification.getContentPane().add(Login);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(Authentification.class.getResource("ensate.png")));
        lblNewLabel.setBounds(0, 0, 884, 120);
        frmAuthentification.getContentPane().add(lblNewLabel);
    }
}
