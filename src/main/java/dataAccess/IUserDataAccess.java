package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;

// use case layer

public interface IUserDataAccess {
    //THE DS GATEWAY

    /**
     * @param username the user's username
     * @return the User object who has username
     */
    CommonUserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    void saveUser(CommonUserDsRequestModel user);

//    boolean correctAdminKey(String inputtedAdminKey);
}
