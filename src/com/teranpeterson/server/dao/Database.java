package com.teranpeterson.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates and maintains a connection the database used by the server
 *
 * @author Teran Peterson
 * @version v0.1.7
 */
public class Database {
    /**
     * Connection to database
     */
    private Connection conn;

    static {
        try {
            Class.forName("org.sqlite.JDBC"); // Driver used to connect to the database
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a connection to the database
     *
     * @throws DAOException Problem establishing connection
     */
    public Connection openConnection() throws DAOException {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:resources/database/FamilyMap.db");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to establish connection to database");
        }

        return conn;
    }

    /**
     * Closes the connection to the database
     *
     * @param commit True pushes changes to database, false rolls back
     * @throws DAOException Problem closing connection
     */
    public void closeConnection(boolean commit) throws DAOException {
        try {
            if (commit) {
                conn.commit();
            } else {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to close connection to database");
        }
    }

    public void createTables() throws DAOException {
        // Create Persons table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS 'Persons' ( `person_id` TEXT NOT NULL UNIQUE, `descendant` TEXT, " +
                    "`firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `gender` TEXT NOT NULL CHECK(gender == 'f' OR gender == 'm'), " +
                    "`father` TEXT, `mother` TEXT, `spouse` TEXT, PRIMARY KEY(`person_id`), FOREIGN KEY(`descendant`) REFERENCES `Users`(`userName`) )";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to create persons table");
        }

        // Create Users table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS 'Users' ( `userName` TEXT NOT NULL UNIQUE, `password` TEXT NOT NULL, " +
                    "`email` TEXT NOT NULL UNIQUE, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `gender` TEXT NOT NULL CHECK(gender == 'f' OR gender == 'm'), " +
                    "`person_id` TEXT NOT NULL UNIQUE, PRIMARY KEY(`userName`), FOREIGN KEY(`person_id`) REFERENCES `Persons`(`person_id`) )";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to create users table");
        }

        // Create Events table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `Events` ( `event_id` TEXT NOT NULL UNIQUE, `descendant` TEXT, " +
                    "`person_id` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `country` TEXT NOT NULL, " +
                    "`city` TEXT NOT NULL, `type` TEXT NOT NULL, `year` INTEGER NOT NULL, FOREIGN KEY(`descendant`) REFERENCES " +
                    "`Users`(`userName`), PRIMARY KEY(`event_id`), FOREIGN KEY(`person_id`) REFERENCES `Persons`(`person_id`) )";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to create events table");
        }

        // Create AuthTokens table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS `AuthTokens` ( `token` TEXT NOT NULL UNIQUE, `userName` TEXT NOT NULL, PRIMARY KEY(`token`) )";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to create auth tokens table");
        }
    }

    public void clear() throws DAOException {
        // Delete Persons table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS `Persons`";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to drop persons table");
        }

        // Delete Users table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS `Users`";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to drop users table");
        }

        // Delete Events table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS `Events`";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to drop events table");
        }

        // Delete Users table
        openConnection();
        try (Statement stmt = conn.createStatement()) {
            String sql = "DROP TABLE IF EXISTS `AuthTokens`";

            stmt.executeUpdate(sql);
            closeConnection(true);
        } catch (DAOException e) {
            closeConnection(false);
            throw e;
        } catch (SQLException e) {
            closeConnection(false);
            throw new DAOException("ERROR: Unable to drop auth tokens table");
        }
    }
}