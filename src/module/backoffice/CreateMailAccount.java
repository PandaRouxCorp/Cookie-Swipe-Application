/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.backoffice;

import controller.Dispatcher;
import interfaces.IActionBackOffice;
import interfaces.IJFrame;
import java.util.HashMap;
import javax.swing.JTextField;
import model.User;

/**
 *
 * @author Lucas
 */
public class CreateMailAccount implements IActionBackOffice{
    
    private User user;
    private HashMap<String, Object> hsJCompment;
    private CreateMailAccount() {
    }
    
    public static CreateMailAccount getInstance() {
        return createMailAccountHolder.INSTANCE;
    }

    @Override
    public void execute() {
        user.addNewMailAccount(null);
        JTextField result = (JTextField) hsJCompment.get("jTextFieldMailAdress");
        System.err.println(result.getText());
        result = (JTextField) hsJCompment.get("jTextFieldMailPassword");
        System.err.println(result.getText());
        result = (JTextField) hsJCompment.get("jTextFieldNameMailAccount");
        System.err.println(result.getText());
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJCompment) {
        this.hsJCompment = hsJCompment;
    }
    
    private static class createMailAccountHolder {

        private static final CreateMailAccount INSTANCE = new CreateMailAccount();
    }
}
