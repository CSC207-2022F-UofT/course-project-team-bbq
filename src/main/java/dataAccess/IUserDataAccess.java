package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;

// use case layer

public interface IUserDataAccess {


    /**
     * @param username the user's username
     * @return the User object who has username
     */
    CommonUserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    void saveFlashcardSetID(String username, int FlashcardSetID);

    void saveUser(CommonUserDsRequestModel user);
}
