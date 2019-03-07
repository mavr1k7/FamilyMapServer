package com.teranpeterson.server.dao;

import com.teranpeterson.server.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller used to connect to and modify events in the database
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class EventDAO {
    /**
     * Connection to database
     */
    private Connection conn;

    /**
     * Creates a dao with a database connection
     */
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Add an event to the database
     *
     * @param event Event to add to the database
     * @throws DAOException Problem executing sql statements
     */
    public void insert(Event event) throws DAOException {
        String sql = "INSERT INTO `Events`(`event_id`,`descendant`,`person_id`,`latitude`,`longitude`,`country`,`city`,`eventType`,`year`) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getDescendant());
            stmt.setString(3, event.getPersonID());
            stmt.setDouble(4, event.getLatitude());
            stmt.setDouble(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to insert event '" + event.getEventID() + "' into database");
        }
    }

    /**
     * Find an event in the database
     *
     * @param eventID ID of the event to find
     * @return If the event is found, return it, otherwise return null
     * @throws DAOException Problem executing sql statements
     */
    public Event find(String eventID) throws DAOException {
        ResultSet result = null;

        String sql = "SELECT * FROM `Events` WHERE `event_id` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            result = stmt.executeQuery();
            if (result.next()) {
                return new Event(result.getString("event_id"), result.getString("descendant"), result.getString("person_id"),
                        result.getDouble("latitude"), result.getDouble("longitude"), result.getString("country"),
                        result.getString("city"), result.getString("eventType"), result.getInt("year"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to find event '" + eventID + "' in database");
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
     * Return a list of all events related to a user
     *
     * @param userName UserName of the user to find events for
     * @return List of all events related to person, or null if none are found
     * @throws DAOException Problem executing sql statements
     */
    public List<Event> personEvents(String userName) throws DAOException {
        List<Event> list = new ArrayList<>();
        ResultSet result = null;

        String sql = "SELECT * FROM `Events` WHERE `descendant` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            result = stmt.executeQuery();
            while (result.next()) {
                list.add(new Event(result.getString("event_id"), result.getString("descendant"), result.getString("person_id"),
                        result.getDouble("latitude"), result.getDouble("longitude"), result.getString("country"),
                        result.getString("city"), result.getString("eventType"), result.getInt("year")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to find events for person '" + userName + "' in database");
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
     * Delete all events associated with a user
     *
     * @param userName User to delete events for
     * @throws DAOException Problem executing sql statements
     */
    public void deleteEvents(String userName) throws DAOException {
        String sql = "DELETE * FROM `Events` WHERE `descendant` = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to delete events for user '" + userName + "' from database");
        }
    }

    /**
     * Delete all events from database
     *
     * @throws DAOException Problem executing sql statements
     */
    public void clear() throws DAOException {
        String sql = "DELETE * FROM `Events`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("ERROR: Unable to clear events from database");
        }
    }
}
