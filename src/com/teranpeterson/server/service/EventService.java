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
public class EventService {
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
            // Validate the provided auth token
            db.createTables();
            Connection conn = db.openConnection();
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            String userName = authTokenDAO.validate(request.getAuthToken());

            // Return if auth token is invalid
            if (userName == null) {
                try {
                    db.closeConnection(false);
                    return new EventResult("ERROR: Invalid auth token");
                } catch (DAOException e) {
                    e.printStackTrace();
                    return new EventResult(e.getMessage());
                }
            }

            // Return events requested by user
            EventDAO eventDAO = new EventDAO(conn);
            if (request.getEventID() == null) {
                List<Event> list = eventDAO.personEvents(userName);
                db.closeConnection(true);
                return new EventResult(list);
            } else {
                Event event = eventDAO.find(request.getEventID());
                db.closeConnection(true);
                if (event == null) return new EventResult("ERROR: Invalid eventID");
                else if (!event.getDescendant().equals(userName)) return new EventResult("ERROR: Event is not related to you");
                else return new EventResult(event);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            try {
                db.closeConnection(false);
                return new EventResult(e.getMessage());
            } catch (DAOException d) {
                d.printStackTrace();
                return new EventResult(d.getMessage());
            }
        }
    }
}
