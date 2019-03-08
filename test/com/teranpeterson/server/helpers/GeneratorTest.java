package com.teranpeterson.server.helpers;

import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.PersonDAO;
import com.teranpeterson.server.model.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Before
    public void setUp() throws Exception {
//        Database db = new Database();
//        Connection conn = db.openConnection();
//        PersonDAO pDAO = new PersonDAO(conn);
//        Person newPerson = new Person("12345", "John", "Cena", "m");
//        pDAO.insert(newPerson);
//        db.closeConnection(true);
        }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() throws Exception {
        Generator g = new Generator("username");
        g.generate("12345", 4);
    }
}