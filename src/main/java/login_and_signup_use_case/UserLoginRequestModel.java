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
     * Gets the inputted username of the user
     * @return the username that the user inputted
     */
    String getUsername() {
        return username;
    }

    /**
     * Sets the inputted password of the user to be as given.
     * @return the password that the user inputted
     */
    String getPassword() {
        return password;
    }

}
