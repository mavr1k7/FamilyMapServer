package com.teranpeterson.server.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseTest {
    private Database db;

    @Before
    public void setUp() throws Exception {
        db = new Database();
    }

    @After
    public void tearDown() throws Exception {
        db.clear();
    }

    @Test
    public void createTables() {
        try {
            db.createTables();
        } catch (DAOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Create");
        }
    }

    @Test
    public void clear() {
        try {
            db.clear();
        } catch (DAOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Clear");
        }
    }
}