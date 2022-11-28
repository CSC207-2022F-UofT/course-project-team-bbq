package entities;
import java.util.*;

/**
 * A user interface that has their username, password, whether they have admin access, and the list of all the ids
 * of the flashcard sets that belong to them
 *<p>
 * Enterprise Business Rules
 * @author Aryan Chablani
 */
public interface User {
    String username = null;
    String password = null;
    boolean isAdmin = false;

    /**
     * Gets the username belonging to the user
     */
    String getUsername();

    /**
     * Gets the password belonging to the user
     */
    String getPassword();

    /**
     * Gets whether the user is an admin user
     */
    boolean getIsAdmin();

    /**
     * Gets the list of flashcard set ids belonging to the user
     */
    ArrayList<Integer> getFlashcardSetIds();

    /**
     * Checks whether the password is valid by not being null and greater by 5
     * @return whether the password is valid
     */
    boolean passwordIsValid();

    /**
     * Checks whether the admin key entered is similar to the actual admin key in the business rules
     * @param adminKey the username of the use
     * @return whether the admin is the same as the one stored in the enterprise rules
     */
    boolean adminKeyValid(String adminKey);
}
