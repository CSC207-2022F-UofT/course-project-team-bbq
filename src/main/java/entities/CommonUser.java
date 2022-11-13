package entities;

import java.util.Map;

public class CommonUser extends User{


    public CommonUser(String username, String password, boolean isAdmin, Map<Integer, String[]> flashcardSets) {
        super(username, password, isAdmin, flashcardSets);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFlashcardSets(Map<Integer, String[]> flashcardSets) {
        this.flashcardSets = flashcardSets;
    }

    public String getPassword() {
        return password;
    }

    public boolean passwordIsValid() {
        return false;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
    public Map<Integer, String[]> getFlashcardSets() {
        return flashcardSets;
    }

}
