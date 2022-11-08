package entities.user;

public interface UserFactory {

    User create(String username, String password);
}
