/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import model.MailAccount;


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
    public static boolean createMailAccount(MailAccount mailAccount){
        
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
