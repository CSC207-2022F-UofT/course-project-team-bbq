package login_and_signup_use_case;

/**
 * User Register Response Model
 * Application Business Rules
 * @author Aryan Chablani
 */

public class UserRegisterResponseModel {

    String signedUpUsername;

    String signedUpPassword;
    boolean isAdmin;

    /**
     * Constructs a signup response model.
     * @param signedUpUsername the username signed up for the user
     * @param signedUpPassword the password signed up for the user
     * @param isAdmin to check whether they should be allowed admin level access
     */
    public UserRegisterResponseModel(String signedUpUsername, String signedUpPassword, boolean isAdmin) {
        this.signedUpUsername = signedUpUsername;
        this.signedUpPassword = signedUpPassword;
        this.isAdmin = isAdmin;
    }

    /**
     * GETTERS for the UserRegisterResponseModel
     */
    public String getSignedUpUsername() {
        return signedUpUsername;
    }

    public String getSignedUpPassword(){
        return signedUpPassword;
    }
    public boolean getIsAdmin(){return isAdmin;}

}
