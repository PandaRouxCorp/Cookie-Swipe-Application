package view;

import interfaces.IJFrame;

import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeLabel;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;

public class AccountCSFrame extends CookieSwipeFrame implements IJFrame{

	private static final long serialVersionUID = -1405750467485829551L;
	
	private CookieSwipeButton cookieSwipeButtonCancel;
    private CookieSwipeButton cookieSwipeButtonValidate;
   
    private CookieSwipeLabel cookieSwipeLabelBackupMail;
    private CookieSwipeLabel cookieSwipeLabelLoginAdressMail;
    private CookieSwipeLabel cookieSwipeLabelPassword;
   
    private CookieSwipePasswordField cookieSwipePasswordFieldPassword;
   
    private CookieSwipeTextField cookieSwipeTextFieldBackupMail;
    private CookieSwipeTextField cookieSwipeTextFieldLoginAdressMail;

   
    public AccountCSFrame() {
    	
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
    	
    	setTitle("Gestion de compte Cookie Swipe");
    	
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
    }
    
    private void putComponents(){
    	
   	 	hsJcomponent.put("cookieSwipeButtonCancel", cookieSwipeButtonCancel);
        hsJcomponent.put("cookieSwipeButtonValidate", cookieSwipeButtonValidate);
        hsJcomponent.put("cookieSwipeLabelBackupMail", cookieSwipeLabelBackupMail);
        hsJcomponent.put("cookieSwipeLabelLoginAdressMail", cookieSwipeLabelLoginAdressMail);
        hsJcomponent.put("cookieSwipeLabelPassword", cookieSwipeLabelPassword);
        hsJcomponent.put("cookieSwipePasswordFieldPassword", cookieSwipePasswordFieldPassword);
        hsJcomponent.put("cookieSwipeTextFieldBackupMail", cookieSwipeTextFieldBackupMail);
        hsJcomponent.put("cookieSwipeTextFieldLoginAdressMail", cookieSwipeTextFieldLoginAdressMail);
        
   }

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
    
    private void placeComponents(){
    	
    	GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(cookieSwipeLabelLoginAdressMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelBackupMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonValidate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cookieSwipePasswordFieldPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldBackupMail, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cookieSwipeTextFieldLoginAdressMail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                    )
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(cookieSwipeButtonCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    )
                )
                .addContainerGap(137, Short.MAX_VALUE)
            )
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeLabelLoginAdressMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeTextFieldLoginAdressMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipePasswordFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cookieSwipeTextFieldBackupMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeLabelBackupMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cookieSwipeButtonValidate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cookieSwipeButtonCancel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addContainerGap(84, Short.MAX_VALUE)
            )
        );
        
    }
    
    private void cookieSwipeButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void cookieSwipeButtonValidateActionPerformed(java.awt.event.ActionEvent evt) {
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
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountCSFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountCSFrame().setVisible(true);
            }
        });
        
    }

    @Override
    public void refresh() {
    	
        repaint();
        pack();
        
    }
}
