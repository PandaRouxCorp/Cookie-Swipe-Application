package model;

import java.util.Comparator;

public class MailComparator implements Comparator<Mail> {

	@Override
	public int compare(Mail m1, Mail m2) {
		if(m1.equals(m2)) return 0;
		return m1.getDate().compareTo(m2.getDate());
	}

}
