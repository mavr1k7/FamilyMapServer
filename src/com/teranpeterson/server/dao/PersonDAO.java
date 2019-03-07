package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Person;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller used to connect to and modify persons in the database
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class PersonDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates a dao with a database connection
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a new person to the database
     *
     * @param person Person to add to the database
     * @throws DAOException Problem executing sql statements
     */
    public void insert(Person person) throws DAOException {
        String sql = "INSERT INTO `Persons`(`person_id`,`descendant`,`firstName`,`lastName`,`gender`,`father`,`mother`,`spouse`) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getDescendant());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFather());
            stmt.setString(7, person.getMother());
            stmt.setString(8, person.getSpouse());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to insert person '" + person.getPersonID() + "' into database");
        }
    }

    /**
     * Find a person in the database
     *
     * @param personID ID of the person to find
     * @return If the person is found, return it, otherwise null
     * @throws DAOException Problem executing sql statements
     */
    public Person find(String personID) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Persons` WHERE `person_id` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            result = stmt.executeQuery();
            if (result.next()) {
                return new Person(result.getString("person_id"), result.getString("descendant"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("gender"), result.getString("father"), result.getString("mother"), result.getString("spouse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to find person '" + personID + "' in database");
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * Return a list of all persons related to a user
     *
     * @param userName userName of the user to find relatives for
     * @return List of relatives, or null if none are found
     */
    public List<Person> findRelatives(String userName) throws DAOException {
        List<Person> list = new ArrayList<>();
        ResultSet result = null;

        String sql = "SELECT * FROM `Persons` WHERE `descendant` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            result = stmt.executeQuery();
            while (result.next()) {
                list.add(new Person(result.getString("person_id"), result.getString("descendant"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("gender"), result.getString("father"), result.getString("mother"), result.getString("spouse")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to find relatives for user '" + userName + "' in database");
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }

    /**
     * Delete all persons related to a user
     *
     * @param userName userName of the user to delete relatives for
     * @throws DAOException Problem executing sql statements
     */
    public void deleteRelatives(String userName) throws DAOException {
        String sql = "DELETE * FROM `Persons` WHERE `descendant` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to delete relatives for user '" + userName + "' from database");
        }
    }

    /**
     * Delete all persons from the database
     *
     * @throws DAOException Problem executing sql statements
     */
    public void clear() throws DAOException {
        String sql = "DELETE * FROM `Persons`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to clear persons from database");
        }
    }
}
