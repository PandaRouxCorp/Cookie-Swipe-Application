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

    //Variable membre
    private String name, address, serverIn, serverOut, portIn, portOut;
    private int id;
    
    //Constructeur

    /**
     * Constructeur par défaut
     */
    public Domain() {

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
