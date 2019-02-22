package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Person;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller used to connect to and modify persons in the database
 *
 * @author Teran Peterson
 * @version v0.0.3
 */
public class PersonDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates an empty person dao
     */
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Add a new person to the database
     * @param person Person to add to the database
     */
    public void insert(Person person) throws DAOException {
        String sql = "INSERT INTO `Persons`(`person_id`,`descendant`,`firstname`,`lastname`,`gender`,`father`,`mother`,`spouse`) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getDescendant());
            stmt.setString(3, person.getFirstname());
            stmt.setString(4, person.getLastname());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFather());
            stmt.setString(7, person.getMother());
            stmt.setString(8, person.getSpouse());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Couldn't insert person into database");
        }
    }

    /**
     * Find a person in the database
     * @param personID ID of the person to find
     * @return If the person is found, return it, otherwise null
     */
    public Person find(String personID) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Persons` WHERE `person_id` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            result = stmt.executeQuery();
            if (result.next()) {
                return new Person(result.getString("person_id"), result.getString("descendant"), result.getString("firstname"),
                        result.getString("lastname"), result.getString("gender"), result.getString("father"), result.getString("mother"), result.getString("spouse"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Couldn't find person in the database");
        } finally {
            if(result != null) {
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
     * @param username Username of the user to find relatives for
     * @return List of relatives, or null if none are found
     */
    public ArrayList<Person> findRelatives(String username) {
        return null;
    }

    /**
     * Delete all persons related to a user
     * @param username Username of the user to delete relatives for
     */
    public void deleteRelatives(String username) {

    }

    /**
     * Delete all persons from the database
     */
    public void clear() throws DAOException {
        String sql = "DELETE * FROM `Persons`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Couldn't clear persons from the database");
        }
    }
}
