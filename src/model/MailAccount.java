/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javax.mail.Address;

/**
 *
 * @author Mary
 */
public class MailAccount extends Address {
    
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
        domain = new Domain(); 
    }
    
    /**
     * Constructeur à utiliser lors de l'ajout d'une boite courriel 
     * @param CSName nom donné à la botie courriel
     * @param address adresse courriel de la boite
     * @param password mot de passe pour accéder à la boite courriel
     * @param color couleur donné à la boite courriel pour l'affichage des courriels
     */
    public MailAccount(String CSName, String address, String password, String color) {
        
        String mailDomain = address.substring(address.indexOf('@')+1);
        domain = new Domain(mailDomain);
        this.CSName = CSName;
        this.address = address;
        this.password = password;
        this.color = color;
    }

    /**
     * Constructeur à utiliser pour charger les comptes courriel dans l'application
     * @param id identifiant unique de la boite mail
     * @param CSName nom donné à la boite courriel
     * @param address adresse courriel de la boite
     * @param password mot de passe pour accéder à la boite mail
     * @param color couleur donné à la boite courriel pour l'affichage des courriels
     * @param domain domaine de l'adresse courriel
     * @param mailSignature signature à mettre à la fin d'un courriel
     * @param lastSynch date de la dernière synchronisation réussi de la boite courriel
     * @param listOfMail Liste des courriels de la boite mail
     */
    public MailAccount(int id, String CSName, String address, String password, String color,
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
     * Renvoie les toutes données du compte courriel
     * @return table de hash contenant toutes les données du compte courriel
     */
    public HashMap<String, Object> getData(){
       
        return null;
    }
    
    /**
     * Modifie les données du compte courrie
     * @param data table de hash contenant les données du compte courriel
     * @return Si la mise à jours des données est correct
     */
    public boolean updateData(HashMap<String, Object> data){
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

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "MailAccount{" + "id=" + id + ", address=" + address + ", CSName=" + CSName + ", password=" + password + ", color=" + color + ", mailSignature=" + mailSignature + ", domain=" + domain + ", lastSynch=" + lastSynch + ", listOfmail=" + listOfmail + '}';
    }

}
