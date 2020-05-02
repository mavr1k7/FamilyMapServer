package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class EventDAOTest {
    private Database db;
    private Event event;

    @Before
    public void setUp() throws Exception {
        event = new Event("12345", "johng", "john", 1, 1, "country", "city", "type", 2019);
        db = new Database();
        db.clear();
        db.createTables();
    }

    @Test
    public void insertPass() throws Exception {
        Event insertTest = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            insertTest = dao.find(event.getEventID());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that the user was entered correctly to database
        assertNotNull(insertTest);
        assertEquals(event, insertTest);
    }

    @Test
    public void insertFail() throws Exception {
        boolean success = true;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            dao.insert(event);
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
            success = false;
        }

        // Check that duplicate insert bounced
        assertFalse(success);
        Connection conn = db.openConnection();
        EventDAO eventDAO = new EventDAO(conn);
        Event event = eventDAO.find("12345");
        assertNull(event);

        db.closeConnection(true);
    }

    @Test
    public void findPass() throws Exception {
        Event findTest = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            findTest = dao.find(event.getEventID());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that we were able to find user in database
        assertNotNull(findTest);
        assertEquals(event, findTest);
    }

    @Test
    public void findFail() throws Exception {
        Event findTest = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            findTest = dao.find("sams");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that find doesn't find fake user
        assertNull(findTest);
    }

    @Test
    public void personEventsPass() throws Exception {
        List<Event> list = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            list = dao.personEvents(event.getDescendant());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that it found all events
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    public void personEventsFail() throws Exception {
        List<Event> list = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            list = dao.personEvents("sams");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that it didn't find any events
        assertTrue(list.isEmpty());
    }

    @Test
    public void deleteEventsPass() throws Exception {
        Event deleteTest = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            dao.deleteEvents(event.getDescendant());
            deleteTest = dao.find("12345");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that all events were deleted
        assertNull(deleteTest);
    }

    @Test
    public void deleteEventsFail() throws Exception {
        Event deleteTest = null;
        try {
            Connection conn = db.openConnection();
            EventDAO dao = new EventDAO(conn);
            dao.insert(event);
            dao.deleteEvents("sams");
            deleteTest = dao.find("12345");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that only events related to the user were deleted
        assertNotNull(deleteTest);
        assertEquals("johng", deleteTest.getDescendant());
    }
}