/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * Domaine d'une adresse courriel
 *
 * @author Mary
 */
public class Domain {

    static Domain getDomainFor(String mailDomain) throws Exception {
        Domain domain = null;
        switch (mailDomain) {
            case "yahoo.fr":
                domain = new Domain("Yahoo", mailDomain, "pop.mail.yahoo.fr",
                        "995", "smtp.mail.yahoo.fr", "465", "pop3", 1);
                break;
            case "hotmail.com":
            case "hotmail.fr":
            case "live.com":
            case "live.fr":
            case "msn.com":
            case "outlook.com":
                domain = new Domain("Microsoft", mailDomain, "pop3.live.com",
                        "995", "smtp.live.com", "587", "pop3", 2);
                break;
            case "orange.fr":
            case "wanadoo.fr":
                domain = new Domain("Orange", mailDomain, "", "", "", "", "", 3);
                break;
            case "gmail.com":
                domain = new Domain("Google", mailDomain, "imap.gmail.com", "993",
                        "smtp.gmail.com", "465", "imaps", 4);
                break;
            default:
                throw new Exception("Unknown domain: " +  mailDomain);
        }
        return domain;
    }

    //Variable membre
    private String name, address, serverIn, serverOut, portIn, portOut, storeProtocol;
    private int id;
    
    //Constructeur

    /**
     * Constructeur par défaut
     */
    public Domain() {

    }

    public Domain(String name, String address, String serverIn, String serverOut, String portIn, String portOut, String storeProtocole) {
        this.name = name;
        this.address = address;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        this.portIn = portIn;
        this.portOut = portOut;
        this.storeProtocol = storeProtocole;
    }

    public Domain(String name, String address, String serverIn, String serverOut, String portIn, String portOut, String storeProtocol, int id) {
        this.name = name;
        this.address = address;
        this.serverIn = serverIn;
        this.serverOut = serverOut;
        this.portIn = portIn;
        this.portOut = portOut;
        this.storeProtocol = storeProtocol;
        this.id = id;
    }

    //Fonction membre publique
    /**
     * Sert à envoyer un courriel
     *
     * @param mail courriel à envoyer
     * @return Si l'envoi c'est bien passé
     */
    public boolean sendMail(Mail mail) {
        return false;
    }

    //Getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
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
    
    public String getStoreProtocol() {
        return this.storeProtocol;
    }
    
    public void setStoreProtocol(String storeProtocol) {
        this.storeProtocol = storeProtocol; 
    }

    //Surcharge de Object
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
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domain other = (Domain) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "Domain{" + "name=" + name + ", address=" + address + ", serverIn=" + serverIn + ", serverOut=" + serverOut + ", portIn=" + portIn + ", portOut=" + portOut + ", id=" + id + '}';
    }

}
