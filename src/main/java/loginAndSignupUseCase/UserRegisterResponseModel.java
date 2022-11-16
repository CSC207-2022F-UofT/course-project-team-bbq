package loginAndSignupUseCase;


public class UserRegisterResponseModel {

    String signedUpUsername;
    boolean isAdmin;

    public UserRegisterResponseModel(String signedUpUsername, boolean isAdmin) {
        this.signedUpUsername = signedUpUsername;
        this.isAdmin = isAdmin;
    }

    public String getSignedUpUsername() {
        return signedUpUsername;
    }
    public boolean getIsAdmin(){return isAdmin;}

}
