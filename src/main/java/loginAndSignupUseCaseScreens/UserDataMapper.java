package loginAndSignupUseCaseScreens;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Frameworks/Drivers layer

/**
 * Used by Spring to know what data to maintain. It's used by JpaRepository,
 * Spring's root of the programmer-written persistence classes mapping data to =
 * storage.
 */
class UserDataMapper {

    String name;

    String password;

    boolean isAdmin;

    LocalDateTime creationTime;

    ArrayList<Integer> flashcardSetIds;

    public UserDataMapper() {
    }

    public UserDataMapper(String name, String password, boolean isAdmin, ArrayList flashcardSetIds, LocalDateTime creationTime) {
        super();
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
        this.creationTime = creationTime;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}

