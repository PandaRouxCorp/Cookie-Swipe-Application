/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import interfaces.IJFrame;
import java.util.HashMap;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class AccountCSFrame extends CookieSwipeFrame implements IJFrame{

    private HashMap<String, Object> hsJcomponents = new HashMap<String, Object>();
    
    private view.component.CookieSwipeButton cookieSwipeButtonCancel;
    private view.component.CookieSwipeButton cookieSwipeButtonValidate;
    private view.component.CookieSwipeLabel cookieSwipeLabelBackupMail;
    private view.component.CookieSwipeLabel cookieSwipeLabelLoginAdressMail;
    private view.component.CookieSwipeLabel cookieSwipeLabelPassword;
    private view.component.CookieSwipePasswordField cookieSwipePasswordFieldPassword;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldBackupMail;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldLoginAdressMail;

    public AccountCSFrame() {
    	setTitle("Gestion de compte Cookie Swipe");
    	
        initFrame();
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        setResizable(false);
        
        hsJcomponents.put("cookieSwipeButtonCancel", cookieSwipeButtonCancel);
        hsJcomponents.put("cookieSwipeButtonValidate", cookieSwipeButtonValidate);
        hsJcomponents.put("cookieSwipeLabelBackupMail", cookieSwipeLabelBackupMail);
        hsJcomponents.put("cookieSwipeLabelLoginAdressMail", cookieSwipeLabelLoginAdressMail);
        hsJcomponents.put("cookieSwipeLabelPassword", cookieSwipeLabelPassword);
        hsJcomponents.put("cookieSwipePasswordFieldPassword", cookieSwipePasswordFieldPassword);
        hsJcomponents.put("cookieSwipeTextFieldBackupMail", cookieSwipeTextFieldBackupMail);
        hsJcomponents.put("cookieSwipeTextFieldLoginAdressMail", cookieSwipeTextFieldLoginAdressMail);
        
        this.pack();
    }
    
    private void initFrame(){
    	initComponents();
    	placeComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        cookieSwipeLabelLoginAdressMail = new view.component.CookieSwipeLabel();
        cookieSwipeTextFieldLoginAdressMail = new view.component.CookieSwipeTextField();
        cookieSwipePasswordFieldPassword = new view.component.CookieSwipePasswordField();
        cookieSwipeTextFieldBackupMail = new view.component.CookieSwipeTextField();
        cookieSwipeLabelPassword = new view.component.CookieSwipeLabel();
        cookieSwipeLabelBackupMail = new view.component.CookieSwipeLabel();
        cookieSwipeButtonValidate = new view.component.CookieSwipeButton();
        cookieSwipeButtonCancel = new view.component.CookieSwipeButton();

        cookieSwipeLabelLoginAdressMail.setText("Identifiant CookieSwipe :");

        cookieSwipeLabelPassword.setText("Mot de passe :");

        cookieSwipeLabelBackupMail.setText("Courriel de secours :");

        cookieSwipeButtonValidate.setText("Valider");
        cookieSwipeButtonValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonValidateActionPerformed(evt);
            }
        });

        cookieSwipeButtonCancel.setText("Annuler");
        cookieSwipeButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonCancelActionPerformed(evt);
            }
        });

    }
    
    public void placeComponents(){
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cookieSwipeLabelLoginAdressMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelBackupMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldBackupMail, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldLoginAdressMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(cookieSwipeButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeLabelLoginAdressMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeTextFieldLoginAdressMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeTextFieldBackupMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelBackupMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );
    }

    private void cookieSwipeButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonCancelActionPerformed
        // TODO add your handling code here:
    }
    private void cookieSwipeButtonValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonValidateActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountCSFrame().setVisible(true);
            }
        });
    }

    @Override
    public HashMap<String, Object> getJComponent() {
        return hsJcomponents;
    }

    @Override
    public void refresh() {
        this.repaint();
        this.pack();
    }
}
