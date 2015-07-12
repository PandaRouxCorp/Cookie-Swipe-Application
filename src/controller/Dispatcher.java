/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import module.backoffice.ConnectAccountAction;
import module.backoffice.CreateMailAccountAction;
import module.backoffice.DeleteMailAccountAction;
import module.backoffice.DisconectAccountAction;
import module.backoffice.UpdateMailAccountAction;
import module.ihm.AddMailAccountFrameInitializer;
import module.ihm.MainFrameInitializer;
import module.ihm.UpdateAccountCSFrameInitializer;
import module.ihm.UpdateMailAccountFrameInitializer;
import module.ihm.WriteMailFrameInitializer;
import network.messageFramework.DeliverySystem;
import network.messageFramework.FrameworkMessage;
import view.AccountCSFrame;
import view.AcountMailCSFrame;
import view.MailCSFrame;
import view.MainCSFrame;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;
import cookie.swipe.application.CookieSwipeApplication;
import javax.mail.Message;
import model.MailAccount;
import module.backoffice.CreateCSAccountAction;
import module.backoffice.ForgottenAction;
import module.backoffice.ReadMailAction;
import module.backoffice.SendMailAction;
import module.ihm.CreateAccountFrameInitializer;
import module.ihm.LoginForgottenFrameInitializer;
import network.mail.FolderManager;
import view.LoginForgottenCSFrame;
import view.PasswordForgottenCSFrame;


public class Dispatcher implements ActionListener {

    /**
     * Distribue les actions de l'utilsiateur à des traitements
     *
     * @param e Evénement décrivant l'action à réaliser
     */
    @Override
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
        String password = new String(((CookieSwipePasswordField)application.getMainFrameJComponent("cookieSwipePasswordFieldPassword")).getPassword());
        if (new ConnectAccountAction().execute(login, password)) {
            MainCSFrame frame = new MainCSFrame();
            application.getUser().addListMailAccountListeneur(frame);
            application.setMainFrame(frame);
            new MainFrameInitializer(frame).execute();
            DeliverySystem.launchTask(new FrameworkMessage<Object>() {
				private static final long serialVersionUID = -1840411908522351726L;
				@Override
				public Object call() throws Exception {
					application.getUser().retrieveMails();
					return null;
				}
			});
        }
    }

    public void updateAccountAction() {
        AccountCSFrame focusFrame = new AccountCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(focusFrame);
        new UpdateAccountCSFrameInitializer(focusFrame).execute();
    }

    public void forgottenPasswordAction() {
        PasswordForgottenCSFrame frame = new PasswordForgottenCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(frame);
        
//        System.err.println("NOT IMPLEMENTED lol");
    }
    
    public void sendMailForgottenPasswordAction() {
        LoginForgottenCSFrame frame = (LoginForgottenCSFrame) CookieSwipeApplication.getApplication().getFocusFrame();
        ForgottenAction action = new ForgottenAction(frame);
        if (action.execute("login"))
            frame.dispose();
    }

    public void forgottenLoginAction() {
        LoginForgottenCSFrame frame = new LoginForgottenCSFrame();
        CookieSwipeApplication.getApplication().setFocusFrame(frame);
        new LoginForgottenFrameInitializer(frame).execute();
    }
    
    public void sendMailForgottenLoginAction() {
        LoginForgottenCSFrame frame = (LoginForgottenCSFrame) CookieSwipeApplication.getApplication().getFocusFrame();
        ForgottenAction action = new ForgottenAction(frame);
        if (action.execute("login"))
            frame.dispose();
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
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        mailAccount.removeToListOfmail((String)CookieSwipeApplication.getApplication().getParam("folderName"),
                                        (Message) CookieSwipeApplication.getApplication().getParam("selectedMail"));
    }

    public void selectMailAction() {
        System.err.println("NOT IMPLEMENTED");
    }

    public void readMailAction() {
        Message message = (Message) CookieSwipeApplication.getApplication().getParam("selectedMail");
        if(message != null)
            new ReadMailAction().execute(message);
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

    public void createAccountAction() { // compte cookie swipe a créé
        CookieSwipeApplication application = CookieSwipeApplication.getApplication();
        CookieSwipeFrame frame = application.getFocusFrame();
        String login    = ((CookieSwipeTextField)application.getFocusFrameJComponent("cookieSwipeTextFieldLoginAdressMail")).getText();
        String pwd    = new String( ((CookieSwipePasswordField)application.getFocusFrameJComponent("cookieSwipePasswordFieldPassword")).getPassword() );
        String backup    = ((CookieSwipeTextField)application.getFocusFrameJComponent("cookieSwipeTextFieldBackupMail")).getText();
        boolean created = new CreateCSAccountAction().execute(login, pwd, backup);
        if(created) 
            frame.dispose();
        if (created && new ConnectAccountAction().execute(login, pwd)) {
            MainCSFrame mainFrame = new MainCSFrame();
            application.getUser().addListMailAccountListeneur(mainFrame);
            application.setMainFrame(mainFrame);
            new MainFrameInitializer(mainFrame).execute();
            DeliverySystem.launchTask(new FrameworkMessage<Object>() {
				@Override
				public Object call() throws Exception {
					application.getUser().retrieveMails();
					return null;
				}
			});
        }
    }
    
    public void inscriptionAction() {
        CookieSwipeApplication application = CookieSwipeApplication.getApplication();
        AccountCSFrame frame = new AccountCSFrame();
        application.setFocusFrame(frame);
        new CreateAccountFrameInitializer(frame).execute();
    }

    public void sendMailAction() {
        CookieSwipeFrame focusFrame = CookieSwipeApplication.getApplication().getFocusFrame();
        new SendMailAction(focusFrame).execute();
    }
    
    public void refreshAction() {
        FolderManager fm = (FolderManager) CookieSwipeApplication.getApplication().getParam("FolderManager");
        fm.refresh();
    }
}
