/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.DAOMailAccount;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Utilisateur de l'application Cookie Swipe 
 * Peut être un singleton ?
 * @author Mary
 */
public class User {
    
    //Variable membre
    
    private int id;
    private String loginAdressMail, password, backupMail;
    private ArrayList<MailAccount> listOfMailAccount;
    private ArrayList<String> blackList;
    
    //Constrcuteur
    
    /**
     * Constructeur par defaut
     */
    public User(){
        
    }
    
    /**
     * Constructeur à utiliser lors de la connexion à un compte CS
     * @param loginAdressMail login de connexion du compte
     * @param password mot de passe de connexion du compte
     */
    public User(String loginAdressMail, String password){
        this.loginAdressMail = loginAdressMail;
        this.password = password;
    }
    
    /**
     * Constructeur à utiliser lors de la création d'un compte CS
     * @param loginAdressMail login du compte et adresse principale
     * @param password Mot de passe du compte
     * @param backUpMail adrese courriel de récupération
     */
    public User(String loginAdressMail, String password, String backUpMail){
        this.loginAdressMail = loginAdressMail;
        this.password = password;
        this.backupMail = backUpMail;
    }
    
    //Fonction membre publique
    
    /**
     * Sert à créer un utilisateur dans le registre CS
     * @return l'utilisateur créé
     */
    public User create(){
    	return dao.DAOUser.createUser(this) ? this : null ;
    }
    
    /**
     * Sert à connecter un utilisateur à l'application CS
     * @return Utilisateur connecté
     */
    public User connect(){
    	return dao.DAOUser.connectUser(this) ? this : null ;
    }
    
    /**
     * Renvoie toutes les données de l'utilisateur
     * @return table de hash contenant toutes les données utilisateur
     */
    public HashMap<String, Object> getData(){
        return null;
    }
    
    /**
     * Change les informations utilisateur (A mettre en hashmap)
     * @param data table de hash contenant toute les données utilisateur
     * @return Si la mise à jour des données à été correct
     */
    public boolean updateData(HashMap<String, Object> data){
        return false;
    }
    
    /***
     * Ajoute un compte courriel au préférence de l'utilisateur
     * @param newMailAccount compte courriel à ajouter
     * @return Si le compte courriel à bien été ajouté
     */
    public boolean addNewMailAccount(String name, String adress, String password){
        boolean res = false;
        MailAccount newMailAccount = new MailAccount(name, adress, password, "blue");
        res = DAOMailAccount.createMailAccount(newMailAccount, this);
        if(res){
            res = listOfMailAccount.add(newMailAccount);
        }
        
        
        return res;
    }
    
    /**
     * Supprime un compte courriel des préférence de l'utilisateur
     * @param deletedMailAccount compte courriel à supprimer
     * @return Si le compte courriel à bien été supprimé
     */
    public boolean deleteMailAccount(MailAccount deletedMailAccount){
        return listOfMailAccount.remove(deletedMailAccount);
    }
    
    /**
     * Ajoute un expéditeur à la liste noire
     * @param sender adresse courriel de l'expéditeur à rajouter
     * @return Si l'adresse à bien été ajoutée de la liste noire
     */
    public boolean blackListSender(String sender){
    	if(blackList == null)
    		blackList = new ArrayList<String>();
    	return blackList.add(sender);
    }

    /**
     * Supprimer un expéditeur de la liste noire
     * @param sender adresse courriel de l'expéditeur à supprimer
     * @return Si l'adresse à bien été retirée de la liste noire
     */
    public boolean removeBlackListSender(String sender){
        return blackList.remove(sender);
    }

    //Getter & setter
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginAdressMail() {
        return loginAdressMail;
    }

    public void setLoginAdressMail(String loginAdressMail) {
        this.loginAdressMail = loginAdressMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBackupMail() {
        return backupMail;
    }

    public void setBackupMail(String backupMail) {
        this.backupMail = backupMail;
    }

    public ArrayList<MailAccount> getListOfMailAccount() {
        return listOfMailAccount;
    }

    public void setListOfMailAccount(ArrayList<MailAccount> listOfMailAccount) {
        this.listOfMailAccount = listOfMailAccount;
    }

    public ArrayList<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(ArrayList<String> blackList) {
        this.blackList = blackList;
    }

    //equals & hashcode
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if(this == obj){
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }
     
    
}
