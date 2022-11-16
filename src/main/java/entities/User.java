package entities;
import java.util.*;

public interface User {
    String username = null;

    String password = null;
    boolean isAdmin = false;

    //Map<Integer, String[]> flashcardSets = null;
    ArrayList<Integer> flashcardSetIds = null;

    String getUsername();

    String getPassword();


    boolean passwordIsValid();

    boolean getIsAdmin();

    ArrayList<Integer> getFlashcardSetIds();


}
