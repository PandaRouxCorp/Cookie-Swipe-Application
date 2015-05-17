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

/**
 *
 * @author Lucas
 */
public class DisconectAccount implements IActionBackOffice {

    private User user;
    private HashMap<String, Object> hsJcomponent;

    private DisconectAccount() {
    }

    public static DisconectAccount getInstance() {
        return DisconectAccountHolder.INSTANCE;
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
    public boolean execute(Object ... object) {
        System.exit(0);
        return true;
    }

    private static class DisconectAccountHolder {

        private static final DisconectAccount INSTANCE = new DisconectAccount();
    }
}
