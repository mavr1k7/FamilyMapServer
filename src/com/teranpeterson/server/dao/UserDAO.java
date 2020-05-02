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
 * @version v0.1.2
 */
public class UserDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates a dao with a database connection
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adds a new user to the database
     *
     * @param user User to add to the database
     * @throws DAOException Problem executing sql statements
     */
    public void insert(User user) throws DAOException {
        String sql = "INSERT INTO `Users`(`userName`,`password`,`email`,`firstName`,`lastname`,`gender`,`person_id`) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to insert user '" + user.getUserName() + "' into database");
        }
    }

    /**
     * Find a user in the database
     *
     * @param userName UserName of the user to find
     * @return If the user is found, return it, otherwise null
     * @throws DAOException Problem executing sql statements
     */
    public User find(String userName) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Users` WHERE `userName` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            result = stmt.executeQuery();
            if (result.next()) {
                return new User(result.getString("userName"), result.getString("password"), result.getString("email"), result.getString("firstName"),
                        result.getString("lastname"), result.getString("gender"), result.getString("person_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to find user '" + userName + "' in database");
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
     * Checks if a given userName and password match and are in the database
     *
     * @param userName UserName of user logging in
     * @param password Password attempt to check
     * @return True if the login credentials match, otherwise false
     * @throws DAOException Problem executing sql statements
     */
    public User authenticate(String userName, String password) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Users` WHERE `userName` = ? AND `password` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.setString(2, password);

            result = stmt.executeQuery();
            if (result.next()) {
                return new User(result.getString("userName"), result.getString("password"), result.getString("email"), result.getString("firstName"),
                        result.getString("lastname"), result.getString("gender"), result.getString("person_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to authenticate user '" + userName + "' in database");
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
     * Checks if a given userName is in use
     *
     * @param userName UserName of user logging in
     * @return True if the login credentials match, otherwise false
     * @throws DAOException Problem executing sql statements
     */
    public boolean check(String userName) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Users` WHERE `userName` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);

            result = stmt.executeQuery();
            if (result.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to check userName '" + userName + "' in database");
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return true;
    }
}
