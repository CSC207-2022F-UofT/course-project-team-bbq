package entities;
import java.util.*;

public class User {
    private String username;
    private String password;
    private HashMap<Integer, String[]> flashcardSets;

    public User(String username, String password){
        this.username = username;
        this.password = password;
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

    public void setFlashcardSets(HashMap<Integer, String[]> flashcardSets) {
        this.flashcardSets = flashcardSets;
    }
}
