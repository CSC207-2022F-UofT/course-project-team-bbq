package loginAndSignupUseCase;

import java.util.HashMap;
import java.util.Map;

public class UserLoginResponseModel {
    String username;

    boolean isAdmin;

    HashMap<Integer, String[]> flashcardSets;

    public UserLoginResponseModel(String loggedInUsername, boolean isAdmin, HashMap<Integer, String[]> flashcardSets){
        this.username = loggedInUsername;
        this.isAdmin = isAdmin;
        this.flashcardSets = flashcardSets;
    }

    public String getSignedInUsername() {
        return username;
    }
    public boolean getIsAdmin(){return isAdmin;}

    public HashMap<Integer, String[]> getFlashcardSets(){
        return flashcardSets;
    }
}
