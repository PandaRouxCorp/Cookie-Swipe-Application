/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cookie.swipe.application.CookieSwipeApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.ihm.AddMailAccountFrameInitializer;
import module.ihm.MainFrameInitializer;
import module.ihm.UpdateMailAccountFrameInitializer;
import module.backoffice.CreateMailAccountAction;
import module.backoffice.DeleteMailAccountAction;
import module.backoffice.DisconectAccountAction;
import module.backoffice.ConnectAccountAction;
import module.backoffice.UpdateMailAccountAction;
import module.ihm.UpdateAccountCSFrameInitializer;
import module.ihm.WriteMailFrameInitializer;
import view.AccountCSFrame;
import view.AcountMailCSFrame;
import view.MailCSFrame;
import view.MainCSFrame;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;


public class Dispatcher implements ActionListener {

    /**
     * Distribue les actions de l'utilsiateur à des traitements
     *
     * @param e Evénement décrivant l'action à réaliser
     */
    public void actionPerformed(ActionEvent e) {
        String actionName = e.getActionCommand() + "Action";
        try {
            Method actionToPerform = Dispatcher.class.getDeclaredMethod(actionName);
            actionToPerform.invoke(this);
        } catch (InvocationTargetException 
                |IllegalArgumentException 
                |IllegalAccessException 
                |NoSuchMethodException 
                |SecurityException ex ) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, "Unknown action: " + actionName, ex);
        }
    }

    public void logAccountAction() {
        CookieSwipeApplication application = CookieSwipeApplication.getApplication();
        String login    = ((CookieSwipeTextField)application.getMainFrameJComponent("cookieSwipeTextFieldLogin")).getText();
        String password = ((CookieSwipePasswordField)application.getMainFrameJComponent("cookieSwipePasswordFieldPassword")).getText();
        if (new ConnectAccountAction().execute(login, password)) {
            MainCSFrame frame = new MainCSFrame();
            application.setMainFrame(frame);
            new MainFrameInitializer(frame).execute();
        }
    }

    public void updateAccountAction() {
        AccountCSFrame focusFrame = new AccountCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new UpdateAccountCSFrameInitializer(focusFrame).execute();
    }

    public void forgottenPasswordAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void forgottenLoginAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void addMailAccountAction() {
        AcountMailCSFrame focusFrame = new AcountMailCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new AddMailAccountFrameInitializer(focusFrame).execute();
    }

    public void logoutAction() {
        new DisconectAccountAction().execute();
    }

    public void createMailAccountAction() {
        CookieSwipeFrame focusFrame = CookieSwipeApplication.getApplication().getFocusFrame();
        if (new CreateMailAccountAction(focusFrame).execute()) {
            focusFrame.dispose();
        }
    }

    public void downloadPictureAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void forwardMailAction() {
        CookieSwipeFrame focusFrame = new MailCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new WriteMailFrameInitializer(focusFrame).execute();
    }

    public void answerMailAction() {
        CookieSwipeFrame focusFrame = new MailCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new WriteMailFrameInitializer(focusFrame).execute();
    }

    public void removeBlacklistSenderAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void addBlackListSenderAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void deleteMailAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    private void selectMailAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void readMailAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void writeMailAction() {
        CookieSwipeFrame focusFrame = new MailCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new WriteMailFrameInitializer(focusFrame).execute();
    }

    public void deleteMailAccountAction() {
        new DeleteMailAccountAction().execute();
    }

    public void updateMailAccountAction() {
        CookieSwipeFrame focusFrame = CookieSwipeApplication.getApplication().getFocusFrame();
        if (new UpdateMailAccountAction(focusFrame).execute()) {
            focusFrame.dispose();
        }
    }

    public void selectMailAccountAction() {
        AcountMailCSFrame focusFrame = new AcountMailCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new UpdateMailAccountFrameInitializer(focusFrame).execute();
    }

    public void createAccountAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void sendMailAction() {
        System.err.println("NOT IMPLEMENTED");
    }
}
