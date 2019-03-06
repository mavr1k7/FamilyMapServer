package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;

import java.sql.Connection;

/**
 * Loads the provided user, person and event data into the database.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class LoadService extends Service {
    /**
     * Creates a blank load service object
     */
    public LoadService() {

    }

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     * posted user, person, and event data into the database.
     * @param request All the user, person and event data to load
     * @return Information about whether it was successful or not
     */
    public LoadResult load(LoadRequest request) {
        Database db = new Database();
        try {
            Connection conn = db.openConnection();
        } catch (DAOException e) {
            try {
                db.closeConnection(false);
                return new LoadResult(e.getMessage());
            } catch (DAOException d) {
                return new LoadResult(d.getMessage());
            }
        }
        return null;
    }
}
