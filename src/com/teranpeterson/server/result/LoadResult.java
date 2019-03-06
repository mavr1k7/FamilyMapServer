package com.teranpeterson.server.result;

/**
 * Contains information about the results of a Load Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoadResult extends Result {
    /**
     * Creates a successful load result with information about users, persons and events added
     *
     * @param users   Number of users added to database
     * @param persons Number of persons added to database
     * @param events  Number of events added to database
     */
    public LoadResult(int users, int persons, int events) {
        super.message = "Successfully added " + users + " users, " + persons + " persons, and " + events + " events to the database.";
        super.success = true;
    }

    /**
     * Creates a failing load result with the given error message
     *
     * @param message Description of the error
     */
    public LoadResult(String message) {
        super.message = message;
        super.success = false;
    }
}
