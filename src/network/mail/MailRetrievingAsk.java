package network.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;

import model.Mail;
import network.messageFramework.FrameworkMessage;

/**
 * Cette classe represente une tache qui recupere les comptes mails d'une boite
 * @author mickx
 *
 */

public class MailRetrievingAsk extends FrameworkMessage<List<Mail>> {
	
	private int begin, end;
	private Message[] messageToRetrieve;
	
	public MailRetrievingAsk(int begin, int end, Message[] messagesToRetrieve) {
		this.begin = begin;
		this.end = end > messagesToRetrieve.length ? messagesToRetrieve.length : end;
		this.messageToRetrieve = messagesToRetrieve;
	}
	
	private static final long serialVersionUID = -2023633522482841762L;
	@Override
	public List<Mail> call() throws Exception {
		List<Mail> mailsRetrieved = new ArrayList<>();
        for (int j = begin; j < end; j++) {
            Mail mail = new Mail();
            mail.setBody(messageToRetrieve[j].getDescription());
            mail.setSubject(messageToRetrieve[j].getSubject());
            mail.setDate(messageToRetrieve[j].getReceivedDate());
            String from = "";
            for (Address f : messageToRetrieve[j].getFrom()) {
                from += f.toString() + "; ";
            }
            mail.setFrom(from);
            mailsRetrieved.add(mail);
        }
        return mailsRetrieved;
	}
}
