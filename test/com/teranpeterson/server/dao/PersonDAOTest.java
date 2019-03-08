package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class PersonDAOTest {

    private Database db;
    private Person person;

    @Before
    public void setUp() throws Exception {
        db = new Database();
        person = new Person("sadf920d", "sams", "johnny", "g", "m", "abe", "lincoln", "sue");
        db.createTables();
    }

    @After
    public void tearDown() throws Exception {
        db.clear();
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
}