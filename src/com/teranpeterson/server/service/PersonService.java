package com.teranpeterson.server.service;

import com.teranpeterson.server.request.PersonRequest;
import com.teranpeterson.server.result.PersonResult;

/**
 * Returns the single Person object with the specified ID or returns ALL family members of the current user.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class PersonService extends Service {
    /**
     * Create a blank person service object
     */
    public PersonService() {

    }

    /**
     * If the request object contains a personID, that person is returned. Otherwise ALL family members related
     * to the current user (determined by the auth token) are returned.
     * @param request Information about the person(s) to return
     * @return Information about the person(s)
     */
    public PersonResult person(PersonRequest request) {
        return null;
    }
}
