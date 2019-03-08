package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.User;
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
        db.clear();
        db.createTables();
    }

    @Test
    public void insertPass() throws Exception {
        User insertTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            insertTest = dao.find(user.getUserName());
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
        Connection conn = db.openConnection();
        UserDAO userDAO = new UserDAO(conn);
        User user = userDAO.find("sam");
        assertNull(user);

        db.closeConnection(true);
    }

    @Test
    public void findPass() throws Exception {
        User findTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            findTest = dao.find(user.getUserName());
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
    public void authenticatePass() throws Exception {
        User authTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            authTest = dao.authenticate("sam", "irock");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that password worked
        assertEquals("sam", authTest.getUserName());
    }

    @Test
    public void authenticateFail() throws Exception {
        User authTest = null;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            authTest = dao.authenticate("sam", "urock");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that password failed
        assertNull(authTest);
    }

    @Test
    public void checkPass() throws Exception {
        boolean checkTest = true;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            checkTest = dao.check("sam");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that username was shown as taken
        assertFalse(checkTest);
    }

    @Test
    public void checkFail() throws Exception {
        boolean checkTest = true;
        try {
            Connection conn = db.openConnection();
            UserDAO dao = new UserDAO(conn);
            dao.insert(user);
            checkTest = dao.check("username");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that username is actually available
        assertTrue(checkTest);
    }
}