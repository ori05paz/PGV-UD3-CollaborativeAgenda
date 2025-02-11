package net.salesianos.server;

/**
 * Represents a system user with authentication credentials and permissions.
 */
public class User {
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Constructor for User.
     * 
     * @param username The username of the user.
     * @param password The password associated with the user.
     * @param isAdmin  Whether the user has administrative privileges.
     */
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Retrieves the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Validates if the entered password matches the stored password.
     * 
     * @param inputPassword The password entered for authentication.
     * @return True if the password is correct, false otherwise.
     */
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    /**
     * Checks if the user has administrative privileges.
     * 
     * @return True if the user is an administrator, false otherwise.
     */
    public boolean isAdmin() {
        return isAdmin;
    }
}