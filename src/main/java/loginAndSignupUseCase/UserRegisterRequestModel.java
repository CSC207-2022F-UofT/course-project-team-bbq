package loginAndSignupUseCase;

// Use case layer

import java.util.Map;

public class UserRegisterRequestModel {

    private String name;
    private String password;
    private String repeatPassword;
    //private boolean isAdmin;
    private String adminKeyEntered;

    public UserRegisterRequestModel(String name, String password, String repeatPassword, String adminKeyEntered) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.adminKeyEntered = adminKeyEntered;
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

    public String getAdminKeyEntered() {
        return adminKeyEntered;
    }
//    public void setAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }



}

