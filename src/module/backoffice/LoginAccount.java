/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import controller.Dispatcher;
import interfaces.IActionBackOffice;
import java.util.HashMap;
import model.User;
import view.MainCSFrame;

/**
 *
 * @author Lucas
 */
public class LoginAccount implements IActionBackOffice {

    private User user;
    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJcomponent;

    private LoginAccount() {
    }

    public static LoginAccount getInstance() {
        return LoginAccountHolder.INSTANCE;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJcomponent = hsJComponant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean execute() {
        
        return true;
    }

    private static class LoginAccountHolder {

        private static final LoginAccount INSTANCE = new LoginAccount();
    }
}
