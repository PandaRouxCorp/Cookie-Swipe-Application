/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import interfaces.IJFrame;
import java.util.HashMap;
import view.component.CookieSwipeButtonSprite;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class LoginJFrame extends CookieSwipeFrame implements IJFrame {

    private HashMap<String, Object> hsJComponent = new HashMap<String, Object>();

    /**
     * Creates new form LoginJFrame
     */
    public LoginJFrame() {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        hsJComponent.put("cookieSwipeButtonLogin", cookieSwipeButtonLogin);
        hsJComponent.put("cookieSwipeButtonSendLogin", cookieSwipeButtonSendLogin);
        hsJComponent.put("cookieSwipeButtonSendPassword", cookieSwipeButtonSendPassword);
        hsJComponent.put("cookieSwipeLabelLogin", cookieSwipeLabelLogin);
        hsJComponent.put("cookieSwipeLabelPassword", cookieSwipeLabelPassword);
        hsJComponent.put("cookieSwipePasswordFieldPassword", cookieSwipePasswordFieldPassword);
        hsJComponent.put("cookieSwipeTextFieldLogin", cookieSwipeTextFieldLogin);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cookieSwipeFrame1 = new view.component.CookieSwipeFrame();
        cookieSwipeFrame2 = new view.component.CookieSwipeFrame();
        cookieSwipeFrame3 = new view.component.CookieSwipeFrame();
        cookieSwipeTextFieldLogin = new view.component.CookieSwipeTextField();
        cookieSwipePasswordFieldPassword = new view.component.CookieSwipePasswordField();
        cookieSwipeLabelLogin = new view.component.CookieSwipeLabel();
        cookieSwipeLabelPassword = new view.component.CookieSwipeLabel();
        cookieSwipeButtonLogin = new view.component.CookieSwipeButton();
        cookieSwipeButtonSendLogin = new view.component.CookieSwipeButton();
        cookieSwipeButtonSendPassword = new view.component.CookieSwipeButton();

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

        cookieSwipeButtonSendLogin.setText("Mot de passe oublié");

        cookieSwipeButtonSendPassword.setText("Identifiant oublié");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(cookieSwipeButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cookieSwipeTextFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cookieSwipeButtonSendLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(cookieSwipeButtonSendPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(cookieSwipeLabelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipeTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cookieSwipeLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cookieSwipePasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonSendLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonSendPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(176, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cookieSwipeButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonLoginActionPerformed

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
    private view.component.CookieSwipeButton cookieSwipeButtonLogin;
    private view.component.CookieSwipeButton cookieSwipeButtonSendLogin;
    private view.component.CookieSwipeButton cookieSwipeButtonSendPassword;
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
    public HashMap<String, Object> getJComponent() {
        return hsJComponent;
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
