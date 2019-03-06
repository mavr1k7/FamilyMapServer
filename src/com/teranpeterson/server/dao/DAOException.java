package com.teranpeterson.server.dao;

/**
 * Exception while accessing information in the database
 */
public class DAOException extends Exception {
    DAOException(String message) {
        super(message);
    }
}