package login_and_sign_up_use_case_test;

import data_access.IUserDataAccess;
import data_access.entity_request_models.CommonUserDsRequestModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock database created in order to test the register test case with a temporary database.
 */
public class InMemoryUser implements IUserDataAccess {

    final private Map<String, CommonUserDsRequestModel> users = new HashMap<>();

    /**
     * Disregarding methods from the actual database classes irrelevant to the mock database.
     * @return null since the method isn't applicable to the mock database
     */
    @Override
    public CommonUserDsRequestModel getUser(String username) {
        return null;
    }

    /**
     * Mimic the act of checking whether a user exists in the database.
     * @return whether the user exists in the mock database.
     */
    @Override
    public boolean existsByName(String username) {
        return users.containsKey(username);
    }

    /**
     * Disregarding methods from the actual database classes irrelevant to the mock database.
     * @return null since the method isn't applicable to the mock database
     */
    @Override
    public Collection<CommonUserDsRequestModel> getAllUsers() {
        return null;
    }

    /**
     * Disregarding methods from the actual database classes irrelevant to the mock database.
     */
    @Override
    public void saveFlashcardSetID(String username, int FlashcardSetID) {

    }

    /**
     * Disregarding methods from the actual database classes irrelevant to the mock database.
     */
    @Override
    public void deleteFlashcardSetID(String username, int FlashcardSetID) {

    }

    /**
     * Mimic the act of saving a user as the database
     */
    @Override
    public void saveUser(CommonUserDsRequestModel user) {
        System.out.println("Save " + user.getUsername());
        users.put(user.getUsername(), user);
    }
}
