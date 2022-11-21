package loginAndSignupUseCase;

import java.util.HashMap;

/**
 * Login Response Model to return to the system for the user
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginResponseModel {
    String username;

    boolean isAdmin;

    HashMap<Integer, String[]> flashcardSets;

    /**
     * Constructs a quiz response model based on the following parameters.
     * @param loggedInUsername the username of the current user using the system
     * @param isAdmin the admin access of the current user
     * @param flashcardSets the flashcard sets pertaining to the user obtained by the login interactor
     */
    public UserLoginResponseModel(String loggedInUsername, boolean isAdmin, HashMap<Integer, String[]> flashcardSets){
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
    public HashMap<Integer, String[]> getFlashcardSets(){
        return flashcardSets;
    }
}
