package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.UserDsRequestModel;

// use case layer

public interface IUserDataAccess {
    //THE DS GATEWAY


    /**
     * @param username the user's username
     * @return the User object who has username
     */
    UserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    void saveUser(CommonUserDsRequestModel user);
}
