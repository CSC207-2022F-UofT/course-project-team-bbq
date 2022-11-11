package entities;

public class AdminUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new AdminUser(username, password);
    }
}
