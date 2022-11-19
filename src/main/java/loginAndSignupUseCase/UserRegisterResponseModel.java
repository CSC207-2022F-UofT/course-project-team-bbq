package loginAndSignupUseCase;


public class UserRegisterResponseModel {

    String signedUpUsername;

    String signedUpPassword;
    boolean isAdmin;

    public UserRegisterResponseModel(String signedUpUsername, String signedUpPassword, boolean isAdmin) {
        this.signedUpUsername = signedUpUsername;
        this.signedUpPassword = signedUpPassword;
        this.isAdmin = isAdmin;
    }

    public String getSignedUpUsername() {
        return signedUpUsername;
    }

    public String getSignedUpPassword(){
        return signedUpPassword;
    }
    public boolean getIsAdmin(){return isAdmin;}

}
