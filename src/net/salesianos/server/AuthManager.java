package net.salesianos.server;

import java.util.HashMap;

/**
 * Manages user authentication and access control.
 * Stores registered users and validates login credentials.
 */
public class AuthManager {
    private static final HashMap<String, User> users = new HashMap<>();

    static {
        users.put("admin", new User("admin", "1234", true));
        users.put("user1", new User("user1", "abcd", false));
    }

    /**
     * Authenticates a user based on username and password.
     * 
     * @param username The username entered.
     * @param password The password entered.
     * @return The User object if authentication is successful, null otherwise.
     */
    public static User authenticate(String username, String password) {
        if (users.containsKey(username) && users.get(username).checkPassword(password)) {
            return users.get(username);
        }
        return null;
    }
}