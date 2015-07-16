/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.HashMap;

import interfaces.IJFrame;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class AcountMailCSFrame extends CookieSwipeFrame implements IJFrame {

    private view.component.CookieSwipeButton cookieSwipeButtonCancel;
    private view.component.CookieSwipeButton cookieSwipeButtonValidate;
    private view.component.CookieSwipeLabel cookieSwipeLabel1;
    private view.component.CookieSwipeLabel cookieSwipeLabelMailAddress;
    private view.component.CookieSwipeLabel cookieSwipeLabelNameAcountMail;
    private view.component.CookieSwipeLabel cookieSwipeLabelPasswordAccountMail;
    private view.component.CookieSwipePasswordField cookieSwipePasswordFieldPasswordAccountMail;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldMailAddress;
    private view.component.CookieSwipeTextField cookieSwipeTextFieldNameAcountMail;
    private javax.swing.JComboBox jComboBoxColor;
    
    public AcountMailCSFrame() {
    	setTitle("Ajout de compte mail");
    	
        initComponents();
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true); 
        setResizable(false);
        
        hsJcomponent.put("cookieSwipeButtonCancel", cookieSwipeButtonCancel);
        hsJcomponent.put("cookieSwipeButtonValidate", cookieSwipeButtonValidate);
        hsJcomponent.put("cookieSwipeLabelMailAddress", cookieSwipeLabelMailAddress);
        hsJcomponent.put("cookieSwipeLabelNameAcountMail", cookieSwipeLabelNameAcountMail);
        hsJcomponent.put("cookieSwipeLabelPasswordAccountMail", cookieSwipeLabelPasswordAccountMail);
        hsJcomponent.put("cookieSwipePasswordFieldPasswordAccountMail", cookieSwipePasswordFieldPasswordAccountMail);
        hsJcomponent.put("cookieSwipeTextFieldMailAddress", cookieSwipeTextFieldMailAddress);
        hsJcomponent.put("cookieSwipeTextFieldNameAcountMail", cookieSwipeTextFieldNameAcountMail);
        hsJcomponent.put("jComboBoxColor", jComboBoxColor);

    }
    
    private void initFrame(){
    	initComponents();
    	placeComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        cookieSwipeLabelNameAcountMail = new view.component.CookieSwipeLabel();
        cookieSwipeLabelMailAddress = new view.component.CookieSwipeLabel();
        cookieSwipeLabelPasswordAccountMail = new view.component.CookieSwipeLabel();
        cookieSwipeTextFieldNameAcountMail = new view.component.CookieSwipeTextField();
        cookieSwipeTextFieldMailAddress = new view.component.CookieSwipeTextField();
        cookieSwipePasswordFieldPasswordAccountMail = new view.component.CookieSwipePasswordField();
        cookieSwipeButtonValidate = new view.component.CookieSwipeButton();
        cookieSwipeButtonCancel = new view.component.CookieSwipeButton();
        jComboBoxColor = new javax.swing.JComboBox();
        cookieSwipeLabel1 = new view.component.CookieSwipeLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cookieSwipeLabelNameAcountMail.setText("Nom de la boite courriel :");

        cookieSwipeLabelMailAddress.setText("Adresse courriel :");

        cookieSwipeLabelPasswordAccountMail.setText("Mot de passe :");

        cookieSwipeTextFieldNameAcountMail.setText("xyz");
        cookieSwipeTextFieldNameAcountMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeTextFieldNameAcountMailActionPerformed(evt);
            }
        });

        cookieSwipeTextFieldMailAddress.setText("xyz@mail.com");
        cookieSwipeTextFieldMailAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeTextFieldMailAddressActionPerformed(evt);
            }
        });

        cookieSwipeButtonValidate.setText("Valider");

        cookieSwipeButtonCancel.setText("Annuler");

        jComboBoxColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"Aucune", "Bleu", "Rouge", "Vert", "Jaune" }));

        cookieSwipeLabel1.setText("Couleur :");

    }
    
    private void placeComponents(){
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cookieSwipeLabelNameAcountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelMailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabelPasswordAccountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cookieSwipeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipeTextFieldNameAcountMail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldMailAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipePasswordFieldPasswordAccountMail, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jComboBoxColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(cookieSwipeButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125)
                        .addComponent(cookieSwipeButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeTextFieldNameAcountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelNameAcountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeTextFieldMailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelMailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipePasswordFieldPasswordAccountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelPasswordAccountMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonValidate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
    }

    private void cookieSwipeTextFieldNameAcountMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeTextFieldNameAcountMailActionPerformed
        // TODO add your handling code here:
    }

    private void cookieSwipeTextFieldMailAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeTextFieldMailAddressActionPerformed
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
            java.util.logging.Logger.getLogger(AcountMailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcountMailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcountMailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcountMailCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcountMailCSFrame().setVisible(true);
            }
        });
    }

    @Override
    public void refresh() {
        this.repaint();
        this.pack();
    }
}
