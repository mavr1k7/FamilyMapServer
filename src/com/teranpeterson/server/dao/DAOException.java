package com.teranpeterson.server.dao;

/**
 * Exception thrown by database errors
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class DAOException extends Exception {
    DAOException(String message) {
        super(message);
    }
}