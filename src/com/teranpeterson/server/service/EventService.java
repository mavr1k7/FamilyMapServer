package com.teranpeterson.server.service;

import com.teranpeterson.server.request.EventRequest;
import com.teranpeterson.server.result.EventResult;

/**
 * Returns the single Event object with the specified ID or returns ALL events for ALL family members of the current user.
 *
 * @author Teran Peterson
 * @version v0.0.1
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
     * @param request Information about the event(s) to return
     * @return Information about the events
     */
    public EventResult event(EventRequest request) {
        return null;
    }
}
