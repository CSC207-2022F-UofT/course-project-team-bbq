package loginAndSignupUseCase;

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
     * GETTERS for the UserRegisterRequestModel
     */
    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getAdminKeyEntered() {
        return adminKeyEntered;
    }
//    public void setAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }



}

