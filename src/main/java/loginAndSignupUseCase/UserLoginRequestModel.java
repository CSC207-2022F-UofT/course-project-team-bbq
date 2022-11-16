package loginAndSignupUseCase;

public class UserLoginRequestModel {
    //Login Data

    private String name;
    private String password;

    public UserLoginRequestModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

}
