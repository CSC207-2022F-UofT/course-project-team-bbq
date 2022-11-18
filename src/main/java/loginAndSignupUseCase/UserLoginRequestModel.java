package loginAndSignupUseCase;

public class UserLoginRequestModel {
    //Login Data

    private final String username;
    private final String password;

    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
