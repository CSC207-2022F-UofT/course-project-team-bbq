package entities;

import java.util.ArrayList;
import java.util.Map;

public interface UserFactory {

    User create(String username, String password, boolean isAdmin);
}
