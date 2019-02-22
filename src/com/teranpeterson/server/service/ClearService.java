package com.teranpeterson.server.service;

import com.teranpeterson.server.request.ClearRequest;
import com.teranpeterson.server.result.ClearResult;

/**
 * Deletes ALL data from the database, including user accounts, auth tokens, and
 * generated person and event data.
 *
 * @author Teran Peterson
 * @version v0.0.1
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
     * @param request Information about the clear request
     * @return Information about whether the clear was successful or not
     */
    public ClearResult clear(ClearRequest request) {
        ClearResult result = new ClearResult();
        return result;
    }
}
