package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.MailAccount;
import model.User;
import network.mail.FolderManager;
import cookie.swipe.application.CookieSwipeApplication;
import errorMessage.CodeError;

/**
 * Classe statique faisant office de passerelle entre les objet MailAccount du
 * modèle et la persistance
 *
 * @author Mary & Stitch
 */
public class DAOMailAccount {

    /**
     * Créé un comtpe courriel en persitance
     *
     * @param mailAccount compte courriel à crée
     * @param user utilisateur qui créer le compte
     * @return code d'erreur
     */
    public static int createMailAccount(MailAccount mailAccount, User user) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "INSERT INTO mailaccount( user_id, adresse, cookieswipename, domain, password, color) "
                + "VALUES (?, ?, ?, ?, ?, ? )";
        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1, user.getId());
            statementInstance.setString(2, mailAccount.getAddress());
            statementInstance.setString(3, mailAccount.getCSName());
            statementInstance.setInt(4, mailAccount.getDomain().getId());
            statementInstance.setString(5, mailAccount.getPassword());
            statementInstance.setString(6, mailAccount.getColor());

            statementInstance.executeUpdate();
            error = CodeError.SUCESS;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
        }
        return error;
    }

    /**
     * Charge toute les données d'un compte courriel qui était sauvegardé
     *
     * @param user Utilisateur concerné
     * @return Code d'erreur
     */
    public static int loadMailAccount(User user) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "SELECT id, adresse, cookieswipename, domain, password, lastsync, mailsignature, color "
                + "FROM mailaccount "
                + "WHERE user_id = ?";
        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1, user.getId());
            ResultSet result = statementInstance.executeQuery();

            List<MailAccount> mailAccountList = new ArrayList<>();
            MailAccount mailAccount = null;
            
            error = CodeError.SUCESS;
            
            while (result.next()) {
                mailAccount = new MailAccount();

                mailAccount.setId(result.getInt("id"));
                mailAccount.setAddress(result.getString("adresse"));
                mailAccount.setCSName(result.getString("cookieswipename"));
                mailAccount.setPassword(result.getString("password"));
                mailAccount.setColor(result.getString("color"));
                mailAccount.setLastSynch(result.getDate("lastsync"));
                mailAccount.setMailSignature(result.getString("mailsignature"));
                mailAccount.getDomain().setId(result.getInt("domain"));
                int resLoadDomain = loadDomail(mailAccount);
                if (resLoadDomain != 0) {
                    error = resLoadDomain;
                } else {
                    mailAccountList.add(mailAccount);
                }
            }
            user.setListOfMailAccount(mailAccountList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    // Puis on ferme le Statement 
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
        }
        return error;
    }

    /**
     * Charge le domaine d'un comtpe courriel
     *
     * @param mailAccount compte courriel concerné
     * @return Code d'erreur
     */
    public static int loadDomail(MailAccount mailAccount) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT name, adresse, popaddr, smtpaddr, portin, portout, protocol FROM domain where id = ?;";

        try {

            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setInt(1, mailAccount.getDomain().getId());

            ResultSet result = statementInstance.executeQuery();

            if (result.next()) {
                mailAccount.getDomain().setName(result.getString("name"));
                mailAccount.getDomain().setAddress(result.getString("adresse"));
                mailAccount.getDomain().setServerIn(result.getString("popaddr"));
                mailAccount.getDomain().setServerOut(result.getString("smtpaddr"));
                mailAccount.getDomain().setPortIn(result.getString("portin"));
                mailAccount.getDomain().setPortOut(result.getString("portout"));
                mailAccount.getDomain().setStoreProtocol(result.getString("protocol"));
            }
            error = CodeError.SUCESS;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
        }

        return error;
    }

    /**
     * Permet de modifier un compte courriel en persitance
     *
     * @param mailaccount Compte courriel à changer
     * @return Code d'erreur
     */
    public static int updateMailAccount(MailAccount mailaccount) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "UPDATE mailaccount "
                + "SET adresse = ?, cookieswipename = ?, domain = ?, password = ?, "
                + "lastsync = ?, mailsignature = ?, color = ? WHERE id = ?;";
        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setString(1, mailaccount.getAddress());
            statementInstance.setString(2, mailaccount.getCSName());
            statementInstance.setInt(3, mailaccount.getDomain().getId());
            statementInstance.setString(4, mailaccount.getPassword());
            statementInstance.setDate(5, new Date(mailaccount.getLastSynch().getTime()));
            statementInstance.setString(6, mailaccount.getMailSignature());
            statementInstance.setString(7, mailaccount.getColor());
            statementInstance.setInt(8, mailaccount.getId());

            statementInstance.executeUpdate();

            error = CodeError.SUCESS;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
        }
        return error;
    }

    /**
     * Charge les courriels d'un compte courriel
     *
     * @param mailAccount compte courriel concerné
     * @return Code d'erreur
     */
    public static int loadMails(List<MailAccount> mailAccounts) {
        try {
        	FolderManager folderManager = new FolderManager();
        	folderManager.addMailAccounts(mailAccounts);
        	CookieSwipeApplication.getApplication().setParam("FolderManager", folderManager);
        } catch (Exception ex) {
            Logger.getLogger(DAOMailAccount.class.getName()).log(Level.SEVERE, "An error occured when retreiving mails", ex);
            return CodeError.FAILLURE;
        }
        return CodeError.SUCESS;
    }

	public static int deleteMailAccount(MailAccount mailAccount) {
        int error;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "DELETE FROM mailaccount WHERE id = ?";
        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1, mailAccount.getId());

            statementInstance.execute();

            error = CodeError.SUCESS;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
        }

        return error;
    }
}
