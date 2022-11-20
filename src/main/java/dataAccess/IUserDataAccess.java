package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;

import java.util.Collection;

// use case layer

public interface IUserDataAccess {


    /**
     * @param username the user's username
     * @return the User object who has username
     */
    CommonUserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    Collection<CommonUserDsRequestModel> getAllUsers();

    void saveFlashcardSetID(String username, int FlashcardSetID);

    void deleteFlashcardSetID(String username, int FlashcardSetID);

    void saveUser(CommonUserDsRequestModel user);
}
