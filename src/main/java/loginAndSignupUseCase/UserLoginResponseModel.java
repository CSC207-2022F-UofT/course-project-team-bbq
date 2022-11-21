package loginAndSignupUseCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Login Response Model to return to the system for the user
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginResponseModel {
    String username;

    boolean isAdmin;

    Map<Integer, String[]> flashcardSets;

     /**
     * Constructs a quiz response model based on the following parameters.
     * @param loggedInUsername the username of the current user using the system
     * @param isAdmin the admin access of the current user
     * @param flashcardSets the flashcard sets map pertaining to the user obtained by the login interactor, where each id
     *                      pertains to its title and description
     */
    public UserLoginResponseModel(String loggedInUsername, boolean isAdmin, Map<Integer, String[]> flashcardSets){
        this.username = loggedInUsername;
        this.isAdmin = isAdmin;
        this.flashcardSets = flashcardSets;
    }

    /**
     * GETTERS for the system
     */
    public String getSignedInUsername() {
        return username;
    }
    public boolean getIsAdmin(){return isAdmin;}

    public Map<Integer, String[]> getFlashcardSets(){

        return flashcardSets;
    }
}
