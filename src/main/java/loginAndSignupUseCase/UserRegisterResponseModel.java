package loginAndSignupUseCase;

public class UserRegisterResponseModel {

    String login;
//    String creationTime;

    public UserRegisterResponseModel(String login) {
        this.login = login;
//        this.creationTime = creationTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

//    public String getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(String creationTime) {
//        this.creationTime = creationTime;
//    }
}
