package view;

import interfaces.IJFrame;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

import view.component.CookieSwipeButtonAttach;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeColor;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeLabel;
import view.component.CookieSwipePanel;
import view.component.CookieSwipeTextField;

public class MailCSFrame extends CookieSwipeFrame implements IJFrame {

	private static final long serialVersionUID = -4066001951397648825L;

	private CookieSwipeButtonAttach[] cookieSwipeButtonAttached;
    
    private CookieSwipePanel attachedFilesPanel;
    
    private CookieSwipeButtonSprite cookieSwipeButtonSend;
    private CookieSwipeButtonSprite cookieSwipeButtonAttach;
    
    private CookieSwipeLabel cookieSwipeLabelCc;
    private CookieSwipeLabel cookieSwipeLabelSubject;
    private CookieSwipeLabel cookieSwipeLabelTo;
    
    private CookieSwipeTextField cookieSwipeTextFieldSubject;
    private CookieSwipeTextField cookieSwipeTextFieldTo;
    private CookieSwipeTextField cookieSwipeTextFieldToCc;
    
    private javax.swing.JScrollPane jScrollPane1;
   
    private view.component.CookieSwipeTextArea jTextAreaMail;
    
    public MailCSFrame() {
        
    	initFrame();

    }

    public void setCookieSwipeTextFieldSubject(String cookieSwipeTextFieldSubject) {
        this.cookieSwipeTextFieldSubject.setText(cookieSwipeTextFieldSubject);
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

    private void initFrame(){
    	
    	initComponents();
    	doWhenResized();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    	
    }
    
    private void configFrame(){
    	
    	setTitle("Envoi de mail");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        
//        setExtendedState(MAXIMIZED_BOTH); //fullscreen
        setSize(720, 480);
        
    }
    
    private void initComponents() {

        cookieSwipeTextFieldTo = new view.component.CookieSwipeTextField();
        cookieSwipeTextFieldToCc = new view.component.CookieSwipeTextField();
        
        cookieSwipeLabelTo = new view.component.CookieSwipeLabel();
        cookieSwipeLabelCc = new view.component.CookieSwipeLabel();
        
        jScrollPane1 = new javax.swing.JScrollPane();
        cookieSwipeTextFieldSubject = new view.component.CookieSwipeTextField();
        cookieSwipeLabelSubject = new view.component.CookieSwipeLabel();
        jTextAreaMail = new view.component.CookieSwipeTextArea();
        
        cookieSwipeButtonSend = new view.component.CookieSwipeButtonSprite();
        cookieSwipeButtonAttach = new view.component.CookieSwipeButtonSprite();

        cookieSwipeLabelTo.setText("À :");
        cookieSwipeLabelCc.setText("Cc :");
        cookieSwipeLabelSubject.setText("Objet :");

        jTextAreaMail.setColumns(20);
        jTextAreaMail.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMail);

        cookieSwipeButtonSend.setText(CookieSwipeButtonSprite.SEND);
        cookieSwipeButtonAttach.setText(CookieSwipeButtonSprite.ATTACH);
        
        /** pour l'exemple **/
        cookieSwipeButtonAttached = new view.component.CookieSwipeButtonAttach[5];
        
        attachedFilesPanel = new view.component.CookieSwipePanel(CookieSwipeColor.BACKGROUND_FRAME);
        attachedFilesPanel.setPreferredSize(new Dimension(getWidth() - 150, getHeight() - 35));
		attachedFilesPanel.setMinimumSize(new Dimension(getWidth() - 150, getHeight() - 35));
		attachedFilesPanel.setMaximumSize(new Dimension(getWidth() - 150, getHeight() - 35));
		attachedFilesPanel.setMinimumSize(new Dimension(0, 0));
        for(int i = 0; i < cookieSwipeButtonAttached.length; i++){
        	cookieSwipeButtonAttached[i] = new CookieSwipeButtonAttach("<html><u>image " + i + "</u></html>");
        }
        for(int i = 0; i < cookieSwipeButtonAttached.length; i++){
        	attachedFilesPanel.add(cookieSwipeButtonAttached[i]);
        }
        /*******************/
        
    }
    
