package model ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBasedonne {

    Connection con;

    public ConnexionBasedonne() {
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
}
