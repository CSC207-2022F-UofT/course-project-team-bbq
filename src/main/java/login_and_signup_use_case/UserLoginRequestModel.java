package login_and_signup_use_case;

/**
 * Login Request Model to request from the user.
 * Application Business Rules
 * @author Aryan Chablani
 */
public class UserLoginRequestModel {

    private final String username;
    private final String password;

    /**
     * Constructs a login request model.
     * @param username the username of the user
     * @param password the password of the user
     */
    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * GETTERS AND SETTERS
     */
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
