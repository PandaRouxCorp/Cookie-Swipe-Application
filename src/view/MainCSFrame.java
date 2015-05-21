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
 * @author Suiken
 */
public class MainCSFrame extends CookieSwipeFrame implements IJFrame {

    HashMap<String, Object> hsJcomponent = new HashMap<String, Object>();

    /**
     * Creates new form MainCSFrame
     */
    public MainCSFrame() {
        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        hsJcomponent.put("cookieSwipeButtonAnswer", cookieSwipeButtonAnswer);
        hsJcomponent.put("cookieSwipeButtonDeleteMail", cookieSwipeButtonDeleteMail);
        hsJcomponent.put("cookieSwipeButtonForward", cookieSwipeButtonForward);
        hsJcomponent.put("cookieSwipeButtonNewMail", cookieSwipeButtonNewMail);
        hsJcomponent.put("cookieSwipeTreeAcountMail", cookieSwipeTreeAcountMail);
        hsJcomponent.put("cookieSwipeButtonAddMailAccount",cookieSwipeButtonAddMailAccount);
        hsJcomponent.put("cookieSwipeButtonDeleteMailAccount", cookieSwipeButtonDeleteMailAccount);
        hsJcomponent.put("cookieSwipeButtonLogout", cookieSwipeButtonLogout);
        hsJcomponent.put("cookieSwipeButtonUpdateCSAccount", cookieSwipeButtonUpdateCSAccount);
        hsJcomponent.put("cookieSwipeButtonUpdateMailAccount", cookieSwipeButtonUpdateMailAccount);
        hsJcomponent.put("cookieSwipeTreeAcountMail", cookieSwipeTreeAcountMail);
        hsJcomponent.put("jListMail", jListMail);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cookieSwipeTreeAcountMail = new view.component.CookieSwipeTree();
        cookieSwipeButtonAnswer = new view.component.CookieSwipeButton();
        cookieSwipeButton1 = new view.component.CookieSwipeButton();
        cookieSwipeButtonLogout = new view.component.CookieSwipeButton();
        cookieSwipeButtonUpdateCSAccount = new view.component.CookieSwipeButton();
        cookieSwipeButtonAddMailAccount = new view.component.CookieSwipeButton();
        cookieSwipeButtonUpdateMailAccount = new view.component.CookieSwipeButton();
        cookieSwipeButtonDeleteMailAccount = new view.component.CookieSwipeButton();
        cookieSwipeButtonForward = new view.component.CookieSwipeButtonSprite();
        cookieSwipeButtonDeleteMail = new view.component.CookieSwipeButtonSprite();
        cookieSwipeButtonNewMail = new view.component.CookieSwipeButtonSprite();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListMail = new view.component.CookieSwipeList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(cookieSwipeTreeAcountMail);

        cookieSwipeButtonAnswer.setText("Répondre");
        cookieSwipeButtonAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonAnswerActionPerformed(evt);
            }
        });

        cookieSwipeButton1.setText("cookieSwipeButton1");

        cookieSwipeButtonLogout.setText("Déconexion");

        cookieSwipeButtonUpdateCSAccount.setText("Modifer mon compte");

        cookieSwipeButtonAddMailAccount.setText("Nouveau compte courriel");

        cookieSwipeButtonUpdateMailAccount.setText("Modifier un compte courriel");

        cookieSwipeButtonDeleteMailAccount.setText("Supprimer un comtpe courriel");

        cookieSwipeButtonForward.setText(CookieSwipeButtonSprite.TRANSFER);
        cookieSwipeButtonForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonForwardActionPerformed(evt);
            }
        });

        cookieSwipeButtonDeleteMail.setText(CookieSwipeButtonSprite.DELETE);
        cookieSwipeButtonDeleteMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonDeleteMailActionPerformed(evt);
            }
        });

        cookieSwipeButtonNewMail.setText(CookieSwipeButtonSprite.NEW);
        cookieSwipeButtonNewMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieSwipeButtonNewMailActionPerformed(evt);
            }
        });

        jListMail.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListMail);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cookieSwipeButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(cookieSwipeButtonUpdateCSAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cookieSwipeButtonAddMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(cookieSwipeButtonNewMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)
                                .addComponent(cookieSwipeButtonAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cookieSwipeButtonUpdateMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cookieSwipeButtonDeleteMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cookieSwipeButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cookieSwipeButtonUpdateCSAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cookieSwipeButtonAddMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonUpdateMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonDeleteMailAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonNewMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonForward, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonDeleteMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cookieSwipeButtonAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonAnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonAnswerActionPerformed

    private void cookieSwipeButtonDeleteMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonDeleteMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonDeleteMailActionPerformed

    private void cookieSwipeButtonNewMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonNewMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonNewMailActionPerformed

    private void cookieSwipeButtonForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieSwipeButtonForwardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cookieSwipeButtonForwardActionPerformed

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
            java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainCSFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.CookieSwipeButton cookieSwipeButton1;
    private view.component.CookieSwipeButton cookieSwipeButtonAddMailAccount;
    private view.component.CookieSwipeButton cookieSwipeButtonAnswer;
    private view.component.CookieSwipeButtonSprite cookieSwipeButtonDeleteMail;
    private view.component.CookieSwipeButton cookieSwipeButtonDeleteMailAccount;
    private view.component.CookieSwipeButtonSprite cookieSwipeButtonForward;
    private view.component.CookieSwipeButton cookieSwipeButtonLogout;
    private view.component.CookieSwipeButtonSprite cookieSwipeButtonNewMail;
    private view.component.CookieSwipeButton cookieSwipeButtonUpdateCSAccount;
    private view.component.CookieSwipeButton cookieSwipeButtonUpdateMailAccount;
    private view.component.CookieSwipeTree cookieSwipeTreeAcountMail;
    private view.component.CookieSwipeList jListMail;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    @Override
    public HashMap<String, Object> getJComponent() {
        return hsJcomponent;
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
