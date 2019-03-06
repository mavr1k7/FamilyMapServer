package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller used to connect to and modify auth tokens in the database
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class AuthTokenDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates a dao with a database connection
     */
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds new authentication token to database
     *
     * @param token Authentication token for a given user
     * @throws DAOException Problem executing sql statements
     */
    public void insert(AuthToken token) throws DAOException {
        String sql = "INSERT INTO `AuthTokens`(`token`,`username`) VALUES (?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Unable to insert user '" + token.getUsername() + "' into database");
        }
    }

    /**
     * Check if an authentication token is in the database
     *
     * @param token Authentication token to look for
     * @return True if the token is valid (in the database), otherwise false
     * @throws DAOException Problem executing sql statements
     */
    public String validate(String token) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `AuthTokens` WHERE `token` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token);

            result = stmt.executeQuery();
            if (result.next()) {
                return result.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to validate token '" + token + "' in database");
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Deletes an authentication token from the database. Tokens expire after an hour of no use
     *
     * @param token Authentication token to remove from database
     * @throws DAOException Problem executing sql statements
     */
    public void expire(AuthToken token) throws DAOException {
        String sql = "DELETE * FROM `AuthTokens` WHERE `token` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Unable to expire token '" + token.getToken() + "' from database");
        }
    }

    /**
     * Delete all authentication tokens from the database
     *
     * @throws DAOException Problem executing sql statements
     */
    public void clear() throws DAOException {
        String sql = "DELETE * FROM `AuthTokens`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Unable to clear auth tokens from database");
        }
    }
}
