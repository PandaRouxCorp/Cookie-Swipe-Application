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
			int i = o2.getReceivedDate().compareTo(o1.getReceivedDate());
			return i;
		} catch (MessagingException e) {
			Logger.getLogger(getClass().getName()).log(Level.WARNING, "An error occured while comparing mail", e);
			return 0;
		}
	}

}
