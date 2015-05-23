package dao;

/**
 *
 * @author 626
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe static permetant à une base de donnée MySql
 * @author Stitch & Mary
 */
public class BDDConnect {

    private static Connection connexion;
    private static final String[] conf = new String[4];

    /**
     * Permet de se connecter à la base de donnée
     *
     * @return une instance de connexion
     */
    public static Connection getConnection() {
        if (connexion == null) {
            if (conf[1] == null || conf[2] == null) {
                conf[0] = "jdbc:mysql://";
                conf[1] = "";
                conf[2] = "";
                conf[3] = "";
                try {
                    FileReader fichierLecture = new FileReader("src\\conf\\confDatabase");
                    BufferedReader fichier = new BufferedReader(fichierLecture);
                    String ligne;
                    int i = 0;
                    while ((ligne = fichier.readLine()) != null) {
                        conf[i] += ligne;
                        i++;
                    }
                    fichierLecture.close();
                } catch (FileNotFoundException e1) {
                    e1.getMessage();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            String pilote = "com.mysql.jdbc.Driver";
            try {
                Class.forName(pilote);
            } catch (ClassNotFoundException e) {
                System.err.println("Where is your MySQL JDBC Driver?");
                return null;
            }
            try {
                connexion = DriverManager.getConnection(conf[0] + "/" + conf[1], conf[2], conf[3]);
            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
            return connexion;
        } else {
            return connexion;
        }
    }

}
