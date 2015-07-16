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
import java.util.HashMap;

import javax.swing.WindowConstants;

import view.component.CookieSwipeButtonAttach;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeColor;
import view.component.CookieSwipeFrame;

public class ReadMailCSFrame extends CookieSwipeFrame implements IJFrame {

    private HashMap<String, Object> hsJcomponents = new HashMap<String, Object>();
    
    private view.component.CookieSwipeButtonAttach[] cookieSwipeButtonAttach;
    
    private CookieSwipeButtonSprite cookieSwipeButtonReply;
    private CookieSwipeButtonSprite cookieSwipeButtonReplyToAll;
    private CookieSwipeButtonSprite cookieSwipeButtonForward;
    private CookieSwipeButtonSprite cookieSwipeButtonArchive;
    private CookieSwipeButtonSprite cookieSwipeButtonDeleteMail;
    private CookieSwipeButtonSprite cookieSwipeButtonToBlacklist;
    
    private view.component.CookieSwipePanel csPanel;

    private view.component.CookieSwipeLabel cookieSwipeLabelCc;
    private view.component.CookieSwipeLabel cookieSwipeLabelTo;
    private view.component.CookieSwipeLabel cookieSwipeLabelObject;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldObject;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldTo;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldToCc;
   
    private javax.swing.JScrollPane jScrollPane1;
   
    private view.component.CookieSwipeTextArea jTextAreaMail;

    /**
     * Creates new form MailCSFrame
     */
    public ReadMailCSFrame() {
    	setTitle("Lecture de mail");
    	initFrame();
    	
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        
//       setExtendedState(MAXIMIZED_BOTH);
        setSize(720, 480);
        
        hsJcomponents.put("cookieSwipeLabelCc", cookieSwipeLabelCc);
        hsJcomponents.put("cookieSwipeLabelTo", cookieSwipeLabelTo);
        hsJcomponents.put("cookieSwipeTextFieldTo", cookieSwipeTextFieldTo);
        hsJcomponents.put("cookieSwipeTextFieldToCc", cookieSwipeTextFieldToCc);
        hsJcomponents.put("cookieSwipeButtonReply", cookieSwipeButtonReply);
        hsJcomponents.put("cookieSwipeButtonReplyToAll", cookieSwipeButtonReplyToAll);
        hsJcomponents.put("cookieSwipeButtonForward", cookieSwipeButtonForward);
        hsJcomponents.put("cookieSwipeButtonArchive", cookieSwipeButtonArchive);
        hsJcomponents.put("cookieSwipeButtonDeleteMail", cookieSwipeButtonDeleteMail);
        hsJcomponents.put("cookieSwipeButtonToBlacklist", cookieSwipeButtonToBlacklist);
        hsJcomponents.put("cookieSwipeLabelObject", cookieSwipeLabelObject);
        
        validate();
        repaint();
        revalidate();
    }

    public void setCookieSwipeTextFieldObject(String cookieSwipeTextFieldObject) {
        this.cookieSwipeTextFieldObject.setText(cookieSwipeTextFieldObject);
    }

    public void setCookieSwipeTextFieldTo(String cookieSwipeTextFieldTo) {
        this.cookieSwipeTextFieldTo.setText(cookieSwipeTextFieldTo);
    }

    public void setCookieSwipeTextFieldToCc(String cookieSwipeTextFieldToCc) {
        this.cookieSwipeTextFieldToCc.setText(cookieSwipeTextFieldToCc);
    }

    public void setjTextAreaMail(String jTextAreaMail) {
        this.jTextAreaMail.setText(jTextAreaMail);
    }
    
    public void initFrame(){
    	initComponents();
    	doWhenResized();
    	placeComponents();
    }
    
