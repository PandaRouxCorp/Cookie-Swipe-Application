/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import cookie.swipe.application.CookieSwipeApplication;
import interfaces.AbstractIHMAction;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import model.Mail;
import model.MailAccount;
import model.User;
import view.MainCSFrame;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeTree;

/**
 *
 * @author Lucas
 */
public class MainFrameInitializer extends AbstractIHMAction {
    
    public MainFrameInitializer(MainCSFrame frame) {
        super(frame);
    }
    
    @Override
    public boolean execute(Object... object) {
        initMail();
        initMailAccount();
        initButton();
        return true;
    }
    
    public void deleteMailAccountInTree(MailAccount mc) {
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAcountMail");
        DefaultTreeModel model = (DefaultTreeModel) myTree.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        Enumeration<DefaultMutableTreeNode> en = rootNode.breadthFirstEnumeration();
        while (en.hasMoreElements()) {
            DefaultMutableTreeNode node = en.nextElement();
            TreeNode[] path = node.getPath();
            if(path[path.length - 1].equals(mc.getCSName())) {
                rootNode.remove(node);
                break; 
            }
        }
        model.reload();
    }
    
    public void addMailAccountInTree(MailAccount mc) {
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAcountMail");
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

        // Construction des différents noeuds de l'arbre.
        User user = CookieSwipeApplication.getApplication().getUser();
        for (MailAccount mailAccount : user.getListOfMailAccount()) {
            myRoot.add(createMailAccountFolder(mailAccount));
        }

        // Construction du modèle de l'arbre.
        DefaultTreeModel myModel = new DefaultTreeModel(myRoot);

        // Construction de l'arbre.
        CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAcountMail");
        myTree.setModel(myModel);

        myTree.addMouseListener(new MouseListener() {
            CookieSwipeTree myTree = (CookieSwipeTree) hsJcomponent.get("cookieSwipeTreeAcountMail");
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

        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonAnswer");
        button.setActionCommand(ActionName.answerMail);
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

    private void initMail() {
        JList list = (JList) hsJcomponent.get("jListMail");
        User user = CookieSwipeApplication.getApplication().getUser();
        int i = 0;
        for (MailAccount mailAccount : user.getListOfMailAccount()) {
//            try {
//                mailAccount.readMessage();
//            } catch (Exception ex) {
//                Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
//            }
            mailAccount.getListOfmail().add(new Mail(i, new Date(2014, 12, 22), mailAccount.getAddress(),
                    "lucas.girardin@ipsen.com", "Sans objet", "low", "Hello panda", null));
            i++;
        }
        list.setModel(new javax.swing.AbstractListModel() {
            ArrayList<Mail> mailList = new ArrayList<>();
            
            @Override
            public int getSize() {
                return 10;
            }

            public Object getElementAt(int i) {
                return null;
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                displayMailButton();
            }
        });

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
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonAnswer");
        button.setVisible(true);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setVisible(true);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setVisible(true);
    }

    private void hiddeMailButton() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonAnswer");
        button.setVisible(false);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonDeleteMail");
        button.setVisible(false);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        button.setVisible(false);

    }
}
