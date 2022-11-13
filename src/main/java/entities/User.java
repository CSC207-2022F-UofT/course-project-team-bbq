package entities;
import java.util.*;

public class User {
    public String username;
    // NEEDS TO BE PUBLIC FOR IT TO BE ACCESSED IN THE OTHER CLASSES
    public String password;
    public boolean isAdmin;
    private Map<Integer, String[]> flashcardSets;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        //this.isAdmin = isAdmin;
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

    public Map<Integer, String[]> getFlashcardSets() {
        return flashcardSets;
    }


}
