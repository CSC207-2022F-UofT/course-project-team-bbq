package login_and_signup_use_case;

/**
 * Register Request Model to request from the user.
 * Application Business Rules
 * @author Aryan Chablani
 */

public class UserRegisterRequestModel {

    private final String name;
    private final String password;
    private final String repeatPassword;
    //private boolean isAdmin;
    private final String adminKeyEntered;

    /**
     * Constructs a signup request model.
     * @param name the username of the user
     * @param password the password of the user
     * @param repeatPassword the repeated password entered by the user to ensure coherency with the password
     * @param adminKeyEntered the admin key entered by the user to check whether they should be allowed admin
     *                        level access
     */
    public UserRegisterRequestModel(String name, String password, String repeatPassword, String adminKeyEntered) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.adminKeyEntered = adminKeyEntered;
    }

    /**
     * Gets the inputted name that the user opts to be as their username.
     * @return the username that the user inputted.
     */
    String getName() {
        return name;
    }

    /**
     * Gets the inputted password that the user opts to be as their password.
     * @return the password that the user inputted.
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the inputted repeated password that the user opts to be as their password again to ensure that
     * they are sure of the password and that it matches their previously inputted password.
     * @return the password that the user inputted.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the admin key that the user has inputted to check whether they should be given admin level access.
     * @return the admin key inputted by the user.
     */
    public String getAdminKeyEntered() {
        return adminKeyEntered;
    }

}

