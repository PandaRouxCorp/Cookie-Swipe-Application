package view;

import interfaces.IJFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.component.CookieSwipeButton;
import view.component.CookieSwipeButtonAttach;
import view.component.CookieSwipeColor;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeLabel;
import view.component.CookieSwipePanel;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;

public class LoginJFrame extends CookieSwipeFrame implements IJFrame {

	private static final long serialVersionUID = -1283545032999719731L;

	private CookieSwipePanel logoPanel;
    
    private CookieSwipeButton cookieSwipeButtonInscription;
    private CookieSwipeButton cookieSwipeButtonLogin;
    
    private CookieSwipeButtonAttach cookieSwipeButtonSendLogin; //pour le style souligné du bouton
    private CookieSwipeButtonAttach cookieSwipeButtonSendPassword;
    
    private CookieSwipeLabel cookieSwipeLabelLogin;
    private CookieSwipeLabel cookieSwipeLabelPassword;
    
    private CookieSwipePasswordField cookieSwipePasswordFieldPassword;
    
    private CookieSwipeTextField cookieSwipeTextFieldLogin;
    
    
    public LoginJFrame() {
    	
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
    	
    	hsJcomponent.put("cookieSwipeButtonLogin", cookieSwipeButtonLogin);
        hsJcomponent.put("cookieSwipeButtonInscription", cookieSwipeButtonInscription);
        hsJcomponent.put("cookieSwipeButtonSendLogin", cookieSwipeButtonSendLogin);
        hsJcomponent.put("cookieSwipeButtonSendPassword", cookieSwipeButtonSendPassword);
        hsJcomponent.put("cookieSwipeLabelLogin", cookieSwipeLabelLogin);
        hsJcomponent.put("cookieSwipeLabelPassword", cookieSwipeLabelPassword);
        hsJcomponent.put("cookieSwipePasswordFieldPassword", cookieSwipePasswordFieldPassword);
        hsJcomponent.put("cookieSwipeTextFieldLogin", cookieSwipeTextFieldLogin);
        
    }
    
    private void configFrame(){
    	
    	setTitle("Login");
    	
    	setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(720, 480);
        setResizable(false);
        
        cookieSwipeButtonLogin.setPreferredSize(cookieSwipeButtonInscription.getSize());
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	setLocation((dim.width / 2) - (getSize().width/2), (dim.height / 2) - (getSize().height / 2));
    	
    }

    private void initComponents() {

        cookieSwipeTextFieldLogin = new view.component.CookieSwipeTextField();
        cookieSwipePasswordFieldPassword = new view.component.CookieSwipePasswordField();
        cookieSwipeLabelLogin = new view.component.CookieSwipeLabel();
        cookieSwipeLabelPassword = new view.component.CookieSwipeLabel();
        cookieSwipeButtonSendLogin = new view.component.CookieSwipeButtonAttach();
        cookieSwipeButtonSendPassword = new view.component.CookieSwipeButtonAttach();
        cookieSwipeButtonInscription = new view.component.CookieSwipeButton();
        cookieSwipeButtonLogin = new view.component.CookieSwipeButton();

        
       	logoPanel = new CookieSwipePanel(new File("cookieSwipe.png"));
       	logoPanel.setBackground(CookieSwipeColor.BACKGROUND_FRAME);
       	
        cookieSwipeLabelLogin.setText("Login");

        cookieSwipeLabelPassword.setText("Password");

        cookieSwipeButtonLogin.setText("Connexion");
        cookieSwipeButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonLoginActionPerformed(evt);
            }
        });

        cookieSwipeButtonSendPassword.setText("<html><u>Mot de passe oublié</u></html>");

        cookieSwipeButtonSendLogin.setText("<html><u>Identifiant oublié</u></html>");

        cookieSwipeButtonInscription.setText("Inscription");

    }
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            		.addGroup(layout.createSequentialGroup()
        				.addGap(220, 220, 220)
        				.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipeLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipePasswordFieldPassword, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cookieSwipeButtonLogin,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGap(20, 20, 20)
                                .addComponent(cookieSwipeButtonInscription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                    
                    .addGroup(layout.createSequentialGroup()
                		.addGap(480, 480, 480)
                		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            				.addGroup(layout.createSequentialGroup()
        						.addComponent(cookieSwipeButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addGap(20, 20, 20)
        						.addComponent(cookieSwipeButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    						)
                        )
                    )
                )
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            	.addGap(70, 70, 70)
        		.addComponent(logoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cookieSwipeLabelLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cookieSwipeLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipePasswordFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonInscription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        		.addComponent(cookieSwipeButtonSendLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                .addComponent(cookieSwipeButtonSendPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            )
        );
        
    }

    private void cookieSwipeButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {
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
                new LoginJFrame().setVisible(true);
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
