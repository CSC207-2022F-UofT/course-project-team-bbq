package data_access_use_case;

import data_access_use_case.entity_request_models.CommonUserDsRequestModel;

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
     * @param flashcardSetId the id of the flashcard set that the user would like to create.
     */
    void saveFlashcardSetID(String username, int flashcardSetId);
    /**
     * @param username the user's username
     * @param flashcardSetId the id of the flashcard set that the user would like to delete.
     */
    void deleteFlashcardSetID(String username, int flashcardSetId);
    /**
     * @param user the User object.
     */
    void saveUser(CommonUserDsRequestModel user);
}
