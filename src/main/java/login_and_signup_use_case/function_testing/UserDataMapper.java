package login_and_signup_use_case.function_testing;

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

    public UserDataMapper(String name, String password, boolean isAdmin, ArrayList<Integer> flashcardSetIds) {
        super();
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.flashcardSetIds = flashcardSetIds;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin(boolean isAdmin){return isAdmin;}

    public void setIsAdmin(boolean isAdmin){this.isAdmin = isAdmin;}

}

