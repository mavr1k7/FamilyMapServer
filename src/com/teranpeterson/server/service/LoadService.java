package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.*;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.model.Person;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.LoadRequest;
import com.teranpeterson.server.result.LoadResult;

import java.sql.Connection;

/**
 * Loads the provided user, person and event data into the database.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoadService {
    /**
     * Creates a blank load service object
     */
    public LoadService() {

    }

    /**
     * Clears all data from the database (just like the /clear API), and then loads the
     * posted user, person, and event data into the database.
     *
     * @param request All the user, person and event data to load
     * @return Information about whether it was successful or not
     */
    public LoadResult load(LoadRequest request) {
        Database db = new Database();
        try {
            db.clear();
            db.createTables();
            Connection conn = db.openConnection();

            int x = 0;
            UserDAO uDAO = new UserDAO(conn);
            for (User u : request.getUsers()) {
                uDAO.insert(u);
                ++x;
            }

            int y = 0;
            PersonDAO pDAO = new PersonDAO(conn);
            for (Person p : request.getPersons()) {
                pDAO.insert(p);
                ++y;
            }

            int z = 0;
            EventDAO eDAO = new EventDAO(conn);
            for (Event e : request.getEvents()) {
                eDAO.insert(e);
                ++z;
            }

            db.closeConnection(true);
            return new LoadResult(x, y, z);
        } catch (DAOException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
                return new LoadResult(e.getMessage());
            } catch (DAOException d) {
                d.printStackTrace();
                return new LoadResult(d.getMessage());
            }
        }
    }
}
