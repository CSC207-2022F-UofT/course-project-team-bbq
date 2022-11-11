package entities;

public class AdminUser extends User{
    private String adminKey = "Built Different";

    public AdminUser(String username, String password) {
        super(username, password);
    }

}
