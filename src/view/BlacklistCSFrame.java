package view;

import cookie.swipe.application.CookieSwipeApplication;
import interfaces.IJFrame;
import java.awt.Component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;

import view.component.CookieSwipeButton;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeList;

public class BlacklistCSFrame extends CookieSwipeFrame implements IJFrame{

	private static final long serialVersionUID = -4844235581189535802L;

	private JScrollPane scrollBlacklist;
	
	private CookieSwipeList<String> blacklist; // à changer si on veut autre chose qu'une chaîne de caractère
        private String[] tableBlacklist;
        DefaultListModel<String> model;
	
	private CookieSwipeButton cookieSwipeDeleteFromBlacklist;
	private CookieSwipeButton cookieSwipeAddToBlacklist;
	
	public BlacklistCSFrame() {
                List<String> bl = CookieSwipeApplication.getApplication().getUser().getBlackList();
                tableBlacklist = bl.toArray(new String [bl.size()]);
                
		initFrame();
	}

    public CookieSwipeList<String> getBlacklist() {
        return blacklist;
    }

    private void initFrame(){
    	
    	initComponents();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    	
    }
    
    private void configFrame(){
    	
    	setTitle("Liste des blacklistés");
    	
    	setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false); 
        setSize(new Dimension(360, 270));
        
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("blacklist", blacklist);
        hsJcomponent.put("cookieSwipeDeleteFromBlacklist", cookieSwipeDeleteFromBlacklist);
        hsJcomponent.put("cookieSwipeAddToBlacklist", cookieSwipeAddToBlacklist);
        
    }
    
	private void initComponents(){
		
		cookieSwipeDeleteFromBlacklist = new CookieSwipeButtonSprite();
		cookieSwipeAddToBlacklist = new CookieSwipeButtonSprite();
		
		cookieSwipeAddToBlacklist.setText(CookieSwipeButtonSprite.BLACKLIST_ADD);
		cookieSwipeDeleteFromBlacklist.setText(CookieSwipeButtonSprite.BLACKLIST_DELETE);
		
                
                model = new DefaultListModel<>();
                for(String element : tableBlacklist) {
                    model.addElement(element);
                }
		blacklist = new CookieSwipeList<>(model);
                
		blacklist.addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        CookieSwipeApplication.getApplication().setParam("blackListedElementSelected", blacklist.getSelectedValue());
                    }
                });
                
                cookieSwipeDeleteFromBlacklist.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.removeElement(blacklist.getSelectedValue());
                    }
                });
                
		scrollBlacklist = new JScrollPane();
		scrollBlacklist.setViewportView(blacklist);
		
	}

	private void placeComponents(){
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(layout.createSequentialGroup()
						.addGap(150, 150, 150)
						.addComponent(cookieSwipeDeleteFromBlacklist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(15, 15, 15)
							.addComponent(scrollBlacklist, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
						)
					)
				)
			)
		);
		
		layout.setVerticalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(cookieSwipeDeleteFromBlacklist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(scrollBlacklist, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
				)
			)
		);
		
	}
	
	@Override
	public void refresh() {
		
		validate();
		repaint();
		revalidate();
		
	}
	
	public static void main(String args[]) {
		
		try {
		    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
				    UIManager.setLookAndFeel(info.getClassName());
				    break;
				}
		    }
		} catch (ClassNotFoundException ex) {
		    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
		    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
		    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
		    java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
		    public void run() {
		    	new BlacklistCSFrame().setVisible(true);
		    }
		});
    }
	
}
