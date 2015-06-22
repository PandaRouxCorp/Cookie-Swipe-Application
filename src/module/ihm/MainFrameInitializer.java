/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import interfaces.AbstractIHMAction;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
import view.component.CookieSwipeList;
import view.component.CookieSwipeTree;
import controller.ActionName;
import controller.Dispatcher;
import cookie.swipe.application.CookieSwipeApplication;

/**
 *
 * @author Lucas
 */
public class MainFrameInitializer extends AbstractIHMAction { 
	
	public static final String jListMailModels = "jListMailModels";
	private static final String AllFolder = "Tous";
	public static List<String> folderNames;
	
	static {
		folderNames = new ArrayList<>();
		folderNames.add("Boite de réception");
		folderNames.add("Boite d'envoie");
		folderNames.add("Corbeille"); 
	}

	private CookieSwipeFrame csFrame;
    
    public MainFrameInitializer(CookieSwipeFrame csFrame) {
		super(csFrame);
		this.csFrame = csFrame;
	}
	
	@Override
	public boolean execute(Object... object) {
		initMailAccount();
        initButton();
        initMailList();
		return true;
	}
    
    private void initMailList() {
    	@SuppressWarnings("unchecked")
		CookieSwipeList<Mail> jListMail = (CookieSwipeList<Mail>) hsJcomponent.get("jListMail");
    	jListMail.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				setMailButtonsVisible(false);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				setMailButtonsVisible(true);
			}
		});
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
        DefaultMutableTreeNode item;
        for(String folderName : folderNames) {
        	item = new DefaultMutableTreeNode(folderName);
        	folder.add(item);
        }
        return folder;
    }
    
    private void initModels() {
    	HashMap<String, HashMap<String, DefaultListModel<Mail>>> models = new HashMap<>();
        CookieSwipeApplication.getApplication().setParam(jListMailModels, models);
        
        User user = CookieSwipeApplication.getApplication().getUser();
        for (MailAccount mailAccount : user.getListOfMailAccount()) {
        	HashMap<String, DefaultListModel<Mail>> map = new HashMap<String, DefaultListModel<Mail>>();
    		for(String folderName : folderNames) {
    			map.put(folderName, new DefaultListModel<Mail>());
    		}
        	models.put(mailAccount.getCSName(), map);
        }
    	
    	HashMap<String, DefaultListModel<Mail>> allModel = new HashMap<String, DefaultListModel<Mail>>();
        allModel.put(folderNames.get(0), new DefaultListModel<Mail>());
        models.put(AllFolder, allModel);
	}
    
    @SuppressWarnings("unchecked")
	private void initMailAccount() {
        DefaultMutableTreeNode myRoot = new DefaultMutableTreeNode(AllFolder);

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
        
        // Creation des models de données pour les listes de mails à afficher
        initModels();

        // Construction du modèle de l'arbre.
        DefaultTreeModel myModel = new DefaultTreeModel(myRoot);

        // Construction de l'arbre.
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAccountMail");
        myTree.setModel(myModel);
        
        if(firstMailAccount != null) {
			Enumeration<DefaultMutableTreeNode> en = myRoot.breadthFirstEnumeration();
	        en.nextElement();
	        DefaultMutableTreeNode node = en.nextElement();
	        myTree.setSelectionPath(new TreePath(node));
	        CookieSwipeApplication.getApplication().setParam("mailAccountSelected", firstMailAccount);
        }
        
        myTree.addMouseListener(new TreeMouseListener(myTree,(JList<Mail>) hsJcomponent.get("jListMail")));
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

        setMailAccountButtonsVisible(false);
        setMailButtonsVisible(false);
        csFrame.pack();
    }

    private void setMailAccountButtonsVisible(boolean b) {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonUpdateMailAccount");
        button.setVisible(b);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMailAccount");
        button.setVisible(b);
        csFrame.pack();
    }

    private void setMailButtonsVisible(boolean b) {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReply");
        button.setVisible(b);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setVisible(b);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setVisible(b);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReplyToAll");
        button.setVisible(b);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonArchive");
        button.setVisible(b);
        csFrame.pack();
    }
    
    @SuppressWarnings("unchecked")
	public void addMailsToList(MailAccount mc, List<Mail> mails) {
    	HashMap<String, HashMap<String, DefaultListModel<Mail>>> models = 
    			(HashMap<String, HashMap<String, DefaultListModel<Mail>>>) 
    				CookieSwipeApplication.getApplication().getParam(jListMailModels);
    	DefaultListModel<Mail> model = models.get(AllFolder).get(folderNames.get(0));
    	for(Mail mail : mails) {
			model.addElement(mail);
		}
    	model = models.get(mc.getCSName()).get(folderNames.get(0));
    	for(Mail mail : mails) {
			model.addElement(mail);
		}
    }

	public void updateMailAccountMailList(MailAccount mc) {
		System.err.println("Not implemented yet");
	}
	
	private class TreeMouseListener extends MouseAdapter {
		private CookieSwipeTree myTree;
		private JList<Mail> jListMail;
		
		public TreeMouseListener(CookieSwipeTree myTree, JList<Mail> jListMail) {
			this.myTree = myTree;
			this.jListMail = jListMail;
		}
		@Override
	    public void mousePressed(MouseEvent e) {
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode) myTree.getLastSelectedPathComponent();
	        DefaultListModel<Mail> model = getModelForSelection(node);
	        jListMail.setModel(model);
	    }
		
		@SuppressWarnings("unchecked")
		private DefaultListModel<Mail> getModelForSelection(DefaultMutableTreeNode node) {
			String keyAccount = null, keyFolder = null;
			if (node != null) {
	        	Object userObject = node.getUserObject();
	            if (userObject instanceof MailAccount) {
	            	setMailAccountButtonsVisible(true);
	                CookieSwipeApplication.getApplication().setParam("mailAccountSelected", node.getUserObject());
	                keyAccount = ((MailAccount)userObject).getCSName();
	                keyFolder = folderNames.get(0);
	            } else {
	            	setMailAccountButtonsVisible(false);
	                if(node.getUserObject() instanceof String) {
	                	String label = (String) node.getUserObject();
	                	if(folderNames.contains(label)) {
	                		MailAccount mailAccount = (MailAccount) ((DefaultMutableTreeNode)node.getParent()).getUserObject();
	                		keyAccount = mailAccount.getCSName();
	                		keyFolder = label;
	                	}
	                	else {
	                		keyAccount = AllFolder;
	    	                keyFolder = folderNames.get(0);
	                	}
	                }
	            }
	        }
			
			if(keyAccount == null || keyFolder == null) return null;
			
			HashMap<String, HashMap<String, DefaultListModel<Mail>>> models;
			models = (HashMap<String, HashMap<String, DefaultListModel<Mail>>>)
					CookieSwipeApplication.getApplication().getParam(jListMailModels);
			return models.get(keyAccount).get(keyFolder);
		}
	}
}
