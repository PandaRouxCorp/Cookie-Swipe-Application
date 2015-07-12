
package dao;

import errorMessage.CodeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 * Classe statique faisant office de passerelle entre les objet User du modèle
 * et la persistance
 *
 * @author Mary
 */
public class DAOUser {

    /**
     * Crée l'utilsiateur en persitance puis le connecte
     *
     * @param user utilsiateur à créer
     * @return Code d'erreur
     */
    public static int createUser(User user) {
        int error;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "INSERT INTO users(login, password, backupadr) VALUES (?, ?, ?);";

        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, user.getLoginAdressMail());
            statementInstance.setString(2, user.getPassword());
            statementInstance.setString(3, user.getBackupMail());

            int statut = statementInstance.executeUpdate();

            if (statut == 1) {
                error = CodeError.SUCESS;
            } else {
                error = CodeError.FAILLURE;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.STATEMENT_EXECUTE_FAIL;

        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
        }
        return error;
    }

    /**
     * Permet de modifier les informations d'un utilisateur en persitance
     *
     * @param user Utilisateur à modifier
     * @return Code d'erreur
     */
    public static int updateUser(User user) {
        int error;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "UPDATE user "
                + "SET login = ?, "
                + "password = ?, "
                + "backupAdr = ?, "
                + "WHERE id = ?;";

        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                error = CodeError.CONNEXION_FAIL;
            }

            statementInstance = connectionInstance.prepareStatement(request);

            statementInstance.setString(1, user.getLoginAdressMail());
            statementInstance.setString(2, user.getPassword());
            statementInstance.setString(3, user.getBackupMail());
            statementInstance.setInt(4, user.getId());

            int statut = statementInstance.executeUpdate();

            if (statut == 1) {
                error = CodeError.SUCESS;
            } else {
                error = CodeError.FAILLURE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
        }
        return error;
    }

    /**
     * Connecte l'utilisateur si il existe en persistance
     *
     * @param user Utilisateur qui tente de se connecter
     * @return Code d'erreur
     */
    public static int connectUser(User user) {
        int error;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT count(*), id FROM users where login = ? and password = ?;";

        try {
            connectionInstance = BDDConnect.getConnection();
            if(connectionInstance == null) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "CONNEXION_FAIL");
                error =  CodeError.CONNEXION_FAIL;
            }
            else {
                statementInstance = connectionInstance.prepareStatement(request);

                statementInstance.setString(1, user.getLoginAdressMail());
                statementInstance.setString(2, user.getPassword());

                ResultSet result = statementInstance.executeQuery();
                result.next();
                if (result.getInt(1) == 1) {
                    user.setId(result.getInt(2));
                    error = DAOMailAccount.loadMailAccount(user);
                } else {
                    error = CodeError.FAILLURE;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
        }
        return error;
    }

    /**
     * Connecte l'utilisateur si il existe en persistance
     *
     * @param usr Utilisateur qui tente de se connecter
     * @return Code d'erreur
     */
    public static int getUserByBackupAddress(User usr) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT count(*), login, password FROM users where backupAdr = ? ;";

        try {
            connectionInstance = BDDConnect.getConnection();
            if(connectionInstance == null) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, "CONNEXION_FAIL");
                error =  CodeError.CONNEXION_FAIL;
            }
            else {
                statementInstance = connectionInstance.prepareStatement(request);

                statementInstance.setString(1, usr.getBackupMail());

                ResultSet result = statementInstance.executeQuery();
                result.next();
                if (result.getInt(1) == 1) {
                    usr.setLoginAdressMail(result.getString(2));
                    usr.setPassword(result.getString(3));
                } else {
                    error = CodeError.FAILLURE;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } finally {
            if (statementInstance != null) {
                try {
                    statementInstance.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                    error = CodeError.STATEMENT_CLOSE_FAIL;
                }
            }
        }
        return error;
    }
}
