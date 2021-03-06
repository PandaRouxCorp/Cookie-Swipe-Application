/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BDDConnect;
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
        
        BDDConnect bddInstance = null;
        Connection connectionInstance = null;
        Statement statementInstance = null;
        String encryptedPassword = null;
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(user.getPassword());
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            bddInstance = new BDDConnect();
            
            try {
                connectionInstance =   bddInstance.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.createStatement();
            
            int statut = statementInstance.executeUpdate( "INSERT INTO users(login, password, backupadr) VALUES ('"+
                    user.getLoginAdressMail()+"','"+encryptedPassword+"','"+user.getBackupMail()+"';" );
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
    
     /**
     * Connecte l'utilisateur si il existe en persistance
     * @param user utilisateur qui tente de se connecter
     *@return si la conenction de l'utilisateur a réussi
     */
    public static boolean connectUser(User user){
        BDDConnect bddInstance = null;
        Connection connectionInstance = null;
        Statement statementInstance = null;
        String encryptedPassword = null;
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(user.getPassword());
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            bddInstance = new BDDConnect();
            
            try {
                connectionInstance =   bddInstance.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.createStatement();
            
            ResultSet result = statementInstance.executeQuery( "SELECT count(*), id FROM users where login ='"+
                    user.getLoginAdressMail()+"' and password = '"+encryptedPassword+"';" );
            result.next();
            int rowCount = result.getInt(1);
            if(rowCount == 1)
            {
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
