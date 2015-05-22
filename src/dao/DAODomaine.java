/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import errorMessage.CodeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Domain;

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
    public static int createDomain(Domain domain){
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request =  "INSERT INTO domain(name, adresse, popaddr, smptaddr, portin, portout) "
                		+ "VALUES (?, ?, ?, ?, ?, ?);";
        try {
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
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
                error = CodeError.SUCESS;
        } catch ( SQLException e ) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if ( statementInstance != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
//            On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /* Et enfin on ferme la connexion */
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }
        return error;
    }
    

    public static int updateDomain(Domain domain) {
        int error = 0;
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
                error = CodeError.CONNEXION_FAIL;
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
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if ( statementInstance != null ) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
//            On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /* Et enfin on ferme la connexion */
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }
        return error;
    }
}
