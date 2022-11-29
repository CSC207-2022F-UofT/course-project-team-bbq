package data_access;

import data_access.entity_request_models.CommonUserDsRequestModel;

import java.util.Collection;

/**
 * User Data Access Interface.
 * Application Business Rules
 * @author Justin Li
 */

public interface IUserDataAccess {

    /**
     * @param username the user's username
     * @return the User object who has username
     */
    CommonUserDsRequestModel getUser(String username);
    /**
     * @param username the user's username
     * @return True if the user's username is in the database
     */
    boolean existsByName(String username);
    /**
     * @return all the User objects in the database
     */
    Collection<CommonUserDsRequestModel> getAllUsers();
    /**
     * @param username the user's username
     * @param flashcardSetID the id of the flashcard set that the user would like to create.
     */
    void saveFlashcardSetID(String username, int flashcardSetID);
    /**
     * @param username the user's username
     * @param flashcardSetID the id of the flashcard set that the user would like to delete.
     */
    void deleteFlashcardSetID(String username, int flashcardSetID);
    /**
     * @param user the User object.
     */
    void saveUser(CommonUserDsRequestModel user);
}
