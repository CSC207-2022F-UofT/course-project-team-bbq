package entities;

import java.util.Map;

public interface UserFactory {

    User create(String username, String password, boolean isAdmin, Map<Integer, String[]> flashcardSets);
}
