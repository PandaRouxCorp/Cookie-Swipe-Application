package view;

import java.awt.Dimension;

import interfaces.IJFrame;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeList;

public class BlacklistCSFrame extends CookieSwipeFrame implements IJFrame{

	private JScrollPane scrollBlacklist;
	
	private view.component.CookieSwipeList<String> blacklist; // à changer si on veut autre chose qu'une chaîne de caractère
	
	private CookieSwipeButton cookieSwipeDeleteFromBlacklist;
	private CookieSwipeButton cookieSwipeAddToBlacklist;
	
	
	public BlacklistCSFrame() {
		setTitle("Liste des blacklistés");
		
		initComponents();

		this.setLocationRelativeTo(null);
        this.setVisible(true);
        setResizable(false);
        setSize(new Dimension(360, 270));
        
        hsJcomponent.put("blacklist", blacklist);
        hsJcomponent.put("cookieSwipeDeleteFromBlacklist", cookieSwipeDeleteFromBlacklist);
        hsJcomponent.put("cookieSwipeAddToBlacklist", cookieSwipeAddToBlacklist);
        
        validate();
		repaint();
		revalidate();
	}

        public void setBlacklist(String[] blacklist) {
            this.blacklist = new CookieSwipeList<>(blacklist);
        }
        
        
	
	public void initComponents(){
		cookieSwipeDeleteFromBlacklist = new CookieSwipeButtonSprite();
		cookieSwipeAddToBlacklist = new CookieSwipeButtonSprite();
		
		cookieSwipeAddToBlacklist.setText(CookieSwipeButtonSprite.BLACKLIST_ADD);
		cookieSwipeDeleteFromBlacklist.setText(CookieSwipeButtonSprite.BLACKLIST_DELETE);
		
		blacklist = new view.component.CookieSwipeList<String>();
		blacklist.setModel(new DefaultListModel<String>());
		
		scrollBlacklist = new JScrollPane();
		scrollBlacklist.setViewportView(blacklist);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGap(150, 150, 150)
						//.addComponent(cookieSwipeAddToBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeDeleteFromBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(15, 15, 15)
							.addComponent(scrollBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
						)
					)
				)
			)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					//.addComponent(cookieSwipeAddToBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(cookieSwipeDeleteFromBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(scrollBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
				)
			)
		);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
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
			    	new BlacklistCSFrame().setVisible(true);
			    }
			});
	    }

}
