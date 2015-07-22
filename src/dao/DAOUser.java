
package dao;

import errorMessage.CodeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Encryption;
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
        String request = "UPDATE users "
                + "SET login = ?, "
                + "password = ?, "
                + "backupAdr = ? "
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
//            System.err.println("Requeteuh " + request);

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
     * Permet de modifier la blackList d'un utilisateur en persitance
     *
     * @param user Utilisateur à modifier
     * @return Code d'erreur
     */
    public static int updateBlackListUser(User user) {
        int error;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "UPDATE users "
                + "SET blacklist = ? "
                + "WHERE id = ?;";

        try {
            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
                error = CodeError.CONNEXION_FAIL;
            }

            statementInstance = connectionInstance.prepareStatement(request);
            
            String blackl = "";
            List<String> bl = user.getBlackList();
            for(int i = 0; i < bl.size(); i++) {
                blackl += (i == 0) ? bl.get(i) : "; " + bl.get(i);
            }
            
            statementInstance.setString(1, blackl);
            statementInstance.setInt(2, user.getId());


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
        String request = "SELECT count(*), id, backupAdr, blacklist FROM users where login = ? and password = ?;";

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
                if (result.getInt(1) >= 1) {
                    user.setId(result.getInt(2));
                    user.setBackupMail(result.getString(3));
                    String blackList = result.getString(4);
                    if (blackList != null && !blackList.isEmpty()) {
                        for (String mail : blackList.split(";")) {
                            user.blackListSender(mail.trim());
                        }
                    }
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
                    String pwd = new Encryption().decrypt( result.getString(3) );
                    usr.setPassword(pwd);
                } else {
                    error = CodeError.FAILLURE;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.STATEMENT_EXECUTE_FAIL;
        } catch (Exception ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
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
    public static int getBlackListUser(User usr) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "SELECT count(*), blacklist FROM users where id = ? ;";

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
                    String blackList = result.getString(2);
                    if (!blackList.isEmpty()) {
                        for (String mail : blackList.split(";")) {
                            usr.blackListSender(mail);
                        }
                    }
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
