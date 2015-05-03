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
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Encryption;
import model.MailAccount;
import model.User;


/**
 * Classe statique faisant office de passerelle entre les objet MailAccount 
 * du modèle et la persistance
 * @author Mary
 */
public class DAOMailAccount {
    
    /**
     * Créé un comtpe courriel en persitance
     * @param mailAccount compte courriel à crée
     * @return si la création du mail a bien réussi
     */
    public static boolean createMailAccount(MailAccount mailAccount,User user){
        
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "INSERT INTO mailaccount( user_id, adresse, cookieswipename, domain, password, color) "
                + "VALUES (?, ?, ?, ?, ?, ? )";
        String encryptedPassword = null;
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(mailAccount.getPassword());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1,user.getId() );
            statementInstance.setString(2, mailAccount.getAddress());
            statementInstance.setString(3,mailAccount.getCSName() );
            statementInstance.setInt(4,mailAccount.getDomain().getId());
            statementInstance.setString(5, encryptedPassword);
            statementInstance.setString(6, mailAccount.getColor());
            statementInstance.execute();
            return true;
            
        } catch ( SQLException e ) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
            System.err.println(statementInstance);
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
    
    /**
     * Charge toute les données d'un compte courriel qui était sauvegardé
     * @param user
     * @param mailAccount compte courriel concerné
     * @return si le chargement du compte courriel a bien réussi
     */
    public static boolean loadMailAccount(User user){
        
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql ="SELECT count(id), id, adresse, cookieswipename, domain, password, lastsync, mailsignature, color "
            		+ "FROM mailaccount "
            		+ "WHERE user_id = ?";
        try {                       
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1, user.getId());
            
            ResultSet result = statementInstance.executeQuery( sql);		 

            boolean loadMailAccount = true;
            
            while ( result.next() ) {
            	String password = null;
                try {
                        password = new Encryption().decrypt(result.getString("password"));
                } catch (Exception e) {
                        e.printStackTrace();
                        loadMailAccount = false;
                }
            	MailAccount mailAccount = new MailAccount(result.getString("adresse"), result.getString("cookieswipename"), password, result.getString("color"));
            	mailAccount.setLastSynch(result.getDate("lastsync"));
            	mailAccount.setMailSignature(result.getString("mailsignature"));
            	mailAccount.getDomain().setId(result.getInt("domain"));
            	if(!loadDomail(mailAccount)) {
            		loadMailAccount = false;
            	}
            	//user.addNewMailAccount(mailAccount);            	
            }
            return loadMailAccount;
        } catch ( SQLException e ) {
            System.err.println(e.getMessage());
            System.err.println(statementInstance); 
        } finally {
            if ( statementInstance != null ) {
                try {
                    // Puis on ferme le Statement 
                    statementInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
            if ( connectionInstance != null ) {
                try {
                    /// Et enfin on ferme la connexion 
                    connectionInstance.close();
                } catch ( SQLException e ) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return false;
    }
    
    /**
     * Charge le domaine d'un comtpe courriel
     * @param mailAccount compte courriel concerné
     * @return si le chargement du domaine a réussi
     */
    public static boolean loadDomail(MailAccount mailAccount){
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT name, adresse, popaddr, smtpaddr, portin, portout FROM domain where id = ?;";
        try {
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setInt(1, mailAccount.getId());
            
            ResultSet result = statementInstance.executeQuery(); 

            if(result.next()){
                mailAccount.getDomain().setName(result.getString("name"));
                mailAccount.getDomain().setAddress(result.getString("adresse"));
                mailAccount.getDomain().setServerIn(result.getString("popaddr"));
                mailAccount.getDomain().setServerOut(result.getString("smtpaddr"));
                mailAccount.getDomain().setPortIn(result.getString("portin"));
                mailAccount.getDomain().setPortOut(result.getString("portout"));
                return true;
            }      
            
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
    

    public static boolean updateMailAccount(MailAccount mailaccount) {

        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String encryptedPassword = null;
        String sql =  "UPDATE mailaccount "
                    + "SET adresse = ?, cookieswipename = ?, domain = ?, password = ?, "
                    + "lastsync = ?, mailsignature = ?, color = ? WHERE id = ?;";
        try {
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            try {
                encryptedPassword = new Encryption().encrypt(mailaccount.getPassword());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setString(1, mailaccount.getAddress());
            statementInstance.setString(2, mailaccount.getCSName());
            statementInstance.setInt(3, mailaccount.getDomain().getId());
            statementInstance.setString(4, encryptedPassword );
            statementInstance.setString(5, mailaccount.getLastSynch().toString() );
            statementInstance.setString(6, mailaccount.getMailSignature() );
            statementInstance.setString(7, mailaccount.getColor());
            statementInstance.setInt(8, mailaccount.getId());
            
            statementInstance.executeUpdate(); 

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
    
    /**
     * Charge les courriels d'un compte courriel
     * @param mailAccount compte courriel concerné
     * @return si le chargement des courriels a réussi
     */
    public static boolean loadMail(MailAccount mailAccount){

        return false;
    }
}
