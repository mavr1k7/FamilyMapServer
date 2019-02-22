package com.teranpeterson.server.service;

import com.teranpeterson.server.request.FillRequest;
import com.teranpeterson.server.result.FillResult;

/**
 * Populates the server's database with generated data for the specified user name.
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class FillService extends Service {
    /**
     * Creates a blank fill service object
     */
    public FillService() {

    }

    /**
     * Populates the server's database with generated data for the specified user name.
     * The required "username" parameter must be a user already registered with the server. If there is
     * any data in the database already associated with the given user name, it is deleted. The
     * optional “generations” parameter lets the caller specify the number of generations of ancestors
     * to be generated, and must be a non-negative integer (the default is 4)
     *
     * @param request Information about the user to generate data for
     * @return Information about the generated persons
     */
    public FillResult fill(FillRequest request) {
        return null;
    }
}
