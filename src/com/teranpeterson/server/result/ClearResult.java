package com.teranpeterson.server.result;

/**
 * Contains information about the results of a Clear Request
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class ClearResult extends Result {
    /**
     * Creates a successful clear result
     */
    public ClearResult() {
        super.message = "Clear succeeded.";
        super.success = true;
    }

    /**
     * Creates a failing clear result with the given error message
     *
     * @param message Description of the error
     */
    public ClearResult(String message) {
        super.message = message;
        super.success = false;
    }
}
