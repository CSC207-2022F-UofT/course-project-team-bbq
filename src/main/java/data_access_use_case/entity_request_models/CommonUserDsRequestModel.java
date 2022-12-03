package data_access_use_case.entity_request_models;

import java.util.List;
/**
 * Common User Request Model.
 * Application Business Rules
 * @author Justin Li
 */

public class CommonUserDsRequestModel {
    private final String username;
    private final String password;
    private final boolean isAdmin;
    private final List<Integer> flashcardSetIds;

    /**
     * Creates a flashcard response model based on the following parameters.
     * @param username the user's username.
     * @param password the user's password.
     * @param isAdmin true if the user is an admin user.
     * @param flashcardSetIds the list of flashcard set ids created by the user.
     */
    public CommonUserDsRequestModel(String username, String password, boolean isAdmin, List<Integer> flashcardSetIds){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }

    /**
     * Gets the username of the user
     * @return the username of the user
     */
    public String getUsername(){
        return username;
    }
    /**
     * Gets the password of the user
     * @return the password of the user
     */
    public String getPassword(){
        return password;
    }
    /**
     * Gets if the user is an admin user
     * @return true if the user is an admin user.
     */
    public boolean getIsAdmin(){
        return isAdmin;
    }
    /**
     * Gets the list of flashcard set ids created by the user
     * @return the list of flashcard set ids created by the user
     */
    public List<Integer> getFlashcardSetIds(){
        return flashcardSetIds;
    }
}
