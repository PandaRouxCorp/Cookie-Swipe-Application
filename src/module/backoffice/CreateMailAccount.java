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
import javax.mail.Address;
import javax.swing.JOptionPane;
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

        String CSName = ((JTextField) hsJCompment.get("jTextFieldNameMailAccount")).getText();
        String mailAdress = ((JTextField) hsJCompment.get("jTextFieldMailAdress")).getText();
        String password = ((JTextField) hsJCompment.get("jTextFieldMailPassword")).getText();
        if(user.addNewMailAccount(CSName, mailAdress, password)){
            new JOptionPane().showMessageDialog(null, "Votre compte mail à bien été ajouté", 
                    "Ajout d'un nouveau compte mail", JOptionPane.INFORMATION_MESSAGE);
        }else{
            new JOptionPane().showMessageDialog(null, "Une erreur est survenue lors de l'ajout de votre compte mail", 
                    "Ajout d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);
        }
        for(Address a : user.getListOfMailAccount()){
            System.err.println(a);
        }
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
