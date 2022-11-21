package login_and_sign_up_use_case;

import dataAccess.IUserDataAccess;
import entityRequestModels.CommonUserDsRequestModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUser implements IUserDataAccess {

    final private Map<String, CommonUserDsRequestModel> users = new HashMap<>();
    @Override
    public CommonUserDsRequestModel getUser(String username) {
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    @Override
    public Collection<CommonUserDsRequestModel> getAllUsers() {
        return null;
    }

    @Override
    public void saveFlashcardSetID(String username, int FlashcardSetID) {

    }

    @Override
    public void deleteFlashcardSetID(String username, int FlashcardSetID) {

    }

    @Override
    public void saveUser(CommonUserDsRequestModel user) {
        System.out.println("Save " + user.getUsername());
        users.put(user.getUsername(), user);
    }
}
