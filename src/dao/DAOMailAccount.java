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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println(ex.getMessage());

            }
            
            try {
                connectionInstance =   BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
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
     * @param mailAccount compte courriel concerné
     * @return si le chargement du compte courriel a bien réussi
     */
    public static boolean loadMailAccount(User user){
        
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
            
            ResultSet result = statementInstance.executeQuery( 
            		  "SELECT count(id), id, adresse, cookieswipename, domain, password, lastsync, mailsignature, color "
            		+ "FROM mailaccount "
            		+ "WHERE user_id ='" + user.getId() + "';" );

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
            	mailAccount.setLastSynch(result.getDate(6));
            	mailAccount.setMailSignature(result.getString(7));
            	mailAccount.getDomain().setId(result.getInt("domain"));
            	if(!loadDomail(mailAccount)) {
            		loadMailAccount = false;
            	}
            	//user.addNewMailAccount(mailAccount);
            	
            }
            return loadMailAccount;
        } catch ( SQLException e ) {
            // Traiter les erreurs éventuelles ici. 
        } finally {
            if ( statementInstance != null ) {
                try {
                    // Puis on ferme le Statement 
                    statementInstance.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connectionInstance != null ) {
                try {
                    /// Et enfin on ferme la connexion 
                    connectionInstance.close();
                } catch ( SQLException ignore ) {
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
       /* BDDConnect bddInstance = null;
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
            
            ResultSet result = statementInstance.executeQuery( "SELECT name,popaddr,smtpaddr,port FROM domain where id ='"+
                    mailAccount.getDomain().getId()+"';" );
            
            if(result.next()){
                mailAccount.getDomain().setDomain(result.getString("name"));
                mailAccount.getDomain().setPopAddress(result.getString("popaddr"));
                mailAccount.getDomain().setSmtpAddress(result.getString("smtpaddr"));
                mailAccount.getDomain().setPort(result.getString("port"));
                return true;
            }                      
            
        } catch ( SQLException e ) {
            // Traiter les erreurs éventuelles ici.
        } finally {
            if ( statementInstance != null ) {
                try {
                    // Puis on ferme le Statement 
                    statementInstance.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connectionInstance != null ) {
                try {
                    // Et enfin on ferme la connexion
                    connectionInstance.close();
                } catch ( SQLException ignore ) {
                }
            }
        }*/
        return false;
    }
    

    public static boolean updateMailAccount(MailAccount mailaccount) {
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
            
            int statut = statementInstance.executeUpdate( 
            		"UPDATE mailaccount "
                    		+ "SET adresse = '" + mailaccount.getAddress() + "', "
                    		+ "cookieswipename = '" + mailaccount.getCSName() + "', "
                    		+ "domain = '" + mailaccount.getDomain().getId() + "', "
                    		+ "password = '" + mailaccount.getPassword() + "', "
                    		+ "lastsync = '" + mailaccount.getLastSynch() + "', "
                    		+ "mailsignature = '" + mailaccount.getMailSignature() + "', "
                    		+ "color = '" + mailaccount.getColor() + "' "
                    		+ "WHERE id = " + mailaccount.getId() + ";"
            		);

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
     * Charge les courriels d'un compte courriel
     * @param mailAccount compte courriel concerné
     * @return si le chargement des courriels a réussi
     */
    public static boolean loadMail(MailAccount mailAccount){

        return false;
    }
}
