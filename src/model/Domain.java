/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Objects;

/**
 * Domaine d'une adresse courriel
 * @author Mary
 */
public class Domain {
    
    //Variable membre
    private String domain, popAddress, smtpAddress, port;
    private int id;
        
    //Constructeur
    
    /**
     * Constructeur par défaut
     */
    public Domain(){
        
    }
    
    /**
     * Constructeur à utiliser au chargement d'un domaine 
     * @param domain
     * @param popAddress
     * @param smtpAddress
     * @param port 
     */
    public Domain(String domain, String popAddress, String smtpAddress, String port) {    
        this.domain = domain;
        this.popAddress = popAddress;
        this.smtpAddress = smtpAddress;
        this.port = port;
    }

    //Fonction membre publique
    
    /**
     * Sert à envoyer un courriel
     * @param mail courriel à envoyer
     * @return Si l'envoi c'est bien passé
     */
    public boolean sendMail(Mail mail){
        return false;
    }
    
    //Getter & setter
    
    public String getDomain() {
        return domain;
    }

    public String getPopAddress() {
        return popAddress;
    }

    public String getSmtpAddress() {
        return smtpAddress;
    }

    public String getPort() {
        return port;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPopAddress(String popAddress) {
        this.popAddress = popAddress;
    }

    public void setSmtpAddress(String smtpAddress) {
        this.smtpAddress = smtpAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //Equals & hashCode
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.domain);
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
        final Domain other = (Domain) obj;
        return Objects.equals(this.domain, other.domain);
    } 

}
