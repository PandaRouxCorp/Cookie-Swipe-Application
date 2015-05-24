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
import module.backoffice.DeleteMailAccount;
import module.backoffice.DisconectAccount;
import module.backoffice.LoginAccount;
import module.backoffice.UpdateMailAccount;
import module.ihm.InitLoginFrame;
import module.ihm.InitUpdateAccountCS;
import module.ihm.InitWriteMail;
import view.AccountCSFrame;
import view.AcountMailCSFrame;
import view.LoginJFrame;
import view.MailCSFrame;
import view.MainCSFrame;

/**
 * Classe singleton permetant d'attribuer des traitements à des actions de
 * l'utilisateur utilisateur à des traitements Doit être un singleton
 *
 * @author Mary
 */
public class Dispatcher {

    //Variable membre
    private IJFrame mainFrame, focusFrame;
    private User user;
    private HashMap<String, Object> param = new HashMap<String, Object>();

    //Constructeur
    /**
     * Constructeur du singleton. Permet de lancer la première fenêtre de
     * l'application et initialiser l'utilisateur.
     */
    private Dispatcher() {

        user = new User();
        this.mainFrame = new LoginJFrame();
        IActionIHM initLoginFrame = InitLoginFrame.getInstance();
        initLoginFrame.setDispatcher(this);
        initLoginFrame.setJComponent(mainFrame.getJComponent());
        initLoginFrame.execute();
    }

    /**
     * Retourne l'instance du dispatcher
     *
     * @return Instance d'un dispatcher
     */
    public static Dispatcher getInstance() {
        return Dispatcher.DispatcherHolder.INSTANCE;
    }

    /**
     * Instancie le dispatcher si c'est la première fois
     */
    private static class DispatcherHolder {

        private static final Dispatcher INSTANCE = new Dispatcher();
    }

    //Fonction membre publique
    /**
     * Permet d'ajouer un paramètre stocké dans le dispatcher
     *
     * @param s Nom du paramètre
     * @param o Paramètre à stocker
     */
    public void addParam(String s, Object o) {
        param.put(s, o);
    }

    /**
     * Retour le paramètre désiré ou null si le paramètre n'exise pas
     *
     * @param s Nom du paramètre
     * @return Un paramètre stocké
     */
    public Object getParam(String s) {
        return param.get(s);
    }

    /**
     * renvoie un listerner pour un bouton configuré pour utiliser le dispatcher
     *
     * @return ActionListener configuré
     */
    public ActionListener getListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendAction(e);
            }
        };
    }

    /**
     * Distribue les actions de l'utilsiateur à des traitements
     *
     * @param e Evénement décrivant l'action à réaliser
     */
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
                focusFrame = new AcountMailCSFrame();
                IActionIHM initUpdateMailAccount = InitUpdateMailAccount.getInstance();
                initUpdateMailAccount.setDispatcher(this);
                initUpdateMailAccount.setJComponent(focusFrame.getJComponent());
                initUpdateMailAccount.execute();

                break;
            case ActionName.udpateMailAccount:
                System.err.println(action);
                IActionBackOffice updateMailAccount = UpdateMailAccount.getInstance();
                updateMailAccount.setDispatcher(this);
                updateMailAccount.setJComponent(focusFrame.getJComponent());
                updateMailAccount.setUser(user);
                if (updateMailAccount.execute()) {
                    focusFrame.dispose();
                }
                break;

            case ActionName.deleteMailAccount:
                System.err.println(action);
                System.err.println(action);
                IActionBackOffice deleteMailAccount = DeleteMailAccount.getInstance();
                deleteMailAccount.setDispatcher(this);
                deleteMailAccount.setUser(user);
                deleteMailAccount.execute();
                
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
                if (createMailAccount.execute()) {
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

    //Getter & Setter
    /**
     * Retourne la fenêtre principale de l'application
     *
     * @return Fenêtre principale
     */
    public IJFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Permet de désigner la fenêtre considéré comme principale pour
     * l'application
     *
     * @param mainFrame Fenêtre principale
     */
    public void setMainFrame(IJFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Retourne la fenêtre qui à le focus pour l'utilisateur et qui n'es pas la
     * fenêtre principale
     *
     * @return Fenêtre qui à le focus
     */
    public IJFrame getFocusFrame() {
        return focusFrame;
    }

    /**
     * Permet de désigner qu'elle fenêtre aurra à le focus pour l'utilisateur
     *
     * @param focusFrame fenêtre qui va avoir le foux
     */
    public void setFocusFrame(IJFrame focusFrame) {
        this.focusFrame = focusFrame;
    }

    /**
     * Retourne l'utilisateur connecté
     *
     * @return Utilsiateur connecté
     */
    public User getUser() {
        return user;
    }

    /**
     * Permet de désigner l'utilsiateur connecté
     *
     * @param user Utilisateur connecté
     */
    public void setUser(User user) {
        this.user = user;
    }

}
