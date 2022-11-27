package entities;

/**
 * A class to call the constructor of the CommonUser and create a Common User object to ensure Clean Architecture
 *<p>
 * Enterprise Business Rules
 * @author Aryan Chablani
 */

public class CommonUserFactory implements UserFactory {

    /**
     * A factory to create a Common User object
     * Precondition: No common user for this username exists yet
     * @param username the username of the use
     * @param password the password of the use
     * @param isAdmin whether the user is an admin or not
     * @return a CommonUser object
     * Post Condition: A common user will be instantiated.
     */
    @Override
    public User create(String username, String password, boolean isAdmin) {
        return new CommonUser(username, password, isAdmin);
    }
}
