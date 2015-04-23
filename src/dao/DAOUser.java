/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
        
        return false;
    }
    
     /**
     * Connecte l'utilisateur si il existe en persistance
     * @param user utilisateur qui tente de se connecter
     *@return si la conenction de l'utilisateur a réussi
     */
    public static boolean connectUser(User user){
        
        return false;
    }
}
