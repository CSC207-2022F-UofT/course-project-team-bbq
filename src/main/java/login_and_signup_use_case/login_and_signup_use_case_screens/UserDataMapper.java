package login_and_signup_use_case.login_and_signup_use_case_screens;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Used by Spring to know what data to maintain. It's used by JpaRepository,
 * Spring's root of the programmer-written persistence classes mapping data to =
 * storage. If required.
 *<p>
 * Frameworks and Drivers
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

class UserDataMapper {

    String name;

    String password;

    boolean isAdmin;

    ArrayList<Integer> flashcardSetIds;

    public UserDataMapper() {
    }

    public UserDataMapper(String name, String password, boolean isAdmin, ArrayList flashcardSetIds, LocalDateTime creationTime) {
        super();
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin(boolean isAdmin){return isAdmin;}

    public void setIsAdmin(){this.isAdmin = isAdmin;}

    public ArrayList getFlashcardSetIds(){
        return flashcardSetIds;
    }

}

