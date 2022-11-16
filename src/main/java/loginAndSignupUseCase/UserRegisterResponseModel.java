package loginAndSignupUseCase;


public class UserRegisterResponseModel {

    String loggedInUsername;
    boolean isAdmin;
//    String creationTime;

    public UserRegisterResponseModel(String login, boolean isAdmin) {
        this.loggedInUsername = login;
        this.isAdmin = isAdmin;
//        this.creationTime = creationTime;
    }

    public String getLogin() {
        return loggedInUsername;
    }
    public boolean getIsAdmin(){return isAdmin;}

    public void setLogin(String login) {
        this.loggedInUsername = login;
    }



//    public String getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }
}
