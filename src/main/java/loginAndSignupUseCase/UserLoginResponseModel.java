package loginAndSignupUseCase;

import java.util.Map;

public class UserLoginResponseModel {
    private final String username;

    private final String password;
    private final boolean isAdmin;

    private final Map<Integer, String[]> flashcardSets;

    public UserLoginResponseModel(String loggedInUsername, String password,
                                  boolean isAdmin, Map<Integer, String[]> flashcardSets){
        this.username = loggedInUsername;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
