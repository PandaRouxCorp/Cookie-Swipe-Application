/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Domain;
import model.User;

/**
 *
 * @author 626
 */
public class DAODomaine {
    
	/**
	 * insert en base de donnée le nouveau domaine
	 * @param domain
	 * @return
	 */
    public static boolean createDomain(Domain domain){
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request =  "INSERT INTO domain(name, adresse, popaddr, smptaddr, portin, portout) "
                		+ "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, domain.getName());
            statementInstance.setString(2, domain.getAddress());
            statementInstance.setString(3, domain.getServerIn());
            statementInstance.setString(4, domain.getServerOut());
            statementInstance.setString(5, domain.getPortIn());
            statementInstance.setString(6, domain.getPortOut());
            
            int statut = statementInstance.executeUpdate(); 

            if(statut == 1)
                return true;
        } catch ( SQLException e ) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
        } finally {
            if ( statementInstance != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
            if ( connectionInstance != null ) {
                try {
                    /* Et enfin on ferme la connexion */
                    connectionInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return false;
    }
    

    public static boolean updateDomain(Domain domain) {
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "UPDATE domain "
        		+ "SET name = ?, "
        		+ "popaddr = ?, "
        		+ "smtpaddr = ?, "
        		+ "port = ? "
        		+ "WHERE id = ?";
        try {
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, domain.getName());
            statementInstance.setString(2, domain.getServerIn());
            statementInstance.setString(3, domain.getServerOut());
            statementInstance.setString(4, domain.getPortIn());
            statementInstance.setInt(5, domain.getId());
            
            statementInstance.executeUpdate(); 
            
        } catch ( SQLException e ) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
        } finally {
            if ( statementInstance != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
            if ( connectionInstance != null ) {
                try {
                    /* Et enfin on ferme la connexion */
                    connectionInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return false;
    }
}
