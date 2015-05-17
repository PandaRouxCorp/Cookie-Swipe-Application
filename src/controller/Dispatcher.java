/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IActionBackOffice;
import interfaces.IActionIHM;
import interfaces.IJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import model.User;
import module.ihm.InitAddMailAccount;
import module.ihm.InitMainFrame;
import module.ihm.InitUpdateMailAccount;
import module.backoffice.CreateMailAccount;
import module.backoffice.DisconectAccount;
import module.backoffice.LoginAccount;
import module.ihm.InitLoginFrame;
import module.ihm.InitUpdateAccountCS;
import module.ihm.InitWriteMail;
import view.AccountCSFrame;
import view.AcountMailCSFrame;
import view.LoginJFrame;
import view.MailCSFrame;
import view.MainCSFrame;

/**
 * Classe permetant d'attribuer les action déclancher par l'interface
 * utilisateur à des traitements Doit être un singleton
 *
 * @author Mary
 */
public class Dispatcher {

    private IJFrame mainFrame, focusFrame;
    private User user;
    private HashMap<String, Object> param = new HashMap<String, Object>();

    public IJFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(IJFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public IJFrame getFocusFrame() {
        return focusFrame;
    }

    public void setFocusFrame(IJFrame focusFrame) {
        this.focusFrame = focusFrame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HashMap<String, Object> getParam(){
        return param;
    }
    
    private Dispatcher() {

        user = new User();
        this.mainFrame = new LoginJFrame();
        IActionIHM initLoginFrame = InitLoginFrame.getInstance();
        initLoginFrame.setDispatcher(this);
        initLoginFrame.setJComponent(mainFrame.getJComponent());
        initLoginFrame.execute();
    }

    public static Dispatcher getInstance() {
        return Dispatcher.DispatcherHolder.INSTANCE;
    }

    private static class DispatcherHolder {

        private static final Dispatcher INSTANCE = new Dispatcher();
    }

    public ActionListener getListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendAction(e);
            }
        };
    }

    public void sendAction(ActionEvent e) {

        String action = e.getActionCommand();
        System.out.println(action);
        switch (action) {
            case ActionName.createAccount:
                System.err.println(action);
                break;
            case ActionName.logAccount:
                System.err.println(action);
                LoginAccount loginAccount = LoginAccount.getInstance();
                loginAccount.setJComponent(mainFrame.getJComponent());
                loginAccount.setUser(user);
                if (loginAccount.execute()) {
                    mainFrame.dispose();
                    mainFrame = new MainCSFrame();
                    IActionIHM initMainFrame = InitMainFrame.getInstance();
                    initMainFrame.setDispatcher(this);
                    initMainFrame.setJComponent(mainFrame.getJComponent());
                    initMainFrame.setUser(user);
                    initMainFrame.execute();
                }

                break;

            case ActionName.updateAccount:
                System.err.println(action);
                focusFrame = new AccountCSFrame();
                IActionIHM initUpdateAccountCS = InitUpdateAccountCS.getInstance();
                initUpdateAccountCS.setDispatcher(this);
                initUpdateAccountCS.setJComponent(focusFrame.getJComponent());
                initUpdateAccountCS.execute();

                break;
            case ActionName.forgottenPassword:
                System.err.println(action);
                break;
            case ActionName.forgottenLogin:
                System.err.println(action);
                break;
            case ActionName.addMailAccount:
                System.err.println(action);
                focusFrame = new AcountMailCSFrame();
                IActionIHM initMailAccount = InitAddMailAccount.getInstance();
                initMailAccount.setDispatcher(this);
                initMailAccount.setJComponent(focusFrame.getJComponent());
                initMailAccount.execute();

                break;
            case ActionName.selectMailAccount:
                System.err.println(action);
                break;
            case ActionName.udpateMailAccount:
                System.err.println(action);
                focusFrame = new AcountMailCSFrame();
                IActionIHM initUpdateMailAccount = InitUpdateMailAccount.getInstance();
                initUpdateMailAccount.setDispatcher(this);
                initUpdateMailAccount.setJComponent(focusFrame.getJComponent());
                initUpdateMailAccount.execute();

                break;
            case ActionName.deleteMailAccount:
                System.err.println(action);
                break;
            case ActionName.writeMail:
                System.err.println(action);
                focusFrame = new MailCSFrame();
                IActionIHM iniWriteMail = InitWriteMail.getInstance();
                iniWriteMail.setDispatcher(this);
                iniWriteMail.setJComponent(focusFrame.getJComponent());
                iniWriteMail.execute();
                
                break;
            case ActionName.readMail:
                System.err.println(action);
                break;
            case ActionName.selectMail:
                System.err.println(action);
                
                break;
            case ActionName.deleteMail:
                System.err.println(action);
                break;
            case ActionName.addBlackListSender:
                System.err.println(action);
                break;
            case ActionName.removeBlacklistSender:
                System.err.println(action);
                break;
            case ActionName.answerMail:
                System.err.println(action);
                focusFrame = new MailCSFrame();
                IActionIHM iniWriteMailAnswer = InitWriteMail.getInstance();
                iniWriteMailAnswer.setDispatcher(this);
                iniWriteMailAnswer.setJComponent(focusFrame.getJComponent());
                iniWriteMailAnswer.execute();
                
                break;
            case ActionName.forwardMail:
                System.err.println(action);
                focusFrame = new MailCSFrame();
                IActionIHM iniWriteMailForward = InitWriteMail.getInstance();
                iniWriteMailForward.setDispatcher(this);
                iniWriteMailForward.setJComponent(focusFrame.getJComponent());
                iniWriteMailForward.execute();
                
                break;
            case ActionName.sendMail:
                System.err.println(action);
                break;
            case ActionName.downloadPicture:
                System.err.println(action);
                break;
            case ActionName.createMailAccount:
                System.err.println(action);
                IActionBackOffice createMailAccount = CreateMailAccount.getInstance();
                createMailAccount.setUser(user);
                createMailAccount.setJComponent(focusFrame.getJComponent());
                if(createMailAccount.execute()){
                    focusFrame.dispose();
                }
                break;
            case ActionName.logout:
                System.err.println(action);
                IActionBackOffice disconectAccount = DisconectAccount.getInstance();
                disconectAccount.setUser(user);
                disconectAccount.setJComponent(focusFrame.getJComponent());
                disconectAccount.execute();
                break;
            default:
                break;
        }
    }
}
