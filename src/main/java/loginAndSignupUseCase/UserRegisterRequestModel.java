package loginAndSignupUseCase;

// Use case layer

public class UserRegisterRequestModel {

    private final String name;
    private final String password;
    private final String repeatPassword;
    //private boolean isAdmin;
    private final String adminKeyEntered;

    public UserRegisterRequestModel(String name, String password, String repeatPassword, String adminKeyEntered) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.adminKeyEntered = adminKeyEntered;
    }

    String getName() {
        return name;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getAdminKeyEntered() {
        return adminKeyEntered;
    }
//    public void setAdmin(boolean isAdmin) {
//        this.isAdmin = isAdmin;
//    }



}

