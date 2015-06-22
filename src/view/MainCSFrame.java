/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import interfaces.IJFrame;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import model.ListMailAccountListener;
import model.Mail;
import model.MailAccount;
import module.ihm.MainFrameInitializer;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeColor;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipePanel;
import view.component.CookieSwipeTree;

/**
 *
 * @author Suiken
 */
public class MainCSFrame extends CookieSwipeFrame implements IJFrame, ListMailAccountListener {

	private static final long serialVersionUID = 8736726751871593606L;

	/**
     * Creates new form MainCSFrame
     */
    public MainCSFrame() {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        hsJcomponent.put("cookieSwipeButtonReply", cookieSwipeButtonReply);
        hsJcomponent.put("cookieSwipeButtonDeleteMail", cookieSwipeButtonDeleteMail);
        hsJcomponent.put("cookieSwipeButtonForward", cookieSwipeButtonForward);
        hsJcomponent.put("cookieSwipeButtonNewMail", cookieSwipeButtonNewMail);
        hsJcomponent.put("cookieSwipeTreeAccountMail", cookieSwipeTreeAccountMail);
        hsJcomponent.put("cookieSwipeButtonAddMailAccount",cookieSwipeButtonAddMailAccount);
        hsJcomponent.put("cookieSwipeButtonDeleteMailAccount", cookieSwipeButtonDeleteMailAccount);
        hsJcomponent.put("cookieSwipeButtonLogout", cookieSwipeButtonLogout);
        hsJcomponent.put("cookieSwipeButtonUpdateCSAccount", cookieSwipeButtonUpdateCSAccount);
        hsJcomponent.put("cookieSwipeButtonUpdateMailAccount", cookieSwipeButtonUpdateMailAccount);
        hsJcomponent.put("jListMail", jListMail);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
	
		//Declaration des variables de base
		/**
		 * *** Declaration des boutons ****
		 */
		cookieSwipeButtonUpdateMailAccount = new CookieSwipeButton(CookieSwipeColor.LETTER);
		cookieSwipeButtonDeleteMailAccount = new CookieSwipeButton(CookieSwipeColor.LETTER);
		cookieSwipeButtonUpdateCSAccount = new CookieSwipeButton(CookieSwipeColor.LETTER);
		cookieSwipeButtonAddMailAccount = new CookieSwipeButton(CookieSwipeColor.LETTER);
		cookieSwipeButtonLogout = new CookieSwipeButton(CookieSwipeColor.LETTER);
		cookieSwipeButtonPseudo = new CookieSwipeButton();
	
		cookieSwipeButtonLogout.setText("Deconnexion");
		cookieSwipeButtonUpdateCSAccount.setText("Modifer profil");
		cookieSwipeButtonAddMailAccount.setText("Nouvelle boite");
		cookieSwipeButtonUpdateMailAccount.setText("Modifier boite");
		cookieSwipeButtonDeleteMailAccount.setText("Supprimer boite");
	
		cookieSwipeButtonLogout.setForeground(CookieSwipeColor.BUTTON);
		cookieSwipeButtonUpdateCSAccount.setForeground(CookieSwipeColor.BUTTON);
		cookieSwipeButtonAddMailAccount.setForeground(CookieSwipeColor.BUTTON);
		cookieSwipeButtonUpdateMailAccount.setForeground(CookieSwipeColor.BUTTON);
		cookieSwipeButtonDeleteMailAccount.setForeground(CookieSwipeColor.BUTTON);
	
		cookieSwipeButtonPseudo.setText("Kae"); //Temporaire //TODO
		cookieSwipeButtonPseudo.addMouseListener(
			new MouseAdapter() {
			    
			    @Override
			    public void mousePressed(MouseEvent e) {
				if(!isMenuActivated){
				    cookieSwipeButtonLogout.setVisible(true);
				    cookieSwipeButtonUpdateCSAccount.setVisible(true);
				    cookieSwipeButtonAddMailAccount.setVisible(true);
				    cookieSwipeButtonUpdateMailAccount.setVisible(true);
				    cookieSwipeButtonDeleteMailAccount.setVisible(true);
				    
				    isMenuActivated = true;
				}else{
				    cookieSwipeButtonLogout.setVisible(false);
				    cookieSwipeButtonUpdateCSAccount.setVisible(false);
				    cookieSwipeButtonAddMailAccount.setVisible(false);
				    cookieSwipeButtonUpdateMailAccount.setVisible(false);
				    cookieSwipeButtonDeleteMailAccount.setVisible(false);
				    
				    isMenuActivated = false;
				}
	
			    }
			}
		);
//		cookieSwipeButtonPseudo.addFocusListener(
//			new FocusListener() {
//	
//			    @Override
//			    public void focusLost(FocusEvent e) {
//				cookieSwipeButtonLogout.setVisible(false);
//				cookieSwipeButtonUpdateCSAccount.setVisible(false);
//				cookieSwipeButtonAddMailAccount.setVisible(false);
//				cookieSwipeButtonUpdateMailAccount.setVisible(false);
//				cookieSwipeButtonDeleteMailAccount.setVisible(false);
//				isMenuActivated = false;
//			    }
//	
//			    @Override
//			    public void focusGained(FocusEvent e) {
//				// TODO Auto-generated method stub
//	
//			    }
//			}
//		); // A supprimer une fois le probleme reglé
	
		//lorsque l'on clique sur le background (pour faire disparaitre les boutons
		getContentPane().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
			cookieSwipeButtonLogout.setVisible(false);
			cookieSwipeButtonUpdateCSAccount.setVisible(false);
			cookieSwipeButtonAddMailAccount.setVisible(false);
			cookieSwipeButtonUpdateMailAccount.setVisible(false);
			cookieSwipeButtonDeleteMailAccount.setVisible(false);
			isMenuActivated = false;
	
		    }
	
		}
		);
		/**
		 * ********************************
		 */
	
		/**
		 * *** Delaration des sprites avec leur listener ****
		 */
		cookieSwipeButtonForward = new CookieSwipeButtonSprite();
		cookieSwipeButtonDeleteMail = new CookieSwipeButtonSprite();
		cookieSwipeButtonNewMail = new CookieSwipeButtonSprite();
		cookieSwipeButtonReply = new CookieSwipeButtonSprite();
		cookieSwipeButtonReplyToAll = new CookieSwipeButtonSprite();
		cookieSwipeButtonRefresh = new CookieSwipeButtonSprite();
		cookieSwipeButtonArchive = new CookieSwipeButtonSprite();
	
		cookieSwipeButtonForward.setText(CookieSwipeButtonSprite.FORWARD);

	
		cookieSwipeButtonDeleteMail.setText(CookieSwipeButtonSprite.DELETE);

	
		cookieSwipeButtonNewMail.setText(CookieSwipeButtonSprite.NEW);

	
		cookieSwipeButtonReply.setText(CookieSwipeButtonSprite.REPLY);

	
		cookieSwipeButtonReplyToAll.setText(CookieSwipeButtonSprite.REPLY_ALL);

		
		cookieSwipeButtonArchive.setText(CookieSwipeButtonSprite.ARCHIVE);

	
		cookieSwipeButtonRefresh.setText(CookieSwipeButtonSprite.REFRESH);

	
		//Delaration du listener en cas de changement de la taille de la fenetre
		addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
			jListMail.setFixedCellWidth(getWidth() - 45);
			jListMail.setFixedCellHeight(43);
			jScrollPane3.setPreferredSize(new Dimension(getWidth() - 258, getHeight() - 110));
			jScrollPane3.setMinimumSize(new Dimension(getWidth() - 258, getHeight() - 110));
			jScrollPane3.setMaximumSize(new Dimension(getWidth() - 258, getHeight() - 110));
			jScrollPane1.setPreferredSize(new Dimension(200, getHeight() - 110));
			jScrollPane1.setPreferredSize(new Dimension(200, getHeight() - 110));
			jScrollPane1.setMinimumSize(new Dimension(0, 0));
			validate();
			repaint();
			revalidate();
		    }
	
		    @Override
		    public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
	
		    }
	
		    @Override
		    public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
	
		    }
	
		    @Override
		    public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
	
		    }
		});
	
		/**
		 * ******** Panel avec les Sprites *********
		 */
		spritePanel = new CookieSwipePanel();
		spritePanel.setPreferredSize(new Dimension((int) spritePanel.getPreferredSize().getWidth(), 43)); //Lorsque la r�solution est petite: 40 grande: 43
	//	spritePanel.setSize((int)getPreferredSize().getWidth(), 43);
		GroupLayout spriteLayout = new GroupLayout(spritePanel);
		spritePanel.setLayout(spriteLayout);
		spriteLayout.setHorizontalGroup(
			spriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(spriteLayout.createSequentialGroup()
				.addGap(80, 80, 80)
				.addComponent(cookieSwipeButtonPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(102, 102, 102)
				.addComponent(cookieSwipeButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonNewMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonReplyToAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonArchive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			)
		);
	
		spriteLayout.setVerticalGroup(
			spriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addGroup(spriteLayout.createSequentialGroup()
				.addGroup(spriteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(cookieSwipeButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonNewMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonReplyToAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonArchive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				)
			)
		);
		/**
		 * *****************************************
		 */
	
		jListMail = new view.component.CookieSwipeList<Mail>();
	    
		cookieSwipeTreeAccountMail = new CookieSwipeTree();
	
		jScrollPane1 = new JScrollPane();
		jScrollPane3 = new JScrollPane();
	
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	
		jScrollPane1.setViewportView(cookieSwipeTreeAccountMail);
		jScrollPane3.setViewportView(jListMail);
	
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGap(15, 15, 15)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(230, 230, 230)
							.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
					)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(spritePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
						.addGroup(layout.createSequentialGroup()
							.addComponent(cookieSwipeButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(cookieSwipeButtonUpdateCSAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(cookieSwipeButtonAddMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(cookieSwipeButtonUpdateMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(cookieSwipeButtonDeleteMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
					)
				)
			)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(spritePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(cookieSwipeButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonAddMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonUpdateCSAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonUpdateMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeButtonDeleteMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				)
				.addGap(15, 15, 15)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
					.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
					.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
				)
			)
		);

    }// </editor-fold>//GEN-END:initComponents

    private void cookieSwipeButtonReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonAnswerActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonAnswerActionPerformed

    private void cookieSwipeButtonDeleteMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonDeleteMailActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonDeleteMailActionPerformed

    private void cookieSwipeButtonNewMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonNewMailActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonNewMailActionPerformed

    private void cookieSwipeButtonForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonForwardActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonForwardActionPerformed

    private void cookieSwipeButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonForwardActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonForwardActionPerformed

    private void cookieSwipeButtonReplyToAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonForwardActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonForwardActionPerformed

    private void cookieSwipeButtonArchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonForwardActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonForwardActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
	 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new MainCSFrame().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.CookieSwipeTree cookieSwipeTreeAccountMail;
    private view.component.CookieSwipeList<Mail> jListMail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private CookieSwipePanel spritePanel;
    
    private boolean isMenuActivated = true;

    private CookieSwipeButton cookieSwipeButtonPseudo;
    private CookieSwipeButton cookieSwipeButtonAddMailAccount;
    private CookieSwipeButton cookieSwipeButtonDeleteMailAccount;
    private CookieSwipeButton cookieSwipeButtonLogout;
    private CookieSwipeButton cookieSwipeButtonUpdateCSAccount;
    private CookieSwipeButton cookieSwipeButtonUpdateMailAccount;

    private CookieSwipeButtonSprite cookieSwipeButtonDeleteMail;
    private CookieSwipeButtonSprite cookieSwipeButtonForward;
    private CookieSwipeButtonSprite cookieSwipeButtonNewMail;
    private CookieSwipeButtonSprite cookieSwipeButtonReply;
    private CookieSwipeButtonSprite cookieSwipeButtonReplyToAll;
    private CookieSwipeButtonSprite cookieSwipeButtonRefresh;
    private CookieSwipeButtonSprite cookieSwipeButtonArchive;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyMailAccountDeleted(MailAccount mc) {
        new MainFrameInitializer(this).deleteMailAccountInTree(mc);
    }

    @Override
    public void notifyMailAccountAdded(MailAccount mc) {
        new MainFrameInitializer(this).addMailAccountInTree(mc);
    }

    @Override
    public void notifyMailsDeleted(MailAccount mc, List<Mail> mails) {
        System.err.println("Not supported yet.");
    }

    @Override
    public void notifyMailsAdded(MailAccount mc, List<Mail> mails) {
    	new MainFrameInitializer(this).addMailsToList(mc, mails);
    }

    @Override
    public void notifyMailListChanged(MailAccount mc) {
    	System.err.println("Not supported yet.");
    }

}
