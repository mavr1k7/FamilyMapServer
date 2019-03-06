package com.teranpeterson.server.result;

/**
 * Contains information about the results of a Fill Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class FillResult extends Result {
    /**
     * Creates a successful fill result with information about persons and events added
     *
     * @param persons Number of persons added to database
     * @param events  Number of events added to database
     */
    public FillResult(int persons, int events) {
        super.message = "Successfully added " + persons + " persons and " + events + " events to the database.";
        super.success = true;
    }

    /**
     * Creates a failing fill result with the given error message
     *
     * @param message Description of the error
     */
    public FillResult(String message) {
        super.message = message;
        super.success = false;
    }
}
