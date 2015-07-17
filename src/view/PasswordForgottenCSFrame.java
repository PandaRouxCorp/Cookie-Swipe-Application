package view;

import interfaces.IJFrame;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeLabel;
import view.component.CookieSwipeTextField;

public class PasswordForgottenCSFrame extends CookieSwipeFrame implements IJFrame {

	private static final long serialVersionUID = 1691242087514507446L;

	private CookieSwipeButton cookieSwipeButtonSendPassword;
    
    private CookieSwipeLabel cookieSwipeLabelLogin;
    private CookieSwipeLabel cookieSwipeLabelMailAddress;
    
    private CookieSwipeTextField cookieSwipeTextFieldMailAddress;
    private CookieSwipeTextField cookieSwipeTextFieldLogin;

    
    public PasswordForgottenCSFrame() {
    	
        initFrame();
        
    }

    private void initFrame(){
    	
    	initComponents();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("cookieSwipeButtonSendPassword", cookieSwipeButtonSendPassword);
        hsJcomponent.put("cookieSwipeLabelLogin", cookieSwipeLabelLogin);
        hsJcomponent.put("cookieSwipeLabelMailAddress", cookieSwipeLabelMailAddress);
        hsJcomponent.put("cookieSwipeTextFieldMailAddress", cookieSwipeTextFieldMailAddress);
        hsJcomponent.put("cookieSwipeTextFieldLogin", cookieSwipeTextFieldLogin);
        
    }
    
    private void configFrame(){
    	
    	setTitle("Mot de passe oublié");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(480, 360);
        setResizable(false);
    	
    }

    private void initComponents() {

        cookieSwipeTextFieldLogin = new CookieSwipeTextField();
        cookieSwipeTextFieldMailAddress = new CookieSwipeTextField();
        cookieSwipeLabelLogin = new CookieSwipeLabel();
        cookieSwipeLabelMailAddress = new CookieSwipeLabel();
        cookieSwipeButtonSendPassword = new CookieSwipeButton();

        cookieSwipeLabelLogin.setText("Login");
        cookieSwipeLabelMailAddress.setText("Adresse mail");
        cookieSwipeButtonSendPassword.setText("Envoyer mot de passe");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipeLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(cookieSwipeButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                            )
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(cookieSwipeLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cookieSwipeLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void cookieSwipeButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonLoginActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PasswordForgottenCSFrame().setVisible(true);
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
