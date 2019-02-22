package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class UserDAOTest {
    private Database db;
    private User user;

    @Before
    public void setUp() throws Exception {
        db = new Database();
        user = new User("sam", "irock", "samsmith@gmail.com", "sam", "smith", "m", "as8sd9fui");
        db.createTables();
    }

    @After
    public void tearDown() throws Exception {
        db.clear();
    }

    @Test
    public void insertPass() throws Exception {
        User insertTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            insertTest = dao.find(user.getUsername());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that the user was entered correctly to database
        assertNotNull(insertTest);
        assertEquals(user, insertTest);
    }

    @Test
    public void insertFail() throws Exception {
        boolean success = true;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            dao.insert(user);
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
            success = false;
        }

        // Check that duplicate insert bounced
        assertFalse(success);
    }

    @Test
    public void findPass() throws Exception {
        User findTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            findTest = dao.find(user.getUsername());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that we were able to find user in database
        assertNotNull(findTest);
        assertEquals(user, findTest);
    }

    @Test
    public void findFail() throws Exception {
        User findTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            findTest = dao.find("johnnyg");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that find doesn't find fake user
        assertNull(findTest);
    }

    @Test
    public void clearPass() throws Exception {
        User clearTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            dao.clear();
            clearTest = dao.find(user.getUsername());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that the user was deleted
        assertNull(clearTest);
    }

    @Test
    public void clearFail() throws Exception {
        User clearTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            dao.clear();
            clearTest = dao.find("");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that the table is completely empty
        assertNull(clearTest);
    }
}