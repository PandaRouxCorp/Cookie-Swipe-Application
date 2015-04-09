/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Mary
 */
public class MailAccount {
    
    //Variable membre
    
    private int id;
    private String address, CSName, password, color, mailSignature;
    private Domain domain;
    private Date lastSynch;
    private ArrayList<Mail> listOfmail;

    //Constructeur 
    
    /**
     * Constructeur par défaut
     */
    public MailAccount(){
        
    }
    
    /**
     * Constructeur à utiliser lors de l'ajout d'une boite courriel 
     * @param address adresse courriel de la boite
     * @param CSName nom donné à la botie courriel
     * @param password mot de passe pour accéder à la boite courriel
     * @param color couleur donné à la boite courriel pour l'affichage des courriels
     */
    public MailAccount(String address, String CSName, String password, String color) {
        this.address = address;
        this.CSName = CSName;
        this.password = password;
        this.color = color;
    }

    /**
     * Constructeur à utiliser pour charger les comptes courriel dans l'application
     * @param id identifiant unique de la boite mail
     * @param address adresse courriel de la boite
     * @param CSName nom donné à la boite courriel
     * @param password mot de passe pour accéder à la boite mail
     * @param color couleur donné à la boite courriel pour l'affichage des courriels
     * @param domain domaine de l'adresse courriel
     * @param mailSignature signature à mettre à la fin d'un courriel
     * @param lastSynch date de la dernière synchronisation réussi de la boite courriel
     * @param listOfMail Liste des courriels de la boite mail
     */
    public MailAccount(int id, String address, String CSName, String password, String color,
            Domain domain,String mailSignature, Date lastSynch, ArrayList<Mail> listOfMail) {
        this.id = id;
        this.address = address;
        this.CSName = CSName;
        this.password = password;
        this.color = color;
        this.domain = domain;
        this.mailSignature = mailSignature;
        this.lastSynch = lastSynch;
        this.listOfmail = listOfMail;
    }
    
    //Fonction membre public
    
    /**
     * Modifie les données du compte courriel
     * @param data données du compte courriel
     * @return Si la mise à jours des données est correct
     */
    public boolean updateMailAccount(String ... data){
        return false;
    }
    
    /**
     * Sert à créer un nouveau courriel
     * @return courriel créé
     */
    public Mail createNewMail(){
        return null;
    }
    
    /**
     * Sert à envoyer un courriel, délègue l'envoi à un domaine
     * @param mail courriel à envoyer
     * @return Si l'envoi c'est bien passé
     */
    public boolean sendMail(Mail mail){
        return false;
    }
    
    /**
     * Sert à suppriemr un courriel de la boite courriel
     * @param mail courriel à supprimer
     * @return Si la suppresion c'est bien passé
     */
    public boolean deleteMail(Mail mail){
        return false;
    }
    
    //Getter & setter
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCSName() {
        return CSName;
    }

    public void setCSName(String CSName) {
        this.CSName = CSName;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailSignature() {
        return mailSignature;
    }

    public void setMailSignature(String mailSignature) {
        this.mailSignature = mailSignature;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getLastSynch() {
        return lastSynch;
    }

    public void setLastSynch(Date lastSynch) {
        this.lastSynch = lastSynch;
    }

    public ArrayList<Mail> getListOfmail() {
        return listOfmail;
    }

    public void setListOfmail(ArrayList<Mail> listOfmail) {
        this.listOfmail = listOfmail;
    }
    
    //equals & hashcode
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.address);
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
        final MailAccount other = (MailAccount) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.address, other.address);
    }
    
}