    public void initComponents() {

        cookieSwipeButtonReply = new CookieSwipeButtonSprite();
        cookieSwipeButtonReplyToAll = new CookieSwipeButtonSprite();
        cookieSwipeButtonForward = new CookieSwipeButtonSprite();
        cookieSwipeButtonArchive = new CookieSwipeButtonSprite();
        cookieSwipeButtonDeleteMail = new CookieSwipeButtonSprite();
        cookieSwipeButtonToBlacklist = new CookieSwipeButtonSprite();
        
        cookieSwipeButtonReply.setText(CookieSwipeButtonSprite.REPLY);
        cookieSwipeButtonReplyToAll.setText(CookieSwipeButtonSprite.REPLY_ALL);
        cookieSwipeButtonForward.setText(CookieSwipeButtonSprite.FORWARD);
        cookieSwipeButtonArchive.setText(CookieSwipeButtonSprite.ARCHIVE);
        cookieSwipeButtonDeleteMail.setText(CookieSwipeButtonSprite.DELETE);
        cookieSwipeButtonToBlacklist.setText(CookieSwipeButtonSprite.BLACKLIST_ADD);
        
        // TODO 
        cookieSwipeButtonAttach = new view.component.CookieSwipeButtonAttach[5];
        for(int i = 0; i < 5; i++){
        	cookieSwipeButtonAttach[i] = new view.component.CookieSwipeButtonAttach("<html><u>image.jpg</u></html>");
        }
        csPanel = new view.component.CookieSwipePanel(CookieSwipeColor.BACKGROUND_FRAME);
        for(int i = 0; i < cookieSwipeButtonAttach.length; i++){
        	csPanel.add(cookieSwipeButtonAttach[i]);
        }
        
        
        cookieSwipeTextFieldTo = new view.component.CookieSwipeTextField();
        cookieSwipeTextFieldToCc = new view.component.CookieSwipeTextField();
        cookieSwipeTextFieldObject = new view.component.CookieSwipeTextField();
        
        cookieSwipeLabelTo = new view.component.CookieSwipeLabel();
        cookieSwipeLabelCc = new view.component.CookieSwipeLabel();
        cookieSwipeLabelObject = new view.component.CookieSwipeLabel();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMail = new view.component.CookieSwipeTextArea();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cookieSwipeLabelTo.setText("À :");
        cookieSwipeLabelCc.setText("Cc :");
        cookieSwipeLabelObject.setText("Objet :");

        jTextAreaMail.setColumns(20);
        jTextAreaMail.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMail);

    }
    
    
    public void placeComponents(){
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeLabelCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        		)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                        .addGap(60, 60, 60)
                    )
                    .addGroup(layout.createSequentialGroup()
						.addComponent(cookieSwipeButtonReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeButtonReplyToAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeButtonArchive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cookieSwipeButtonToBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
					)
                    .addGroup(layout.createSequentialGroup()
                		.addComponent(cookieSwipeTextFieldTo, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                        .addGap(60, 60, 60)
                    )
                    .addGroup(layout.createSequentialGroup()
                    		.addComponent(cookieSwipeTextFieldToCc, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                        .addGap(60, 60, 60)
                    )
                    .addGroup(layout.createSequentialGroup()
                    		.addComponent(cookieSwipeTextFieldObject, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                            .addGap(60, 60, 60)
                    )
                    .addGroup(layout.createSequentialGroup()
                    		.addComponent(csPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                            .addGap(60, 60, 60)
                    )
        		)
                .addContainerGap(103, Short.MAX_VALUE)
    		)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(layout.createSequentialGroup()
                		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        						.addComponent(cookieSwipeButtonReply, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addComponent(cookieSwipeButtonReplyToAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addComponent(cookieSwipeButtonArchive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addComponent(cookieSwipeButtonToBlacklist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    					)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cookieSwipeLabelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cookieSwipeTextFieldToCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cookieSwipeLabelObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    		.addComponent(csPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                        .addGap(15, 15, 15)
                    )
                )
                .addContainerGap(29, Short.MAX_VALUE)
            )
        );
    }
    
    public void doWhenResized(){
    	addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		    	cookieSwipeTextFieldToCc.setPreferredSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMinimumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMaximumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMinimumSize(new Dimension(0, 0));
		    	
		    	cookieSwipeTextFieldObject.setPreferredSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldObject.setMinimumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldObject.setMaximumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldObject.setMinimumSize(new Dimension(0, 0));
		    	
		    	cookieSwipeTextFieldTo.setPreferredSize(new Dimension(getWidth() - 185, 35));
		    	cookieSwipeTextFieldTo.setMinimumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldTo.setMaximumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldTo.setMinimumSize(new Dimension(0, 0));
				
		    	jScrollPane1.setPreferredSize(new Dimension(getWidth() - 150, getHeight() - 200));
				jScrollPane1.setMinimumSize(new Dimension(getWidth() - 150, getHeight() - 200));
				jScrollPane1.setMaximumSize(new Dimension(getWidth() - 150, getHeight() - 200));
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
    }

    public static void main(String args[]) {
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

    	java.awt.EventQueue.invokeLater(new Runnable() {
    	    public void run() {
    	    	new ReadMailCSFrame().setVisible(true);
    	    }
    	});
    }

    @Override
    public HashMap<String, Object> getJComponent() {
        return hsJcomponents;
    }

    @Override
    public void refresh() {
    	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
