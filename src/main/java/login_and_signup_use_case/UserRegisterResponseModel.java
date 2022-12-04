package login_and_signup_use_case;

/**
 * User Register Response Model
 * Application Business Rules
 * @author Aryan Chablani
 */

public class UserRegisterResponseModel {

    final String signedUpUsername;

    final String signedUpPassword;
    final boolean isAdmin;

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
     * Gets the username of the user as it would be saved in the database.
     * @return the username assigned to the user for their account.
     */
    public String getSignedUpUsername() {
        return signedUpUsername;
    }

    /**
     * Gets the password of the user as it would be saved in the database.
     * @return the password assigned to the user for their account.
     */
    public String getSignedUpPassword(){
        return signedUpPassword;
    }

    /**
     * Gets the admin level access of the user as it would be saved in the database.
     * @return whether the user is assigned admin level access for their account.
     */
    public boolean getIsAdmin(){return isAdmin;}

}
