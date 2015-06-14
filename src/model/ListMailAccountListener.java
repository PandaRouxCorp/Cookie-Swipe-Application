/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mickx
 */
public interface ListMailAccountListener {

    public void notifyMailAccountDeleted(MailAccount mc);
    public void notifyMailAccountAdded(MailAccount mc);

    public void notifyMailDeleted(MailAccount mc);

    public void notifyMailAdded(MailAccount mc);

    public void notifyMailListChanged(MailAccount mc);
    
}
