/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sun.media.sound.Toolkit;

import interfaces.IJFrame;
import view.component.CookieSwipeColor;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipePanel;

/**
 *
 * @author Lucas
 */
public class LoginJFrame extends CookieSwipeFrame implements IJFrame {

    /**
     * Creates new form LoginJFrame
     */
    public LoginJFrame() {
    	setTitle("Login");

        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(720, 480);
        setResizable(false);
        
        hsJcomponent.put("cookieSwipeButtonLogin", cookieSwipeButtonLogin);
        hsJcomponent.put("cookieSwipeButtonInscription", cookieSwipeButtonInscription);
        hsJcomponent.put("cookieSwipeButtonSendLogin", cookieSwipeButtonSendLogin);
        hsJcomponent.put("cookieSwipeButtonSendPassword", cookieSwipeButtonSendPassword);
        hsJcomponent.put("cookieSwipeLabelLogin", cookieSwipeLabelLogin);
        hsJcomponent.put("cookieSwipeLabelPassword", cookieSwipeLabelPassword);
        hsJcomponent.put("cookieSwipePasswordFieldPassword", cookieSwipePasswordFieldPassword);
        hsJcomponent.put("cookieSwipeTextFieldLogin", cookieSwipeTextFieldLogin);
        
        cookieSwipeButtonLogin.setPreferredSize(cookieSwipeButtonInscription.getSize());
        
        
        validate();
        repaint();
        revalidate();
        
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cookieSwipeFrame1 = new view.component.CookieSwipeFrame();
        cookieSwipeFrame2 = new view.component.CookieSwipeFrame();
        cookieSwipeFrame3 = new view.component.CookieSwipeFrame();
        cookieSwipeTextFieldLogin = new view.component.CookieSwipeTextField();
        cookieSwipePasswordFieldPassword = new view.component.CookieSwipePasswordField();
        cookieSwipeLabelLogin = new view.component.CookieSwipeLabel();
        cookieSwipeLabelPassword = new view.component.CookieSwipeLabel();
        cookieSwipeButtonSendLogin = new view.component.CookieSwipeButtonAttach();
        cookieSwipeButtonSendPassword = new view.component.CookieSwipeButtonAttach();
        cookieSwipeButtonInscription = new view.component.CookieSwipeButton();
        cookieSwipeButtonLogin = new view.component.CookieSwipeButton();

        
       	CookieSwipePanel logoPanel = new CookieSwipePanel(new File("cookieSwipe.png"));
       	logoPanel.setBackground(CookieSwipeColor.BACKGROUND_FRAME);
       	
       	
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cookieSwipeFrame1Layout = new javax.swing.GroupLayout(cookieSwipeFrame1.getContentPane());
        cookieSwipeFrame1.getContentPane().setLayout(cookieSwipeFrame1Layout);
        cookieSwipeFrame1Layout.setHorizontalGroup(
            cookieSwipeFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        cookieSwipeFrame1Layout.setVerticalGroup(
            cookieSwipeFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cookieSwipeFrame2Layout = new javax.swing.GroupLayout(cookieSwipeFrame2.getContentPane());
        cookieSwipeFrame2.getContentPane().setLayout(cookieSwipeFrame2Layout);
        cookieSwipeFrame2Layout.setHorizontalGroup(
            cookieSwipeFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        cookieSwipeFrame2Layout.setVerticalGroup(
            cookieSwipeFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout cookieSwipeFrame3Layout = new javax.swing.GroupLayout(cookieSwipeFrame3.getContentPane());
        cookieSwipeFrame3.getContentPane().setLayout(cookieSwipeFrame3Layout);
        cookieSwipeFrame3Layout.setHorizontalGroup(
            cookieSwipeFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        cookieSwipeFrame3Layout.setVerticalGroup(
            cookieSwipeFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            		.addGroup(layout.createSequentialGroup()
        				.addGap(220, 220, 220)
        				.addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    				)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(280, 280, 280)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cookieSwipeButtonLogin,  javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGap(20, 20, 20)
                                .addComponent(cookieSwipeButtonInscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        )
                    )
                    
                    .addGroup(layout.createSequentialGroup()
                		.addGap(480, 480, 480)
                		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            				.addGroup(layout.createSequentialGroup()
        						.addComponent(cookieSwipeButtonSendLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        						.addGap(20, 20, 20)
        						.addComponent(cookieSwipeButtonSendPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    						)
                        )
                    )
                )
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            	.addGap(70, 70, 70)
        		.addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cookieSwipeLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonInscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        		.addComponent(cookieSwipeButtonSendLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(cookieSwipeButtonSendPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
            )
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cookieSwipeButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonLoginActionPerformed
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Image logo;
    private view.component.CookieSwipeButton cookieSwipeButtonInscription;
    private view.component.CookieSwipeButton cookieSwipeButtonLogin;
    private view.component.CookieSwipeButtonAttach cookieSwipeButtonSendLogin; //pour le style souligné du bouton
    private view.component.CookieSwipeButtonAttach cookieSwipeButtonSendPassword;
    private view.component.CookieSwipeFrame cookieSwipeFrame1;
    private view.component.CookieSwipeFrame cookieSwipeFrame2;
    private view.component.CookieSwipeFrame cookieSwipeFrame3;
    private view.component.CookieSwipeLabel cookieSwipeLabelLogin;
    private view.component.CookieSwipeLabel cookieSwipeLabelPassword;
    private view.component.CookieSwipePasswordField cookieSwipePasswordFieldPassword;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldLogin;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void paintComponents(Graphics g){
    	super.paintComponents(g);
    	
    	g.drawImage(logo, 0, 0, null);
    }
}
