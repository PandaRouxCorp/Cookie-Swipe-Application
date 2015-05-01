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

public class BDDConnect {

    private static Connection connexion;
    private static final String[] conf = new String[4];

    /**
     * permet la connection a la base de donnée
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() {
        if (connexion == null) {
            if (conf[1] == null || conf[2] == null) {
                conf[0] = "jdbc:mysql://";
                conf[1] = "";
                conf[2] = "";
                conf[3] = "";
                try {
                    FileReader fichierLecture = new FileReader("conf\\confDatabase");
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

        /*
         System.out.println("MySQL JDBC Driver Registered!");
        
         @SuppressWarnings("UnusedAssignment")
         Connection connection = null;

         try {
         connection = DriverManager
         .getConnection("jdbc:mysql://93.26.228.33:3308/cookieswipe","CS", "TOTO");
 
         } catch (SQLException e) {
         System.out.println("Connection Failed! Check output console");
         return null;
         }

         if (connection != null) {
         System.out.println("You made it, take control your database now!");
         } else {
         System.out.println("Failed to make connection!");
         }
        
         return connection;*/
    }

}
