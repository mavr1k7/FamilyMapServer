package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.AuthToken;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class AuthTokenDAOTest {
    private AuthToken token;
    Database db;

    @Before
    public void setUp() throws Exception {
        token = new AuthToken("username", "token");
        db = new Database();
        db.clear();
        db.createTables();
    }

    @Test
    public void insertPass() throws Exception {
        boolean success = true;
        try {
            Connection conn = db.openConnection();
            AuthTokenDAO dao = new AuthTokenDAO(conn);
            dao.insert(token);
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
            success = false;
        }

        // Check that the token was inserted correctly
        assertTrue(success);
    }

    @Test
    public void insertFail() throws Exception {
        boolean success = true;
        try {
            Connection conn = db.openConnection();
            AuthTokenDAO dao = new AuthTokenDAO(conn);
            dao.insert(token);
            dao.insert(token);
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
            success = false;
        }

        // Check that duplicate insert bounced
        assertFalse(success);
    }

    @Test
    public void validatePass() throws Exception {
        String success = "";
        try {
            Connection conn = db.openConnection();
            AuthTokenDAO dao = new AuthTokenDAO(conn);
            dao.insert(token);
            success = dao.validate("token");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that user was successfully validated
        assertEquals("username", success);
    }

    @Test
    public void validateFail() throws Exception {
        String success = "";
        try {
            Connection conn = db.openConnection();
            AuthTokenDAO dao = new AuthTokenDAO(conn);
            dao.insert(token);
            success = dao.validate("nottoken");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that invalid login didn't work
        assertNotEquals("username", success);
    }
}