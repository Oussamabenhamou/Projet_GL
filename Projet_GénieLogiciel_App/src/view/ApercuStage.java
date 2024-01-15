package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApercuStage extends javax.swing.JDialog {
    private int ID;
    /**
     * Creates new form ApercuStage
     */
    public ApercuStage(int ID) {
        this.ID = ID;
        initComponents();
        displayInternshipInformation();
        displayotherinf();
        displaydateinf();
    }

    private void displayInternshipInformation() {
        // Replace these values with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/Document_Management_App";
        String username = "admin";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM Internship WHERE id_dem = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Replace 1 with the actual ID of the student
                statement.setInt(1, ID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    StringBuilder resultText = new StringBuilder();

                    while (resultSet.next()) {
                        resultText.append("Name_Societe: ").append(resultSet.getString("Name_Societe")).append("\n");
                        resultText.append("Email: ").append(resultSet.getString("Email")).append("\n");
                        resultText.append("Number_Phone: ").append(resultSet.getString("Number_Phone")).append("\n");
                        resultText.append("Address: ").append(resultSet.getString("Address")).append("\n");
                        resultText.append("Full_Name: ").append(resultSet.getString("Full_Name")).append("\n");
                        resultText.append("Encadrant_Fullname: ").append(resultSet.getString("Encadrant_Fullname")).append("\n");
                        resultText.append("Name_Eta: ").append(resultSet.getString("Name_Eta")).append("\n");
                        resultText.append("Beg_Date: ").append(resultSet.getDate("Beg_Date")).append("\n");
                        resultText.append("Final_Date: ").append(resultSet.getDate("Final_Date")).append("\n");
                        resultText.append("Theme_Name: ").append(resultSet.getString("Theme_Name")).append("\n");

                        resultText.append("\n"); // Add a line break between entries
                    }

                    jTextArea1.setText(resultText.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }

    private void displayotherinf() {
        // Replace these values with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/Document_Management_App";
        String username = "admin";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT nom, prenom FROM Etudiants WHERE ID = (SELECT ID_Etudiant FROM demandes_etudiants WHERE ID = ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Replace 1 with the actual ID of the student
                statement.setInt(1, ID);

                try (ResultSet resultSet = statement.executeQuery()) {
                    StringBuilder resultText = new StringBuilder();
                    while (resultSet.next()) {
                        resultText.append("Etudiant: ").append(resultSet.getString("nom")).append(" ");
                        resultText.append(resultSet.getString("prenom")).append("\n");
                    }

                    jLabel2.setText(resultText.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }

    private void displaydateinf() {
        // Replace these values with your actual database connection details
        String url = "jdbc:mysql://localhost:3306/Document_Management_App";
        String username = "admin";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT Date FROM demandes_etudiants WHERE ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Replace 1 with the actual ID of the student
                statement.setInt(1, ID);

                try (ResultSet resultSet = statement.executeQuery()) {
                    StringBuilder resultText = new StringBuilder();

                    while (resultSet.next()) {
                        resultText.append("Date: ").append(resultSet.getString("Date")).append("\n");
                    }

                    jLabel3.setText(resultText.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
        }
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        jButton1.setBackground(new java.awt.Color(255, 255, 153));
        jButton1.setText("OK");
        jButton1.setAutoscrolls(true);
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Aperçu de demande de stage");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setText("Date : ");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel2.setText("Etudiant :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFont(new java.awt.Font("Sitka Text", java.awt.Font.PLAIN, 14)); // Set the font size
        jTextArea1.setEditable(false);
        jScrollPane2.setViewportView(jTextArea1);


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(141, 141, 141))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(104, 104, 104))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        this.dispose();
    }

    // public static void main(String args[]) {
    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             new ApercuStage().setVisible(true);
    //         }
    //     });
    // }

    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
}
