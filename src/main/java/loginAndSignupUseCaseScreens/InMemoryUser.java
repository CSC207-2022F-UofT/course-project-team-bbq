package loginAndSignupUseCaseScreens;

import dataAccess.IUserDataAccess;
import entityRequestModels.CommonUserDsRequestModel;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements IUserDataAccess {

    private Map<String, CommonUserDsRequestModel> users = new HashMap<>();

    @Override
    public CommonUserDsRequestModel getUser(String username) {
        return users.get(username);
    }

    /**
     * @param identifier
     * @return Utilise the identifier to find if a user exists in memory.
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param requestModel
     */
    @Override
    public void saveUser(CommonUserDsRequestModel requestModel) {
        System.out.println("Save " + requestModel.getName());
        users.put(requestModel.getName(), requestModel);
    }
}
