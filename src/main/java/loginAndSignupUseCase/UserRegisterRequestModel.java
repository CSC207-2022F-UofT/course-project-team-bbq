package loginAndSignupUseCase;

// Use case layer

import java.util.Map;

public class UserRegisterRequestModel {

    private String name;
    private String password;
    private String repeatPassword;
    private boolean isAdmin;
    private String ADMIN_KEY = "BuiltDifferent";

    public UserRegisterRequestModel(String name, String password, String repeatPassword, boolean isAdmin) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.isAdmin = isAdmin;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getADMIN_KEY(){
        return ADMIN_KEY;
    }


}

