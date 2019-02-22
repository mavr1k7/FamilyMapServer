package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.AuthToken;

/**
 * Controller used to connect to and modify auth tokens in the database
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class AuthTokenDAO {
    /**
     * Creates an empty auth token dao
     */
    public AuthTokenDAO() {

    }

    /**
     * Adds new authentication token to database
     * @param token Authentication token for a given user
     */
    public void insert(AuthToken token) {

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
