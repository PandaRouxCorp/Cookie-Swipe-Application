/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Mary
 */
public class Mail implements Serializable {
    
    //Variable membre
    private int id;
    private Date date;
    private String to, from, subject, priority, body;
    private File[] attachement;

    //Constrcteur 
    
    /**
     * Constrcteur par défaut
     */
    public Mail(){
        
    }
    
    /**
     * Constructeur à utiliser pour créer un courriel basique
     * @param to adresse courriel destinataire du courriel
     */
    public Mail(String to) {
        this.to = to;
    }
    
    /**
     * Constructeur à utilsier pour répondre ou transférer un courriel
     * @param mail courriel que l'ont transfert ou que l'ont répond
     * @param to adresse courriel destinataire du courriel

     */
    public Mail(Mail mail, String to){
        this.to = to;
    }

    /**
     * Constrcteur à utiliser pour charger un courriel
     * @param id id du courriel
     * @param date date à laquel le courriel est envoyé ou reçu
     * @param to destinataire du courriel
     * @param from boite courriel expéditeur
     * @param subject sujet du courriel
     * @param priority priorité mise sur le courriel
     * @param body corps du courriel
     * @param attachement liste de fichier en pièce jointe du courriel
     */
    public Mail(int id, Date date, String to, String from, String subject, String priority, String body, File[] attachement) {
        this.id = id;
        this.date = date;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.priority = priority;
        this.body = body;
        this.attachement = attachement;
    }
    
    //Fonction membre publique
    
    //Getter & setter
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public File[] getAttachement() {
        return attachement;
    }

    public void setAttachement(File[] attachement) {
        this.attachement = attachement;
    }
    
    //response & forward mail
    public Mail response() {
    	Mail resp = new Mail();
    	resp.setBody(mailTranfer());
    	resp.setTo(from);
    	resp.setFrom(to);
    	resp.setSubject("FW : " + subject);
    	return resp;
    }
    
    public Mail forward() {
    	Mail resp = new Mail();
    	resp.setAttachement(attachement);
    	resp.setBody(mailTranfer());
    	resp.setSubject("FW : " + subject);
    	return resp;
    }
    
    private String mailTranfer() {
    	return "------------------------\n"
    			+ "From : " + from + "\n"
    	    	+ "To : "   + to   + "\n"
    	    	+ "Date : " + date + "\n\n"
    	    	+  body
    			+ "";
    }
    
    //equals & hashcode
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.id;
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
        final Mail other = (Mail) obj;
        return this.id == other.id;
    }

}
