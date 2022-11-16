package entities;

import java.util.ArrayList;
import java.util.Map;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password, boolean isAdmin) {
        return new CommonUser(username, password, isAdmin);
    }
}
