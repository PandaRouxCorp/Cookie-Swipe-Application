/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm.backoffice;

import controller.Dispatcher;
import interfaces.IActionBackOffice;
import interfaces.IJFrame;
import javax.swing.JTextField;
import model.User;

/**
 *
 * @author Lucas
 */
public class CreateMailAccount implements IActionBackOffice{
    
    private User user;
    private IJFrame frame;
    private CreateMailAccount() {
    }
    
    public static CreateMailAccount getInstance() {
        return createMailAccountHolder.INSTANCE;
    }

    @Override
    public void execute() {
        //user.addNewMailAccount(null);
        JTextField result = (JTextField) frame.getJComponent().get("jTextFieldMailAdress");
                System.err.println(result.getText());
                result = (JTextField) frame.getJComponent().get("jTextFieldMailPassword");
                System.err.println(result.getText());
                result = (JTextField) frame.getJComponent().get("jTextFieldNameMailAccount");
                System.err.println(result.getText());
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setFrame(IJFrame frame) {
        this.frame = frame;
    }
    
    private static class createMailAccountHolder {

        private static final CreateMailAccount INSTANCE = new CreateMailAccount();
    }
}
