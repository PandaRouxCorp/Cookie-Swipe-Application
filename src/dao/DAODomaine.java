
package dao;

import errorMessage.CodeError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Domain;

/**
 * Classe statique faisant office de passerelle entre les objet Domain du
 * modèle et la persistance
 * 
 * @author Stitch & Mary
 */
public class DAODomaine {

    /**
     * Créer un domaine en persitance
     * @param domain Dommaine à insérer
     * @return un code d'erreur
     */
    public static int createDomain(Domain domain) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "INSERT INTO domain(name, adresse, popaddr, smptaddr, portin, portout) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        try {

            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, domain.getName());
            statementInstance.setString(2, domain.getAddress());
            statementInstance.setString(3, domain.getServerIn());
            statementInstance.setString(4, domain.getServerOut());
            statementInstance.setString(5, domain.getPortIn());
            statementInstance.setString(6, domain.getPortOut());

            int statut = statementInstance.executeUpdate();

            if (statut == 1) {
                error = CodeError.SUCESS;
            }
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
     * Permet de modifier un domaine
     * @param domain Domaine à modifier
     * @return code d'erreur
     */
    public static int updateDomain(Domain domain) {
        int error = 0;
        Connection connectionInstance = null;
        PreparedStatement statementInstance = null;
        String request = "UPDATE domain "
                + "SET name = ?, "
                + "popaddr = ?, "
                + "smtpaddr = ?, "
                + "port = ? "
                + "WHERE id = ?";
        try {

            try {
                connectionInstance = BDDConnect.getConnection();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                error = CodeError.CONNEXION_FAIL;
                return error;
            }

            statementInstance = connectionInstance.prepareStatement(request);
            statementInstance.setString(1, domain.getName());
            statementInstance.setString(2, domain.getServerIn());
            statementInstance.setString(3, domain.getServerOut());
            statementInstance.setString(4, domain.getPortIn());
            statementInstance.setInt(5, domain.getId());

            statementInstance.executeUpdate();

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
