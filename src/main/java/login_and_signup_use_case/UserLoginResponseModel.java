package login_and_signup_use_case;

import java.util.Map;

/**
 * Login Response Model to return to the system for the user
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginResponseModel {
    final String username;

    final String password;

    final boolean isAdmin;

    final Map<Integer, String[]> flashcardSets;

     /**
     * Constructs a quiz response model based on the following parameters.
     * @param loggedInUsername the username of the current user using the system
     * @param isAdmin the admin access of the current user
     * @param flashcardSets the flashcard sets map pertaining to the user obtained by the login interactor, where each id
     *                      pertains to its title and description
     */
    public UserLoginResponseModel(String loggedInUsername, String password, boolean isAdmin, Map<Integer, String[]> flashcardSets){
        this.username = loggedInUsername;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSets = flashcardSets;
    }

    /**
     * Gets the username of the user as saved in the database
     * @return the username that the user stored
     */
    public String getSignedInUsername() {
        return username;
    }

    /**
     * Gets the password of the user as saved in the database
     * @return the password that the user stored
     */
    public String getPassword() { return password; }

    /**
     * Gets whether the user is saved as an admin in the database
     * @return the admin level access of the user as stored in the database
     */
    public boolean getIsAdmin(){return isAdmin;}

    /**
     * Gets the list of flashcard set ids belonging to the user and onverts it into a map with the id as
     * a key and the title and description of the flashcard set as the value in a string array format
     * @return map of the flashcard sets belonging to the user
     */
    public Map<Integer, String[]> getFlashcardSets(){

        return flashcardSets;
    }
}
