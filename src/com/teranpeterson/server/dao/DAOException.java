package com.teranpeterson.server.dao;

/**
 * Data access exception
 */
public class DAOException extends Exception {
    DAOException(String message)
    {
        super(message);
    }
}
