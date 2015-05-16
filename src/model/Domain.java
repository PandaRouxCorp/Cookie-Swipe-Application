/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Objects;
import javax.swing.JOptionPane;

/**
 * Domaine d'une adresse courriel
 * @author Mary
 */
public class Domain {
    
    //Variable membre
    private String name, address, serverIn, serverOut, portIn, portOut;
    private int id;
    //Constructeur
    
    /**
     * Constructeur par défaut
     */
    public Domain(){
        
    }
    
    public Domain(String domain) throws NullPointerException{
        System.err.println(domain);
        id = 1;
        switch(domain){
            case "yahoo.fr" :
                name = "Yahoo!";
                address = domain;
                serverIn = "pop.mail.yahoo.fr";
                portIn = "995";
                serverOut = "smtp.mail.yahoo.fr";
                portOut = "465";
                break;
            case "hotmail.com" :
            case "hotmail.fr" :
            case "live.com" :
            case "live.fr" :
            case "msn.com" :
            case "outlook.com" :
                name = "Hotmail";
                address = domain;
                serverIn = "pop3.live.com";
                portIn = "995";
                serverOut = "smtp.live.com";
                portOut = "587";
                break;
            case "orange.fr" :
            case "wanadoo.fr" :
                name = "";
                address = domain;
                serverIn = "";
                portIn = "";
                serverOut = "";
                portOut = "";
                break;
            case "gmail.com" :
                name = "Gmail";
                address = domain;
                serverIn = "imap.gmail.com";
                portIn = "993";
                serverOut = "smtp.gmail.com";
                portOut = "465";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Domaine inconnu", 
                    "Ajout d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);
                throw new NullPointerException("Domain unknow");

        }
    }

    
    public Domain(String name, String address, String serverIn, String serverOut, String portIn, String portOut) {
        this.name = name;
        this.address = address;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        this.portIn = portIn;
        this.portOut = portOut;
    }

    public Domain(String name, String address, String serverIn, String serverOut, String portIn, String portOut, int id) {
        this.name = name;
        this.address = address;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        this.portIn = portIn;
        this.portOut = portOut;
        this.id = id;
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

    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getServerIn() {
        return serverIn;
    }

    public void setServerIn(String serverIn) {
        this.serverIn = serverIn;
    }

    public String getServerOut() {
        return serverOut;
    }

    public void setServerOut(String serverOut) {
        this.serverOut = serverOut;
    }

    public String getPortIn() {
        return portIn;
    }

    public void setPortIn(String portIn) {
        this.portIn = portIn;
    }

    public String getPortOut() {
        return portOut;
    }

    public void setPortOut(String portOut) {
        this.portOut = portOut;
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
        hash = 29 * hash + Objects.hashCode(this.name);
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
        return Objects.equals(this.name, other.name);
    } 

}
