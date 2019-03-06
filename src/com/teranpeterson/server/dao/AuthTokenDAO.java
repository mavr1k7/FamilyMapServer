package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.AuthToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Controller used to connect to and modify auth tokens in the database
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class AuthTokenDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates an empty auth token dao
     */
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds new authentication token to database
     * @param token Authentication token for a given user
     */
    public void insert(AuthToken token) throws DAOException {
        String sql = "INSERT INTO `AuthTokens`(`token`,`username`) VALUES (?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token.getToken());
            stmt.setString(2, token.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Couldn't insert user into database");
        }
    }

    /**
     * Check if an authentication token is in the database
     * @param token Authentication token to look for
     * @return True if the token is valid (in the database), otherwise false
     */
    public boolean isValid(AuthToken token) {
        return true;
    }

    /**
     * Deletes an authentication token from the database. Tokens expire after an hour of no use
     * @param token Authentication token to remove from database
     */
    public void expire(AuthToken token) {

    }

    /**
     * Delete all authentication tokens from the database
     */
    public void clear() {

    }
}
