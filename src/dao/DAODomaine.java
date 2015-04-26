/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BDDConnect;
import model.Domain;

/**
 *
 * @author 626
 */
public class DAODomaine {
    
    public static boolean createDomain(Domain domain){
        BDDConnect bddInstance = null;
        Connection connectionInstance = null;
        Statement statementInstance = null;
        
        try {
            
            bddInstance = new BDDConnect();            
            try {
                connectionInstance =   bddInstance.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.createStatement();
            
            int statut = statementInstance.executeUpdate( "INSERT INTO domain(  name," +
                                                                                "popaddr," +
                                                                                "smptaddr," +
                                                                                "port) VALUES ('"+
            domain.getDomain()+"','"+domain.getPopAddress()+"','"+domain.getSmtpAddress()+"','"+domain.getPort()+"';'");
                    
            if(statut == 1)
                return true;
            
        } catch ( SQLException e ) {
            /* Traiter les erreurs éventuelles ici. */
        } finally {
            if ( statementInstance != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connectionInstance != null ) {
                try {
                    /* Et enfin on ferme la connexion */
                    connectionInstance.close();
                } catch ( SQLException ignore ) {
                }
            }
        }  
        return false;
    }
}
