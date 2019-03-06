package com.teranpeterson.server.service;

import com.teranpeterson.server.dao.AuthTokenDAO;
import com.teranpeterson.server.dao.DAOException;
import com.teranpeterson.server.dao.Database;
import com.teranpeterson.server.dao.UserDAO;
import com.teranpeterson.server.model.AuthToken;
import com.teranpeterson.server.model.User;
import com.teranpeterson.server.request.LoginRequest;
import com.teranpeterson.server.result.LoginResult;

import java.sql.Connection;

/**
 * Logs in the user and returns an auth token.
 *
 * @author Teran Peterson
 * @version v0.1.1
 */
public class LoginService extends Service {
    /**
     * Creates a blank login service object
     */
    public LoginService() {

    }

    /**
     * Logs in the user and returns an auth token.
     *
     * @param request Information about the user logging in
     * @return Information the user with an auth token for the active session
     */
    public LoginResult login(LoginRequest request) {
        Database db = new Database();
        try {
            db.createTables();
            Connection conn = db.openConnection();
            UserDAO uDAO = new UserDAO(conn);
            User user = uDAO.authenticate(request.getUsername(), request.getPassword());
            if (user != null) {
                String token = super.login(conn, user.getUsername());
                db.closeConnection(true);
                return new LoginResult(token, user.getUsername(), user.getPersonID());
            } else {
                db.closeConnection(false);
                return new LoginResult("ERROR: Invalid username or password");
            }
        } catch (DAOException e) {
            try {
                db.closeConnection(false);
                return new LoginResult(e.getMessage());
            } catch (DAOException d) {
                return new LoginResult(d.getMessage());
            }
        }
    }
}
