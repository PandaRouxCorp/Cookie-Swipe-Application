/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author mickx
 */
public interface ListMailAccountListener {

    public void notifyMailAccountDeleted(MailAccount mc);
    public void notifyMailAccountAdded(MailAccount mc);
    public void notifyMailsDeleted(MailAccount mc, List<Mail> mails);
    public void notifyMailsAdded(MailAccount mc, List<Mail> mails);
    public void notifyMailListChanged(MailAccount mc);
    
}
