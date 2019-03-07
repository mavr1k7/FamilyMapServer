package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.EventDAO;
import com.teranpeterson.server.model.Event;
import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;

import java.sql.Connection;
import java.util.List;

/**
 * Returns the single Event object with the specified ID or returns ALL events for ALL family members of the current user.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class EventService extends Service {
    /**
     * Creates a blank event service object
     */
    public EventService() {

    }

    /**
     * If the request object contains an eventID, that event is returned. Otherwise, ALL events for ALL family members related
     * to the current user (determined by the auth token) are returned.
     *
     * @param request Information about the event(s) to return
     * @return Information about the events
     */
    public EventResult event(EventRequest request) {
        Database db = new Database();
        try {
            db.createTables();
            Connection conn = db.openConnection();
            AuthTokenDAO aDAO = new AuthTokenDAO(conn);
            String userName = aDAO.validate(request.getAuthToken());
            if (userName == null) {
                try {
                    db.closeConnection(false);
                    return new EventResult("ERROR: Invalid auth token");
                } catch (DAOException d) {
                    return new EventResult(d.getMessage());
                }
            }
            EventDAO eDAO = new EventDAO(conn);
            if (request.getEventID().equals("ALL")) {
                List<Event> list = eDAO.personEvents(userName);
                db.closeConnection(true);
                return new EventResult(list);
            } else {
                Event event = eDAO.find(request.getEventID());
                db.closeConnection(true);
                return new EventResult(event);
            }
        } catch (DAOException e) {
            try {
                db.closeConnection(false);
                return new EventResult(e.getMessage());
            } catch (DAOException d) {
                return new EventResult(d.getMessage());
            }
        }
    }
}
