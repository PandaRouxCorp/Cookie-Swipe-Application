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

public class LoginForgottenCSFrame extends CookieSwipeFrame implements IJFrame {

	private static final long serialVersionUID = 4729753654116728888L;

	private CookieSwipeButton cookieSwipeButtonSendLogin;
    
    private CookieSwipeLabel cookieSwipeLabelMailAddress;
    
    private CookieSwipeTextField cookieSwipeTextFieldMailAddress;
    

    public LoginForgottenCSFrame() {

        initFrame();
        
    }
    
    private void initFrame(){
    	
    	initComponents();
    	placeComponents();
    	
    	putComponents();
    	configFrame();
    	
    	refresh();
    	
    }
    
    private void configFrame(){
    	
    	setTitle("Login oublié");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(360, 240);
        setResizable(false);
        
    }
    
    private void putComponents(){
    	
    	hsJcomponent.put("cookieSwipeButtonSendLogin", cookieSwipeButtonSendLogin);
        hsJcomponent.put("cookieSwipeLabelLogin", cookieSwipeLabelMailAddress);
        hsJcomponent.put("cookieSwipeTextFieldMailAddress", cookieSwipeTextFieldMailAddress);
        
    }

    private void initComponents() {

        cookieSwipeTextFieldMailAddress = new view.component.CookieSwipeTextField();
        cookieSwipeLabelMailAddress = new view.component.CookieSwipeLabel();
        cookieSwipeButtonSendLogin = new view.component.CookieSwipeButton();

        cookieSwipeLabelMailAddress.setText("Adresse mail");
        cookieSwipeButtonSendLogin.setText("Envoyer login");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipeLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, 154, Short.MAX_VALUE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cookieSwipeButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(cookieSwipeLabelMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldMailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void cookieSwipeButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
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
                new LoginForgottenCSFrame().setVisible(true);
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