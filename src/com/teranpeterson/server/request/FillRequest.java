package com.teranpeterson.server.request;

/**
 * Contains information about the Fill Request
 *
 * @author Teran Peterson
 * @version v0.0.1
 */
public class FillRequest {
    /**
     * User's username
     */
    private String username;
    /**
     * Number of generations to create
     */
    private int generations;

    /**
     * Creates a fill request with the given information and default 4 generations
     * @param username User's username
     */
    public FillRequest(String username) {
        this(username, 4);
    }

    /**
     * Creates a fill request with the given information
     * @param username User's username
     * @param generations Number of generations to create
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    /**
     * Gets the user's username
     * @return User's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username
     * @param username User's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the number of generations to create
     * @return Number of generations to create
     */
    public int getGenerations() {
        return generations;
    }

    /**
     * Sets the number of generations to create
     * @param generations Number of generations to create
     */
    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
