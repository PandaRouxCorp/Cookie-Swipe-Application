
package model;

/**
 *
 * @author 626
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BDDConnect {

    public BDDConnect(){
    }
  
    public Connection getConnection() throws Exception{
        
        String pilote = "com.mysql.jdbc.Driver";
        try {
            Class.forName(pilote);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            return null;
        } 
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
        
        return connection;
    }
  
  
}
