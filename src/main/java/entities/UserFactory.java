package entities;

/**
 * A class to call the constructor of the CommonUser and create a Common User object to ensure Clean Architecture
 *<p>
 * Enterprise Business Rules
 * @author Aryan Chablani
 */

public interface UserFactory {

    /**
     * A factory to create a user object
     * Precondition: No User for this username exists yet
     * @param username the username of the user
     * @param password the password of the user
     * @param isAdmin whether the user is an admin or not
     * @return a User object
     * Post Condition: A User will be creates.
     */
    User create(String username, String password, boolean isAdmin);
}
