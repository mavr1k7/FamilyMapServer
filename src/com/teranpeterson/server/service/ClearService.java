package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.result.ClearResult;

/**
 * Deletes ALL data from the database, including user accounts, auth tokens, and
 * generated person and event data.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class ClearService extends Service {
    /**
     * Creates a blank clear service object
     */
    public ClearService() {

    }

    /**
     * Deletes ALL data from the database, including user accounts, auth tokens, and
     * generated person and event data.
     *
     * @return Information about whether the clear was successful or not
     */
    public ClearResult clear() {
        Database db = new Database();
        try {
            db.clear();
            return new ClearResult();
        } catch (DAOException e) {
            return new ClearResult(e.getMessage());
        }
    }
}
