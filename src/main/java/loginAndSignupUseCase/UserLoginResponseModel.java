package loginAndSignupUseCase;

import java.util.HashMap;

public class UserLoginResponseModel {
    String username;

    boolean isAdmin;

    HashMap<Integer, String[]> flashcardSets;

    //public UserLoginResponseModel();

//    public UserRegisterResponseModel(String signedUpUsername, boolean isAdmin) {
//        this.signedUpUsername = signedUpUsername;
//        this.isAdmin = isAdmin;
//    }
//
//    public String getSignedUpUsername() {
//        return signedUpUsername;
//    }
//    public boolean getIsAdmin(){return isAdmin;}
}
