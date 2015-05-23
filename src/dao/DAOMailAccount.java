/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import errorMessage.CodeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Encryption;
import model.MailAccount;
import model.User;

/**
 * Classe statique faisant office de passerelle entre les objet MailAccount du
 * modèle et la persistance
 *
 * @author Mary
 */
public class DAOMailAccount {

    /**
     * Créé un comtpe courriel en persitance
     *
     * @param mailAccount compte courriel à crée
     * @return si la création du mail a bien réussi
     */
    public static int createMailAccount(MailAccount mailAccount, User user) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String sql = "INSERT INTO mailaccount( user_id, adresse, cookieswipename, domain, password, color) "
                + "VALUES (?, ?, ?, ?, ?, ? )";
//        String encryptedPassword = null;

        try {
//            try {
//                encryptedPassword = new Encryption().encrypt(mailAccount.getPassword());
//            } catch (Exception ex) {
//                System.err.println(ex.getMessage());
//            }
//            
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
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
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
//            On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /* Et enfin on ferme la connexion */
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }
        return error;
    }

    /**
     * Charge toute les données d'un compte courriel qui était sauvegardé
     *
     * @param user
     * @param mailAccount compte courriel concerné
     * @return si le chargement du compte courriel a bien réussi
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
            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setInt(1, user.getId());
            ResultSet result = statementInstance.executeQuery();

            while (result.next()) {
                MailAccount mailAccount = new MailAccount();

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
                    error = CodeError.SUCESS;
                    user.getListOfMailAccount().add(mailAccount);
                }
            }
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
//            On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /// Et enfin on ferme la connexion 
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }
        return error;
    }

    /**
     * Charge le domaine d'un comtpe courriel
     *
     * @param mailAccount compte courriel concerné
     * @return si le chargement du domaine a réussi
     */
    public static int loadDomail(MailAccount mailAccount) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT name, adresse, popaddr, smtpaddr, portin, portout FROM domain where id = ?;";

        try {

            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
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
            }
            error = CodeError.SUCESS;
        } catch (SQLException e) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
//             On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /* Et enfin on ferme la connexion */
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }

        return error;
    }

    public static int updateMailAccount(MailAccount mailaccount) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
//        String encryptedPassword = null;
        String sql = "UPDATE mailaccount "
                + "SET adresse = ?, cookieswipename = ?, domain = ?, password = ?, "
                + "lastsync = ?, mailsignature = ?, color = ? WHERE id = ?;";
        try {

            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
            }
//            
//            try {
//                encryptedPassword = new Encryption().encrypt(mailaccount.getPassword());
//            } catch (Exception ex) {
//                System.err.println(ex.getMessage());
//            }

            statementInstance = connectionInstance.prepareStatement(sql);
            statementInstance.setString(1, mailaccount.getAddress());
            statementInstance.setString(2, mailaccount.getCSName());
            statementInstance.setInt(3, mailaccount.getDomain().getId());
            statementInstance.setString(4, mailaccount.getPassword());
            statementInstance.setDate(5, mailaccount.getLastSynch());
            statementInstance.setString(6, mailaccount.getMailSignature());
            statementInstance.setString(7, mailaccount.getColor());
            statementInstance.setInt(8, mailaccount.getId());

            statementInstance.executeUpdate();

            error = CodeError.SUCESS;
        } catch (SQLException e) {
            /* Traiter les erreurs éventuelles ici. */
            System.err.println(e.getMessage());
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    /* Puis on ferme le Statement */
                    statementInstance.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                    error = CodeError.STATEMENT_CLOSE_FAIL;

                }
            }
//            On ne peux pas fermer un singleton
//            if ( connectionInstance != null ) {
//                try {
//                    /* Et enfin on ferme la connexion */
//                    connectionInstance.close();
//                } catch ( SQLException e ) {
//                    System.err.println(e.getMessage());
//                }
//            }
        }
        return error;
    }

    /**
     * Charge les courriels d'un compte courriel
     *
     * @param mailAccount compte courriel concerné
     * @return si le chargement des courriels a réussi
     */
    public static int loadMail(MailAccount mailAccount) {
        return CodeError.NOT_INPLEMENT;
    }
}
