package dataAccess;

import entityRequestModels.UserDsRequestModel;

// use case layer

public interface IUserDataAccess {
    static String path = "src/data/Users.csv";

    /**
     * @param username the user's username
     * @return the User object who has username
     */
    UserDsRequestModel getUser(String username);

    boolean existsByName(String username);

    void saveUser(UserDsRequestModel user);
}
