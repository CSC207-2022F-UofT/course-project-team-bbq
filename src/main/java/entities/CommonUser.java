package entities;

import java.util.ArrayList;

/**
 * A user that has their username, password, whether they have admin access, and the list of all the ids
 * of the flashcard sets that belong to them
 *<p>
 * Enterprise Business Rules
 * @author Aryan Chablani
 */

public class CommonUser implements User{

    private String username;
    private String password;
    private boolean isAdmin;
    final String ADMIN_KEY = "BuiltDifferent";
    final ArrayList<Integer> flashcardSetIds;

    /**
     * Constructor for Common User
     * Precondition: No common user for this username exists yet
     * @param username the username of the user
     * @param password the password of the user
     * @param isAdmin whether the user is an admin or not
     * Post Condition: A common user will be instantiated.
     */
    public CommonUser(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = new ArrayList<>();
    }

    //GETTERS AND SETTERS
    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    @Override
    public ArrayList<Integer> getFlashcardSetIds() {
        return flashcardSetIds;
    }

    /**
     * Checks whether the password is valid by not being null and greater by 5
     * @return whether the password is valid
     */
    @Override
    public boolean passwordIsValid() {
        return password != null && password.length() > 5;
    }

    /**
     * Checks whether the admin key entered is similar to the actual admin key in the business rules
     * @param adminKey the username of the use
     * @return whether the admin is the same as the one stored in the enterprise rules
     */
    @Override
    public boolean adminKeyValid(String adminKey) {
        return adminKey.equals(ADMIN_KEY);
    }


}
