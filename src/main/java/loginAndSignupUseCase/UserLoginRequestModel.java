package loginAndSignupUseCase;

public class UserLoginRequestModel {
    //Login Data

    private String username;
    private String password;

    public UserLoginRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

}
