/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import interfaces.AbstractIHMAction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import model.Mail;
import model.MailAccount;
import model.User;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeTree;
import controller.ActionName;
import controller.Dispatcher;
import cookie.swipe.application.CookieSwipeApplication;

/**
 *
 * @author Lucas
 */
public class MainFrameInitializer extends AbstractIHMAction { 
    
    public MainFrameInitializer(CookieSwipeFrame csFrame) {
		super(csFrame);
	}
	
	@Override
	public boolean execute(Object... object) {
		initMailAccount();
        initButton();
		return true;
	}
    
    public void deleteMailAccountInTree(MailAccount mc) {
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAccountMail");
        DefaultTreeModel model = (DefaultTreeModel) myTree.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        @SuppressWarnings("unchecked")
		Enumeration<DefaultMutableTreeNode> en = rootNode.breadthFirstEnumeration();
        while (en.hasMoreElements()) {
            DefaultMutableTreeNode node = en.nextElement();
            TreeNode[] path = node.getPath();
            if(path[path.length - 1].toString().equals(mc.getCSName())) {
                rootNode.remove(node);
                break; 
            }
        }
        model.reload();
    }
    
    public void addMailAccountInTree(MailAccount mc) {
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAccountMail");
        DefaultTreeModel model = (DefaultTreeModel) myTree.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        rootNode.add(createMailAccountFolder(mc));
        model.reload();
    }

    private MutableTreeNode createMailAccountFolder(MailAccount mailAccount) {
        DefaultMutableTreeNode folder = new DefaultMutableTreeNode(mailAccount);
        DefaultMutableTreeNode item = new DefaultMutableTreeNode("Boite de réception");
        folder.add(item);
        item = new DefaultMutableTreeNode("Boite d'envoie");
        folder.add(item);
        item = new DefaultMutableTreeNode("Corbeille");
        folder.add(item);
        return folder;
    }
    
    private void initMailAccount() {
        DefaultMutableTreeNode myRoot = new DefaultMutableTreeNode("Tous");

        MailAccount firstMailAccount = null;
        MutableTreeNode firstFolder = null;
        
        // Construction des différents noeuds de l'arbre.
        User user = CookieSwipeApplication.getApplication().getUser();
        for (MailAccount mailAccount : user.getListOfMailAccount()) {
        	if(firstMailAccount != null) {
        		myRoot.add(createMailAccountFolder(mailAccount));
        	}
        	else {
        		firstMailAccount = mailAccount;
        		firstFolder = createMailAccountFolder(mailAccount);
        		myRoot.add(firstFolder);
        	}
        }

        // Construction du modèle de l'arbre.
        DefaultTreeModel myModel = new DefaultTreeModel(myRoot);

        // Construction de l'arbre.
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAccountMail");
        myTree.setModel(myModel);
        
        if(firstMailAccount != null) {
	        @SuppressWarnings("unchecked")
			Enumeration<DefaultMutableTreeNode> en = myRoot.breadthFirstEnumeration();
	        en.nextElement();
	        DefaultMutableTreeNode node = en.nextElement();
	        myTree.setSelectionPath(new TreePath(node));
	        CookieSwipeApplication.getApplication().setParam("mailAccountSelected", firstMailAccount);
        }
        
        myTree.addMouseListener(new MouseListener() {
            CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAccountMail");
            @Override
            public void mouseClicked(MouseEvent e) {}
			@Override
            public void mousePressed(MouseEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTree.getLastSelectedPathComponent();
                if (node != null) {
                    if (node.getUserObject() instanceof MailAccount) {
                        displayMailAccountButton();
                        CookieSwipeApplication.getApplication().setParam("mailAccountSelected", node.getUserObject());
                    } else {
                        hiddeMailAccountButton();
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    class MailListViewModel extends AbstractListModel<Mail> {
		private static final long serialVersionUID = 4609356553818780860L;
		private MailAccount mailAccount;
		
		public MailListViewModel(MailAccount mailAccount) {
			this.mailAccount = mailAccount;
		}

		@Override
		public int getSize() {
			return mailAccount.getListOfmail().length;
		}

		@Override
		public Mail getElementAt(int index) {
			return mailAccount.getListOfmail()[index];
		}
    	
    }
    
    private void initButton() {
        Dispatcher dispatcher = new Dispatcher();
        
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonUpdateCSAccount");
        button.setActionCommand(ActionName.updateAccount);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonLogout");
        button.setActionCommand(ActionName.logout);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonAddMailAccount");
        button.setActionCommand(ActionName.addMailAccount);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonUpdateMailAccount");
        button.setActionCommand(ActionName.selectMailAccount);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMailAccount");
        button.setActionCommand(ActionName.deleteMailAccount);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonNewMail");
        button.setActionCommand(ActionName.writeMail);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReply");
        button.setActionCommand(ActionName.replyMail);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setActionCommand(ActionName.deleteMail);
        button.addActionListener(dispatcher);

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setActionCommand(ActionName.forwardMail);
        button.addActionListener(dispatcher);

        hiddeMailAccountButton();
        hiddeMailButton();

    }

    private void displayMailAccountButton() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonUpdateMailAccount");
        button.setVisible(true);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMailAccount");
        button.setVisible(true);
    }

    private void hiddeMailAccountButton() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonUpdateMailAccount");
        button.setVisible(false);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMailAccount");
        button.setVisible(false);

    }

    private void displayMailButton() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReply");
        button.setVisible(true);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setVisible(true);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setVisible(true);
    }

    private void hiddeMailButton() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReply");
        button.setVisible(false);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setVisible(false);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setVisible(false);

    }
    
    @SuppressWarnings("unchecked")
	public void addMailsToList(MailAccount mc, List<Mail> mails) {
		DefaultListModel<Mail> model = (DefaultListModel<Mail>) ((JList<Mail>) hsJcomponent.get("jListMail")).getModel();
		for(Mail mail : mails) {
			model.addElement(mail);
		}
    }

	public void updateMailAccountMailList(MailAccount mc) {
		System.err.println("Not implemented yet");
	}
}
