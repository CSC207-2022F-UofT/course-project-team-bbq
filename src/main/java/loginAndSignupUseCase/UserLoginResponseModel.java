package loginAndSignupUseCase;

import java.util.HashMap;
import java.util.Map;

public class UserLoginResponseModel {
    String username;

    boolean isAdmin;

    Map<Integer, String[]> flashcardSets;

    public UserLoginResponseModel(String loggedInUsername, boolean isAdmin, Map<Integer, String[]> flashcardSets){
        this.username = loggedInUsername;
        this.isAdmin = isAdmin;
        this.flashcardSets = flashcardSets;
    }

    public String getSignedInUsername() {
        return username;
    }
    public boolean getIsAdmin(){return isAdmin;}

    public Map<Integer, String[]> getFlashcardSets(){
        return flashcardSets;
    }
}
