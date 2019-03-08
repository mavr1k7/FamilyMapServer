package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class PersonDAOTest {
    private Database db;
    private Person person;

    @Before
    public void setUp() throws Exception {
        person = new Person("12345", "johng", "john", "green", "m", "abe", "lincoln", "sue");
        db = new Database();
        db.clear();
        db.createTables();
    }

    @Test
    public void insertPass() throws Exception {
        Person insertTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            insertTest = dao.find(person.getPersonID());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that the user was entered correctly to database
        assertNotNull(insertTest);
        assertEquals(person, insertTest);
    }

    @Test
    public void insertFail() throws Exception {
        boolean success = true;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            dao.insert(person);
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
        Person findTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            findTest = dao.find(person.getPersonID());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that we were able to find user in database
        assertNotNull(findTest);
        assertEquals(person, findTest);
    }

    @Test
    public void findFail() throws Exception {
        Person findTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            findTest = dao.find("sams");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that find doesn't find fake user
        assertNull(findTest);
    }

    @Test
    public void updatePass() throws Exception {
        Person updateTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            dao.update(person.getPersonID(), "father", "mother");
            updateTest = dao.find(person.getPersonID());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that update worked
        assertEquals("father", updateTest.getFather());
        assertEquals("mother", updateTest.getMother());
    }

    @Test
    public void updateFail() throws Exception {
        Person updateTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            dao.update("sams", "father", "mother");
            updateTest = dao.find("sams");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that update didn't work
        assertNull(updateTest);
    }

    @Test
    public void findRelativesPass() throws Exception {
        List<Person> list = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            list = dao.findRelatives(person.getDescendant());
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that it found all relatives
        assertFalse(list.isEmpty());
    }

    @Test
    public void findRelativesFail() throws Exception {
        List<Person> list = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            list = dao.findRelatives("sams");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that it didn't find any relatives
        assertTrue(list.isEmpty());
    }

    @Test
    public void deleteRelativesPass() throws Exception {
        Person deleteTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            dao.deleteRelatives(person.getDescendant());
            deleteTest = dao.find("12345");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that it all relatives were deleted
        assertNull(deleteTest);
    }

    @Test
    public void deleteRelativesFail() throws Exception {
        Person deleteTest = null;
        try {
            Connection conn = db.openConnection();
            PersonDAO dao = new PersonDAO(conn);
            dao.insert(person);
            dao.deleteRelatives("sams");
            deleteTest = dao.find("12345");
            db.closeConnection(true);
        } catch (DAOException e) {
            db.closeConnection(false);
        }

        // Check that only users relatives were deleted
        assertNotNull(deleteTest);
    }
}