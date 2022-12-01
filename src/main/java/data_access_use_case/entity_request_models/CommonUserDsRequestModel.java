package data_access_use_case.entity_request_models;

import java.util.List;
/**
 * Common User Request Model.
 * Application Business Rules
 * @author Justin Li
 */

public class CommonUserDsRequestModel {
    private final String username;
    private final String password;
    private final boolean isAdmin;
    private final List<Integer> flashcardSetIds;

    public CommonUserDsRequestModel(String username, String password, boolean isAdmin, List<Integer> flashcardSetIds){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public List<Integer> getFlashcardSetIds(){
        return flashcardSetIds  ;
    }
}
