/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import network.mail.FolderManager;
import cookie.swipe.application.CookieSwipeApplication;
import dao.DAOMailAccount;
import errorMessage.CodeError;
import network.messageFramework.DeliverySystem;
import network.messageFramework.FrameworkMessage;

/**
 * Utilisateur de l'application Cookie Swipe Peut être un singleton ?
 *
 * @author Mary
 */
public class User {

    //Variable membre
    private int id;
    private String loginAdressMail, password, backupMail;
    private List<MailAccount> listOfMailAccount = new ArrayList<>();
    private List<String> blackList;
    private List<ListMailAccountListener> mailAccountListeners = new ArrayList<>();

    //Constrcuteur
    /**
     * Constructeur par defaut
     */
    public User() {

    }

    /**
     * Constructeur à utiliser lors de la connexion à un compte CS
     *
     * @param loginAdressMail login de connexion du compte
     * @param password mot de passe de connexion du compte
     */
    public User(String loginAdressMail, String password) {
        this.loginAdressMail = loginAdressMail;
        try {
            this.password = new Encryption().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            this.password = "";
        }

    }

    /**
     * Constructeur à utiliser lors de la création d'un compte CS
     *
     * @param loginAdressMail login du compte et adresse principale
     * @param password Mot de passe du compte
     * @param backUpMail adrese courriel de récupération
     */
    public User(String loginAdressMail, String password, String backUpMail) {
        this.loginAdressMail = loginAdressMail;
        try {
            this.password = new Encryption().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            this.password = "";
        }
        this.backupMail = backUpMail;
    }

    //Fonction membre publique
    /**
     * Sert à créer un utilisateur dans le registre CS
     *
     * @return l'utilisateur créé
     */
    public int create() {
        return dao.DAOUser.createUser(this);
    }

    /**
     * Sert à connecter un utilisateur à l'application CS
     *
     * @return Utilisateur connecté
     */
    public int connect() {
    	return dao.DAOUser.connectUser(this);
    }

    /**
     * Renvoie toutes les données de l'utilisateur
     *
     * @return table de hash contenant toutes les données utilisateur
     */
    public HashMap<String, Object> getData() {
        return null;
    }

    /**
     * Change les informations utilisateur (A mettre en hashmap)
     *
     * @param data table de hash contenant toute les données utilisateur
     * @return Si la mise à jour des données à été correct
     */
    public boolean updateData(HashMap<String, Object> data) {
        return false;
    }

    /**
     * *
     * Ajoute un compte courriel au préférence de l'utilisateur
     *
     * @param newMailAccount compte courriel à ajouter
     * @return Si le compte courriel à bien été ajouté
     */
    public int addNewMailAccount(String name, String address, String password, String color) {
        String mailDomain = address.substring(address.indexOf('@') + 1);
        Domain domain = null;
        try {
            domain = Domain.getDomainFor(mailDomain);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
        
        MailAccount newMailAccount = new MailAccount(name, address, domain, password, color);
        if(!newMailAccount.connectionIsOk()) {	
        	return CodeError.CONNEXION_FAIL;
        }
        
        int res = DAOMailAccount.createMailAccount(newMailAccount, this);
        if (res == CodeError.SUCESS) {
            addMailAccount(newMailAccount);
        }
        return res;
    }

    private void addMailAccount(MailAccount newMailAccount) {
        listOfMailAccount.add(newMailAccount);
        notifyMailAccountAdded(newMailAccount);	
        DeliverySystem.launchTask(new FrameworkMessage<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    FolderManager fm = (FolderManager)CookieSwipeApplication.getApplication().getParam("FolderManager");
                    fm.addNewMailAccount(newMailAccount);
                } catch (Exception e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while opening store", e);
		}
                return null;
            }

        });
    }

	public int updatemailAccount(MailAccount mailAccount) {
        return DAOMailAccount.updateMailAccount(mailAccount);
    }

    /**
     * Supprime un compte courriel des préférence de l'utilisateur
     *
     * @param deletedMailAccount compte courriel à supprimer
     * @return Si le compte courriel à bien été supprimé
     */
    public int deleteMailAccount(MailAccount deletedMailAccount) {
        int error = DAOMailAccount.deleteMailAccount(deletedMailAccount);
        if (error == CodeError.SUCESS) {
            listOfMailAccount.remove(deletedMailAccount);
            notifyMailAccountDeleted(deletedMailAccount);
        }
        return error;
    }

    /**
     * Ajoute un expéditeur à la liste noire
     *
     * @param sender adresse courriel de l'expéditeur à rajouter
     * @return Si l'adresse à bien été ajoutée de la liste noire
     */
    public boolean blackListSender(String sender) {
        if (blackList == null) {
            blackList = new ArrayList<String>();
        }
        return blackList.add(sender);
    }

    /**
     * Supprimer un expéditeur de la liste noire
     *
     * @param sender adresse courriel de l'expéditeur à supprimer
     * @return Si l'adresse à bien été retirée de la liste noire
     */
    public boolean removeBlackListSender(String sender) {
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
        try {
            this.password = new Encryption().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getBackupMail() {
        return backupMail;
    }

    public void setBackupMail(String backupMail) {
        this.backupMail = backupMail;
    }

    public List<MailAccount> getListOfMailAccount() {
        return listOfMailAccount;
    }

    public void setListOfMailAccount(List<MailAccount> listOfMailAccount) {
        this.listOfMailAccount = listOfMailAccount;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
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
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", loginAdressMail=" + loginAdressMail + ", password=" + password + ", backupMail=" + backupMail + ", listOfMailAccount=" + listOfMailAccount + ", blackList=" + blackList + '}';
    }

    public void addListMailAccountListeneur(ListMailAccountListener listener) {
        mailAccountListeners.add(listener);
    }
    
    private void notifyMailAccountAdded(MailAccount mc) {
        for(ListMailAccountListener listener : mailAccountListeners) {
            listener.notifyMailAccountAdded(mc);
        }
    }
    
    private void notifyMailAccountDeleted(MailAccount mc) {
        for(ListMailAccountListener listener : mailAccountListeners) {
            listener.notifyMailAccountDeleted(mc);
        }
    }

    public void retrieveMails() {
    	DAOMailAccount.loadMails(listOfMailAccount);
    }

}
