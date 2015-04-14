/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.mail;

import model.Mail;
import network.messageFramework.Message;

/**
 *
 * @author mickx
 */
public class MailMessage extends Message<Mail> {
    
    private final Mail mail;

    public MailMessage(Mail mail) {
        this.mail = mail;
    }

    @Override
    public Mail call() throws Exception {
        Thread.sleep(100); // Attention quand cette ligne sera enlevée il se peut que les test unitaire de MailSender ne passe plus mais ce n'est pas grave
        // Ici on fait la requête réseau
        return mail;
    }
    
    @Override
    public boolean shouldBeSavedIfNotExecuted() {
        return true;
    }
}