    private void doWhenResized(){
    	
    	addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) {
		    	
		    	cookieSwipeTextFieldToCc.setPreferredSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMinimumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMaximumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldToCc.setMinimumSize(new Dimension(0, 0));
		    	
                cookieSwipeTextFieldSubject.setPreferredSize(new Dimension(getWidth() - 150, 35));
                cookieSwipeTextFieldSubject.setMinimumSize(new Dimension(getWidth() - 150, 35));
                cookieSwipeTextFieldSubject.setMaximumSize(new Dimension(getWidth() - 150, 35));
                cookieSwipeTextFieldSubject.setMinimumSize(new Dimension(0, 0));

		    	cookieSwipeTextFieldTo.setPreferredSize(new Dimension(getWidth() - 185, 35));
		    	cookieSwipeTextFieldTo.setMinimumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldTo.setMaximumSize(new Dimension(getWidth() - 150, 35));
		    	cookieSwipeTextFieldTo.setMinimumSize(new Dimension(0, 0));
				
		    	jScrollPane1.setPreferredSize(new Dimension(getWidth() - 150, getHeight() - 200));
				jScrollPane1.setMinimumSize(new Dimension(getWidth() - 150, getHeight() - 200));
				jScrollPane1.setMaximumSize(new Dimension(getWidth() - 150, getHeight() - 200));
				jScrollPane1.setMinimumSize(new Dimension(0, 0));
				
				attachedFilesPanel.setPreferredSize(new Dimension(getWidth() - 150, getHeight() - 200));
				attachedFilesPanel.setMinimumSize(new Dimension(getWidth() - 150, getHeight() - 200));
				attachedFilesPanel.setMaximumSize(new Dimension(getWidth() - 150, getHeight() - 200));
				attachedFilesPanel.setMinimumSize(new Dimension(0, 0));
								
				refresh();
				
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
    
    private void placeComponents(){
    	
    	 GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(30, 30, 30)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(cookieSwipeLabelCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(cookieSwipeLabelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(cookieSwipeLabelSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
         		)
                 .addGap(18, 18, 18)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                         .addGap(60, 60, 60)
                     )
                     .addGroup(layout.createSequentialGroup()
                 		.addComponent(cookieSwipeTextFieldTo, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                 		.addComponent(cookieSwipeButtonAttach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(cookieSwipeButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(60, 60, 60)
                     )
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(cookieSwipeTextFieldSubject, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                         .addGap(60, 60, 60)
                     )
                     .addGroup(layout.createSequentialGroup()
                     		.addComponent(cookieSwipeTextFieldToCc, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                         .addGap(60, 60, 60)
                     )
                     .addGroup(layout.createSequentialGroup()
                     		.addComponent(cookieSwipeTextFieldSubject, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                             .addGap(60, 60, 60)
                     )
                     .addGroup(layout.createSequentialGroup()
                     		.addComponent(attachedFilesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
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
                             .addComponent(cookieSwipeLabelTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(cookieSwipeTextFieldTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(cookieSwipeButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(cookieSwipeButtonAttach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         )
                         .addGap(5, 5, 5)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                             .addComponent(cookieSwipeTextFieldToCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(cookieSwipeLabelCc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         )
                         .addGap(7, 7, 7)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                             .addComponent(cookieSwipeLabelSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(cookieSwipeTextFieldSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         )
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                             .addComponent(attachedFilesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                         )
                         .addGap(7, 7, 7)
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 2000, 2000)
                         .addGap(15, 15, 15)
                     )
                 )
                 .addContainerGap(29, Short.MAX_VALUE)
             )
         );
         
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("cookieSwipeButtonSend", cookieSwipeButtonSend);
        hsJcomponent.put("cookieSwipeButtonAttach", cookieSwipeButtonAttach);
        hsJcomponent.put("cookieSwipeLabelCc", cookieSwipeLabelCc);
        hsJcomponent.put("cookieSwipeLabelTo", cookieSwipeLabelTo);
        hsJcomponent.put("cookieSwipeTextFieldTo", cookieSwipeTextFieldTo);
        hsJcomponent.put("cookieSwipeTextFieldToCc", cookieSwipeTextFieldToCc);
        hsJcomponent.put("cookieSwipeTextFieldSubject", cookieSwipeTextFieldSubject);
        hsJcomponent.put("jTextAreaMail", jTextAreaMail);
        
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
    	    java.util.logging.Logger.getLogger(MailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (InstantiationException ex) {
    	    java.util.logging.Logger.getLogger(MailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (IllegalAccessException ex) {
    	    java.util.logging.Logger.getLogger(MailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
    	    java.util.logging.Logger.getLogger(MailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    	}

    	java.awt.EventQueue.invokeLater(new Runnable() {
    	    public void run() {
    	    	new MailCSFrame().setVisible(true);
    	    }
    	});
    	
    }

    @Override
    public void refresh() {
    	
    	validate();
        repaint();
        revalidate();
        
    }
}
