/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Encryption;
import model.User;

/**
 * Classe statique faisant office de passerelle entre les objet User 
 * du modèle et la persistance
 * @author Mary
 */
public class DAOUser {
    
    /**
     * Crée l'utilsiateur en persitance puis le connecte
     * @param user utilsiateur à créer
     * @return si la création de l'utilsiateur a réussi
     */
    
    public static boolean createUser(User user){
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String encryptedPassword = null;
        String request = "INSERT INTO users(login, password, backupadr) VALUES (?, ?, ?);"; 
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(user.getPassword());
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, user.getLoginAdressMail());
            statementInstance.setString(2, encryptedPassword);
            statementInstance.setString(3, user.getBackupMail());
            
            int statut = statementInstance.executeUpdate();
            
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
    
    public static boolean updateUser(User user) {

        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String encryptedPassword = null;
        String request = "UPDATE user "
        		+ "SET login = ?, "
        		+ "password = ?, "
        		+ "backupAdr = ?, "
        		+ "WHERE id = ?;";
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(user.getPassword());
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.prepareStatement(request);

            statementInstance.setString(1, user.getLoginAdressMail());
            statementInstance.setString(2, encryptedPassword);
            statementInstance.setString(3, user.getBackupMail());
            statementInstance.setInt(4, user.getId());
            
            int statut = statementInstance.executeUpdate();
            
            return (statut == 1);
            
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
    
    
     /**
     * Connecte l'utilisateur si il existe en persistance
     * @param user utilisateur qui tente de se connecter
     *@return si la conenction de l'utilisateur a réussi
     */
    public static boolean connectUser(User user){
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String encryptedPassword = null;
        String request = "SELECT count(*), id FROM users where login = ? and password = ?;";
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(user.getPassword());
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.prepareStatement(request);

            statementInstance.setString(1, user.getLoginAdressMail());
            statementInstance.setString(2, encryptedPassword);
            
            ResultSet result = statementInstance.executeQuery();
            result.next();
            
            if(result.getInt(1) == 1) {
                user.setId(result.getInt(2));
                return true;
            }                
            
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
