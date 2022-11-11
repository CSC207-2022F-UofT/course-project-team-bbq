package dataAccess;

import entityRequestModels.UserDsRequestModel;

// use case layer

public interface IUserDataAccess {


    /**
     * @param username the user's username
     * @return the User object who has username
     */
    UserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    void saveFlashcardsetID(String username, int FlashcardSetID);

    void saveUser(UserDsRequestModel user);
}
