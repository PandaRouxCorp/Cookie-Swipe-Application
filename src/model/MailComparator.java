package model;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;

public class MailComparator implements Comparator<Message> {

	@Override
	public int compare(Message o1, Message o2) {
		try {
			return o1.getSentDate().compareTo(o2.getSentDate());
		} catch (MessagingException e) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "An error occured while comparing mail", e);
			return 0;
		}
	}

}
