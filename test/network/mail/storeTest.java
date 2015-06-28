package network.mail;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPFolder;

public class storeTest {
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
        // Get a Session object
        Session session = Session.getInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        
        // Ouvrir la connexion
        store.connect("imap.gmail.com", "address@gmail.com","password");

        IMAPFolder folder = (IMAPFolder) store.getDefaultFolder();
        
        FolderManager manager = new FolderManager(folder);
        manager.startSync();
	}
}