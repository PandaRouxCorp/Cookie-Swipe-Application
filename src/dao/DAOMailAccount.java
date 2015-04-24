/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BDDConnect;
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
        
        BDDConnect bddInstance = null;
        Connection connectionInstance = null;
        Statement statementInstance = null;
        String encryptedPassword = null;
        
        try {
            try {
                encryptedPassword = new Encryption().encrypt(mailAccount.getPassword());
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
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            
            int statut = statementInstance.executeUpdate( "INSERT INTO mailaccount( user_id," +
                                                                                    "adresse," +
                                                                                    "cookieswipename," +
                                                                                    "domain," +
                                                                                    "password," +
                                                                                    "lastsync," +
                                                                                    "mailsignature," +
                                                                                    "color) VALUES ('"+
                    user.getId()+"','"+mailAccount.getAddress()+"','"+mailAccount.getCSName()+"','"+mailAccount.getDomain()+"','"+
                    encryptedPassword+"','"+dateFormat.format(cal)+"','"+mailAccount.getMailSignature()+"','"+mailAccount.getColor()+"';" );
                    
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
     * Charge toute les données d'un compte courriel qui était sauvegardé
     * @param mailAccount compte courriel concerné
     * @return si le chargement du compte courriel a bien réussi
     */
    public static boolean loadMailAccount(MailAccount mailAccount){
        
        return false;
    }
    
    /**
     * Charge le domaine d'un comtpe courriel
     * @param mailAccount compte courriel concerné
     * @return si le chargement du domaine a réussi
     */
    public static boolean loadDomail(MailAccount mailAccount){
    
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
