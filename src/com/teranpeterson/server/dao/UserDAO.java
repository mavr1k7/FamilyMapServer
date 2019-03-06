package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller used to connect to and modify users in the database
 *
 * @author Teran Peterson
 * @version v0.0.4
 */
public class UserDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates an empty user dao
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a new user to the database
     * @param user User to add to the database
     * @throws DAOException Problem executing sql statements
     */
    public void insert(User user) throws DAOException {
        String sql = "INSERT INTO `Users`(`username`,`password`,`email`,`firstname`,`lastname`,`gender`,`person_id`) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstname());
            stmt.setString(5, user.getLastname());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Couldn't insert user into database");
        }
    }

    /**
     * Find a user in the database
     * @param username Username of the user to find
     * @return If the user is found, return it, otherwise null
     * @throws DAOException Problem executing sql statements
     */
    public User find(String username) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Users` WHERE `username` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            result = stmt.executeQuery();
            if (result.next()) {
                return new User(result.getString("username"), result.getString("password"), result.getString("email"), result.getString("firstname"),
                        result.getString("lastname"), result.getString("gender"), result.getString("person_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Couldn't find user in the database");
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
     * Checks if a given username and password match and are in the database
     * @param username Username of user logging in
     * @param password Password attempt to check
     * @return True if the login credentials match, otherwise false
     */
    public User authenticate(String username, String password) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Users` WHERE `username` = ? AND `password` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            result = stmt.executeQuery();
            if (result.next()) {
                return new User(result.getString("username"), result.getString("password"), result.getString("email"), result.getString("firstname"),
                        result.getString("lastname"), result.getString("gender"), result.getString("person_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Couldn't find user in the database");
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
     * Delete all users from the database
     * @throws DAOException Problem executing sql statements
     */
    public void clear() throws DAOException {
        String sql = "DELETE * FROM `Users`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("ERROR: Couldn't clear users from the database");
        }
    }
}
